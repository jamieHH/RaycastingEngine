package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.jamapp.JamappClient;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.overlays.ConsoleOverlay;
import com.jamie.raycasting.graphics.overlays.ItemsOverlay;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.graphics.overlays.TextOverlay;
import com.jamie.raycasting.graphics.overlays.menus.*;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.input.Controls;
import com.jamie.raycasting.world.World;
import com.jamie.raycasting.world.levels.Level;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Client extends JamappClient
{
	private static World world;
	private static Mob player;
	public static int playerLives = 3;
	public static String lastLevelName;
	public static String lastLevelRef;

	private static InputHandler temporaryInput = new ArtificialInputHandler();

	public static final Menu mainMenu = new MainMenu();
    public static final Menu optionsMenu = new OptionsMenu();
    public static final Menu pauseMenu = new PauseMenu();
    public static final Menu overMenu = new OverMenu();
    public static final Overlay inventoryOverlay = new ItemsOverlay();
    public static final Overlay consoleOverlay = new ConsoleOverlay();

	private static Overlay activeOverlay;
	private static Overlay previousOverlay;

	// stats
	public static int totalMobs;
	public static int totalMobsSlain;
	public static int totalLifeContainers;
	public static int totalLifeContainersCollected;

	
	public Client(InputHandler input) {
		super(input);

        setActiveOverlay(mainMenu);
	}

	public static void calcStats() {
		totalMobs = 0;
		totalMobsSlain = 0;
		totalLifeContainers = 0;
		totalLifeContainersCollected = 0;

		Iterator it = world.getLevelCache().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Level> pair = (HashMap.Entry<String, Level>)it.next();
			Level lvl = pair.getValue();
			totalMobs += lvl.getInitMobsCount();
			totalLifeContainers += lvl.getInitLifeContainerCount();
			totalMobsSlain += lvl.getMobsSlainCount();
			totalLifeContainersCollected += lvl.getLifeContainersCollectedCount();
		}

		System.out.println("totalMobs: " + totalMobs);
		System.out.println("totalMobsSlain: " + totalMobsSlain);
		System.out.println("totalLifeContainers: " + totalLifeContainers);
		System.out.println("totalLifeContainersCollected: " + totalLifeContainersCollected);
	}

	public void tick() {
		input.tick();
		if (getActiveOverlay() != null) {
			input.unlockCursor();
			getActiveOverlay().tick();
		} else {
			input.lockCursor();
		}

        if (world != null) {
            world.tick();
        }

		if (getPlayer() != null) {
			getPlayer().isUsingMenu = getActiveOverlay() != null;
			if (!getPlayer().isDead) {
				if (getActiveOverlay() != consoleOverlay) {
					if (input.check(Controls.INVENTORY)) {
						input.stopInput(Controls.INVENTORY);
						if (getActiveOverlay() == null) {
							Sound.slideUp.play();
							setActiveOverlay(inventoryOverlay);
						} else {
							Sound.slideDown.play();
							setActiveOverlay(null);
						}
					}

					if (input.check(Controls.PAUSE)) {
						input.stopInput(Controls.PAUSE);
						if (getActiveOverlay() == null) {
							Sound.slideUp.play();
							setActiveOverlay(pauseMenu);
						} else {
							Sound.slideDown.play();
							setActiveOverlay(null);
						}
					}
				}

				// Console requires other windows not to open while typing
				if (input.check(Controls.CONSOLE)) {
					input.stopInput(Controls.CONSOLE);
					input.stopInput(Controls.PAUSE);
					if (getActiveOverlay() == null) {
						setActiveOverlay(consoleOverlay);
					} else {
						setActiveOverlay(null);
					}
				}
			} else {
				if (getActiveOverlay() != overMenu) {
					setActiveOverlay(null);
				}
				if (input.check(Controls.PAUSE)) {
					input.stopInput(Controls.PAUSE);
					if (getActiveOverlay() == null) {
						Sound.slideUp.play();
						setActiveOverlay(overMenu);
					} else {
						Sound.slideDown.play();
						setActiveOverlay(null);
					}
				}
			}
		}
	}
	
	public static void newGame(String level) {
		setWorld(new World());
		setPlayer(new Player(input));
		getPlayer().setRotation(1.9);
		getWorld().switchLevel(getPlayer(), level, null);
		setActiveOverlay(null);

		alert("Press [Space] or Click to activate objects");
	}

	public static void restartLevel() {
		getPlayer().resetHealth();
		getWorld().unloadLevel(lastLevelName);
		getWorld().switchLevel(getPlayer(), lastLevelName, lastLevelRef);
		setActiveOverlay(null);
		playerLives --;
		alert(playerLives + " Lives Left!");
	}

	public static void alert(String message) {
		Overlay overlay = new TextOverlay(App.getDisplayWidth(), App.getDisplayHeight(), message);
		setActiveOverlay(overlay);
	}

	public static void stopGame() {
		world.clearLoadedLevels();
		world = null;
		setPlayer(null);
		setActiveOverlay(mainMenu);
	}

	public static void resizeMenus() {
		mainMenu.resizeOverlay();
		optionsMenu.resizeOverlay();
		pauseMenu.resizeOverlay();
		overMenu.resizeOverlay();
		inventoryOverlay.resizeOverlay();
		consoleOverlay.resizeOverlay();

	}

	public static void setActiveOverlay(Overlay overlay) {
		previousOverlay = activeOverlay;
		activeOverlay = overlay;
		if (getActiveOverlay() instanceof Menu) {
			((Menu) getActiveOverlay()).resetSelection();
		}
	}

	public static Overlay getActiveOverlay() {
		return activeOverlay;
	}

	public static Overlay getPreviousOverlay() {
		return previousOverlay;
	}

	public static void switchPerspective() {
		int i = world.level.getMobEntities().indexOf(player) + 1;
		if (i >= world.level.getMobEntities().size()) {
			i = 0;
		}

		setPlayer(world.level.getMobEntities().get(i));
	}

	public static void possessNextMob() {
		int i = world.level.getMobEntities().indexOf(player) + 1;
		if (i >= world.level.getMobEntities().size()) {
			i = 0;
		}

        getPlayer().input = temporaryInput;
        setPlayer(world.level.getMobEntities().get(i));
        temporaryInput = getPlayer().input;
        getPlayer().input = input;
	}

	public static Mob getPlayer() {
		return player;
	}

	public static void setPlayer(Mob player) {
		Client.player = player;
	}

	public static World getWorld() {
		return world;
	}

	public static void setWorld(World world) {
		Client.world = world;
	}
}
package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.jamapp.JamappClient;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.overlays.InventoryOverlay;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.graphics.overlays.TextOverlay;
import com.jamie.raycasting.graphics.overlays.menus.*;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.input.Controls;
import com.jamie.raycasting.world.World;

public class Client extends JamappClient
{
	private static World world;
	private static Mob player;

	private static InputHandler temporaryInput = new ArtificialInputHandler();

	public static final Menu mainMenu = new MainMenu(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    public static final Menu loadMenu = new LoadMenu(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    public static final Menu optionsMenu = new OptionsMenu(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    public static final Menu pauseMenu = new PauseMenu(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    public static final Menu overMenu = new OverMenu(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
//    public static final Overlay inventoryOverlay = new InventoryOverlay((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));
    public static final Overlay inventoryOverlay = new ItemsOverlay((int) (App.getDisplayWidth()), (int) (38));
    public static final Overlay consoleOverlay = new ConsoleOverlay((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));

	private static Overlay activeOverlay;
	private static Overlay previousOverlay;

	
	public Client(InputHandler input) {
		super(input);

        setActiveOverlay(mainMenu);
	}

	public void tick() {
		if (getActiveOverlay() != null) {
			getActiveOverlay().tick();
			input.unlockCursor();
		} else {
			input.lockCursor();
        }

        if (world != null) {
			world.tick();

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

					if (input.check(Controls.CONSOLE) || input.check(Controls.PAUSE)) {
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
	}
	
	public static void newGame(String level) {
		setWorld(new World());
		setPlayer(new Player(input));
		getPlayer().setRotation(1.9);
		getWorld().switchLevel(getPlayer(), level, 0);
		setActiveOverlay(null);

		alert("Press [Space] or Click to activate objects");
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
		mainMenu.setSize(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
		loadMenu.setSize(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
		optionsMenu.setSize(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
		pauseMenu.setSize(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
		overMenu.setSize(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
		inventoryOverlay.setSize((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));
		consoleOverlay.setSize((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));
	}

	public static void setActiveOverlay(Overlay overlay) {
		previousOverlay = activeOverlay;
		activeOverlay = overlay;
		if (getActiveOverlay() instanceof Menu) {
			((Menu) getActiveOverlay()).optionIndex = 0;
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
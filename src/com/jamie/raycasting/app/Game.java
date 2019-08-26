package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.jamapp.GameLoop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.overlays.InventoryOverlay;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.graphics.overlays.menus.*;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.input.Controls;
import com.jamie.raycasting.world.World;

public class Game extends GameLoop
{
	public World world;
	private Mob player;

	private InputHandler temporaryInput = new ArtificialInputHandler();

	public final Menu mainMenu = new MainMenu(App.width, (int) (App.height * 0.6));
    public final Menu loadMenu = new LoadMenu(App.width, (int) (App.height * 0.6));
    public final Menu optionsMenu = new OptionsMenu(App.width, (int) (App.height * 0.6));
    public final Menu pauseMenu = new PauseMenu(App.width, (int) (App.height * 0.6));
    public final Menu overMenu = new OverMenu(App.width, (int) (App.height * 0.6));
    public final Overlay inventoryOverlay = new InventoryOverlay((int) (App.width * 0.8), (int) (App.height * 0.6));

	public Overlay activeOverlay;
	
	
	public Game(InputHandler input) {
		super(input);

        setActiveOverlay(mainMenu);
	}

	public void tick() {
		if (activeOverlay != null) {
			activeOverlay.tick(this);
			userInput.lockCursor = false;
		} else {
            userInput.lockCursor = true;
        }

        if (world != null) {
			world.tick();

			if (getPlayer() != null) {
				if (activeOverlay != null) {
					getPlayer().isUsingMenu = true;
				} else {
					getPlayer().isUsingMenu = false;
				}

				if (userInput.nextMob) {
					userInput.stopInput(Controls.NEXTMOB);
					possessNextMob();
				}

				if (!getPlayer().isDead) {
					if (userInput.inventory) {
						userInput.stopInput(Controls.INVENTORY);
						if (activeOverlay == null) {
							Sound.slideUp.play();
							setActiveOverlay(inventoryOverlay);
						} else {
							Sound.slideDown.play();
							setActiveOverlay(null);
						}
					}

					if (userInput.pause) {
						userInput.stopInput(Controls.PAUSE);
						if (activeOverlay == null) {
							Sound.slideUp.play();
							setActiveOverlay(pauseMenu);
						} else {
							Sound.slideDown.play();
							setActiveOverlay(null);
						}
					}

					if (userInput.randomLevel) {
						world.switchLevel(player, "test", 0);
					}

					if (userInput.loadLevel) {
						world.switchLevel(player, "island", 0);
					}
				} else {
					if (activeOverlay == inventoryOverlay) {
						setActiveOverlay(null);
					}
					if (userInput.pause) {
						userInput.stopInput(Controls.PAUSE);
						if (activeOverlay == null) {
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
	
	public void newGame() {
		world = new World(this);
		setPlayer(new Player(userInput));
		getPlayer().setRotation(1.9);

		world.switchLevel(player, "dungeon", 0);
		setActiveOverlay(null);
	}

	public void stopGame() {
		world.clearLoadedLevels();
		world = null;
		setPlayer(null);
		setActiveOverlay(mainMenu);
	}

	public void setActiveOverlay(Overlay activeOverlay) {
		this.activeOverlay = activeOverlay;
		if (activeOverlay instanceof Menu) {
			((Menu) this.activeOverlay).optionIndex = 0;
		}
	}

	private void switchPerspective() {
		int i = world.level.getMobEntities().indexOf(player) + 1;
		if (i >= world.level.countMobs()) {
			i = 0;
		}

		setPlayer(world.level.getMobEntity(i));
	}

	private void possessNextMob() {
		int i = world.level.getMobEntities().indexOf(player) + 1;
		if (i >= world.level.countMobs()) {
			i = 0;
		}

        getPlayer().input = temporaryInput;
        setPlayer(world.level.getMobEntity(i));
        temporaryInput = getPlayer().input;
        getPlayer().input = userInput;
	}

	public Mob getPlayer() {
		return player;
	}

	public void setPlayer(Mob player) {
		this.player = player;
	}
}
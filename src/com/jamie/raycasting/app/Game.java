package com.jamie.raycasting.app;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.overlays.InventoryOverlay;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.graphics.overlays.menus.*;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.world.World;

public class Game
{
	private int pauseTime;

	public World world;

	public Mob player;
	public UserInputHandler userInput;
	private InputHandler temporaryInput = new InputHandler();

	public final Menu mainMenu = new MainMenu(App.width, (int) (App.height * 0.6));
    public final Menu loadMenu = new LoadMenu(App.width, (int) (App.height * 0.6));
    public final Menu optionsMenu = new OptionsMenu(App.width, (int) (App.height * 0.6));
    public final Menu pauseMenu = new PauseMenu(App.width, (int) (App.height * 0.6));
    public final Menu overMenu = new OverMenu(App.width, (int) (App.height * 0.6));
    public final Overlay inventoryOverlay = new InventoryOverlay((int) (App.width * 0.8), (int) (App.height * 0.6));

	public Overlay activeOverlay;
	
	
	public Game(UserInputHandler input) {
		userInput = input;

        setActiveOverlay(mainMenu);
	}

	public void tick() {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

		if (activeOverlay != null) {
			activeOverlay.tick(this);
		}


        if (world != null) {
			world.tick();

			if (player != null) {
				if (activeOverlay != null) {
					player.isUsingMenu = true;
				} else {
					player.isUsingMenu = false;
				}

				if (userInput.nextMob) {
					userInput.setInputState("nextMob", false);
//					switchPerspective();
					possessNextMob();
				}

				if (!player.isDead) {
					if (userInput.inventory) {
						userInput.setInputState("inventory", false);
						if (activeOverlay == null) {
							Sound.slideUp.play();
							setActiveOverlay(inventoryOverlay);
						} else {
							Sound.slideDown.play();
							setActiveOverlay(null);
						}
					}

					if (userInput.pause) {
						userInput.setInputState("pause", false);
						if (activeOverlay == null) {
							Sound.slideUp.play();
							setActiveOverlay(pauseMenu);
						} else {
							Sound.slideDown.play();
							setActiveOverlay(null);
						}
					}

					if (userInput.randomLevel) {
						player.rotation = 0.2;
						world.switchLevel(player, "test", 999);
					}

					if (userInput.loadLevel) {
						player.rotation = 0.2;
						world.switchLevel(player, "island", 999);
					}
				} else {
					if (userInput.action || userInput.pause) {
						setActiveOverlay(overMenu);
					}
				}
			}
		}
	}
	
	public void newGame() {
		world = new World(this);
		player = new Player(userInput);
		player.rotation = 1.9;

		world.level = world.getLoadLevel("prison");
		world.level.addEntity(player, world.level.spawnX, world.level.spawnZ);

		setActiveOverlay(null);
	}

	public void stopGame() {
		world.clearLoadedLevels();
		world = null;
		player = null;
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

		player = world.level.getMobEntity(i);
	}

	private void possessNextMob() {
		int i = world.level.getMobEntities().indexOf(player) + 1;
		if (i >= world.level.countMobs()) {
			i = 0;
		}

        player.input = temporaryInput;
        player = world.level.getMobEntity(i);
        temporaryInput = player.input;
        player.input = userInput;
	}
}
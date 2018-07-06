package com.jamie.raycasting.app;

import java.util.List;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.graphics.overlays.menus.*;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.world.levels.Level;
import com.jamie.raycasting.world.World;
import com.jamie.raycasting.world.blocks.Block;
import com.jamie.raycasting.world.blocks.LadderBlock;

public class Game
{
	public int time;
	public int pauseTime;

	public World world = new World(this);

	public Mob player;
	public UserInputHandler userInput;
	private InputHandler temporaryInput = new InputHandler();

	public final Menu mainMenu = new MainMenu(App.width, (int) (App.height * 0.6));
    public final Menu loadMenu = new LoadMenu(App.width, (int) (App.height * 0.6));
    public final Menu optionsMenu = new OptionsMenu(App.width, (int) (App.height * 0.6));
    public final Menu pauseMenu = new PauseMenu(App.width, (int) (App.height * 0.6));
    public final Menu overMenu = new OverMenu(App.width, (int) (App.height * 0.6));

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

		world.tick();
		time++;

		if (player != null) {
			if (activeOverlay != null) {
				player.isUsingMenu = true;
			} else {
				player.isUsingMenu = false;
			}

			if (userInput.nextMob) {
				switchPerspective();
//				possessNextMob();
			}

			if (!player.isDead) {
				if (userInput.inventory) {
					userInput.setKeyGroupState("inventory", false);
					if (activeOverlay == null) {
						setActiveOverlay(new InventoryOverlay((int) (App.width * 0.8), (int) (App.height * 0.6), this));
					} else {
						setActiveOverlay(null);
					}
				}

				if (userInput.pause) {
					userInput.setKeyGroupState("pause", false);
					if (activeOverlay == null) {
						setActiveOverlay(pauseMenu);
					} else {
						setActiveOverlay(null);
					}
				}

				if (userInput.randomLevel) {
					player.rotation = 0.2;
//					world.switchLevel("random", 999);
//					world.switchLevel("test", 999);
					world.switchLevel("realm", 999);
					world.switchLevel("dungeon", 999);
				}

				if (userInput.loadLevel) {
					player.rotation = 0.2;
					world.switchLevel("island", 999);
				}
			} else {
				if (userInput.action || userInput.pause) {
					setActiveOverlay(overMenu);
				}
			}
		}
	}
	
	public void newGame() {
		world.clearLoadedLevels();
		player = new Player(userInput);

		world.level = world.getLoadLevel("prison");

		player.setPosition(world.level.spawnX, world.level.spawnZ);
		player.rotation = 1.9;

		world.level.addEntity(player);

		setActiveOverlay(null);
	}

	public void stopGame() {
		world.clearLoadedLevels();
		player = null;
		world = new World(this);
		setActiveOverlay(mainMenu);
	}

	public void setActiveOverlay(Overlay activeOverlay) {
		this.activeOverlay = activeOverlay;
		if (activeOverlay instanceof Menu) {
			((Menu) this.activeOverlay).optionIndex = 0;
		}
	}

	private void switchPerspective() {
        pauseTime = 10;

		int i = world.level.getMobEntities().indexOf(player) + 1;
		if (i >= world.level.countMobs()) {
			i = 0;
		}

		player = world.level.getMobEntity(i);
	}

	private void possessNextMob() {
		pauseTime = 10;

		List<Mob> mobs = world.level.getMobEntities();

		int i = mobs.indexOf(player) + 1;
		if (i >= world.level.countMobs()) {
			i = 0;
		}

        player.input = temporaryInput;
        player = world.level.getMobEntity(i);
        temporaryInput = player.input;
        player.input = userInput;
	}
}
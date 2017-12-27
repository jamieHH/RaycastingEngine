package com.jamie.raycasting.app;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.graphics.overlays.menus.*;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.levels.Level;
import com.jamie.raycasting.levels.blocks.Block;
import com.jamie.raycasting.levels.blocks.LadderBlock;

import javax.imageio.ImageIO;

public class Game
{
	public int time;
	public int pauseTime;
	public Level level;
	public Mob player;
	public UserInputHandler userInput;
	private InputHandler temporaryInput = new InputHandler();

	public final Menu mainMenu = new MainMenu();
    public final Menu loadMenu = new LoadMenu();
    public final Menu optionsMenu = new OptionsMenu();
    public final Menu pauseMenu = new PauseMenu();
    public final Menu overMenu = new OverMenu();
    public final Overlay loadingOverlay = new LoadingOverlay();

	public Overlay activeOverlay;
	
	public Map<String, Level> loaded = new HashMap<String, Level>();
	
	
	public Game(UserInputHandler input) {
		userInput = input;

        setActiveOverlay(mainMenu);
	}

	public void setActiveOverlay(Overlay activeOverlay) {
		this.activeOverlay = activeOverlay;
		if (activeOverlay instanceof Menu) {
            ((Menu) this.activeOverlay).optionIndex = 0;
        }
	}

	public void tick() {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

		if (activeOverlay != null) {
		    activeOverlay.tick(this);
        } else {
			level.tick();
			time++;

            if (userInput.nextMob) {
				switchPerspective();
//                possessNextMob();
            }

            if (!player.isDead) {
				if (userInput.inventory) {
					userInput.setKeyGroupState("inventory", false);
					setActiveOverlay(new InventoryOverlay(this));
				}

				if (userInput.pause) {
					userInput.setKeyGroupState("pause", false);
					setActiveOverlay(pauseMenu);
				}

				if (userInput.randomLevel) {
					player.rotation = 0.2;
					switchLevel("random", 999);
				}

				if (userInput.loadLevel) {
					player.rotation = 0.2;
					switchLevel("island", 999);
//                switchLevel("test", 999);
				}
            } else {
				if (userInput.action || userInput.pause) {
					setActiveOverlay(overMenu);
					activeOverlay.pauseTime = 10;
				}
			}
        }
	}
	
	
	public void newGame() {
		clearLoadedLevels();
		player = new Player(userInput);

		level = getLoadLevel("prison");

		player.setPosition(level.spawnX, level.spawnZ);
		player.rotation = 1.9;

		level.addEntity(player);
		level.player = player;

		activeOverlay = null;
	}
	
	
	public void switchLevel(String name, int id) {
	    setActiveOverlay(loadingOverlay);
		activeOverlay.pauseTime = 30;

		level.removeEntity(player);
		level.player = null;
		if (name == "random") {
		    level = Level.makeRandomLevel(1000, 1000);
		} else {
        	level = getLoadLevel(name);
		}
		level.setSpawn(id);

		player.setPosition(level.spawnX, level.spawnZ);

		Block spawnBlock = level.getBlock((int) level.spawnX, (int) level.spawnZ); // find a better way to disable the ladder block

		if (spawnBlock instanceof LadderBlock) {
            ((LadderBlock) spawnBlock).disabled = true;
        }

		level.addEntity(player);
		level.player = player;
	}
	
	private void clearLoadedLevels() {
		loaded.clear();
	}

	private Level getLoadLevel(String name) {
		if (loaded.containsKey(name)) {
			return loaded.get(name);
		}

		try {
			BufferedImage img = ImageIO.read(new FileInputStream("res/levels/" + name + ".png"));

			int w = img.getWidth();
			int h = img.getHeight();
			int[] pixels = new int[w * h];
			img.getRGB(0, 0, w, h, pixels, 0, w);

			Level level = Level.getByName(name);
			level.create(this, w, h, pixels);
			loaded.put(name, level);

			return level;
		} catch (Exception e) {
			System.out.println("Failed to load level: " + name + "!");
			throw new RuntimeException(e);
		}
	}

	private void switchPerspective() {
        pauseTime = 10;

		int i = level.getMobEntities().indexOf(player) + 1;
		if (i >= level.countMobs()) {
			i = 0;
		}

		player = level.getMobEntity(i);
	}

	private void possessNextMob() {
		pauseTime = 10;

		List<Mob> mobs = level.getMobEntities();

		int i = mobs.indexOf(player) + 1;
		if (i >= level.countMobs()) {
			i = 0;
		}

        player.input = temporaryInput;
        player = level.getMobEntity(i);
        temporaryInput = player.input;
        player.input = userInput;
	}
}
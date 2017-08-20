package com.jamie.raycasting.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.graphics.overlays.menus.MainMenu;
import com.jamie.raycasting.graphics.overlays.menus.OverMenu;
import com.jamie.raycasting.graphics.overlays.menus.PauseMenu;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.levels.Level;
import com.jamie.raycasting.levels.blocks.Block;
import com.jamie.raycasting.levels.blocks.LadderBlock;

public class Game {

	public int time;
	public int pauseTime;
	public Level level;
	public Mob player;
	public UserInputHandler userInput;
	
	public Overlay menu;
	
	public Map<String, Level> loaded = new HashMap<String, Level>();
	
	
	public Game(UserInputHandler input) {
		userInput = input;

        menu = new MainMenu();
//		newGame();
	}

	public void tick() {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

		if (menu != null) {
		    menu.tick(this);
        } else {
			level.tick();
			time++;

            if (player.isDead) {
                if (userInput.action || userInput.pause) {
                    menu = new OverMenu();
                    menu.pauseTime = 10;
                }
                return;
            }

            if (userInput.pause) {
                menu = new PauseMenu();
                menu.pauseTime = 10;
            }

            if (userInput.randomLevel) {
                switchLevel("random", 999);
//                switchLevel("test", 999);
            }

            if (userInput.loadLevel) {
                switchLevel("island", 999);
            }

			if (userInput.nextMob) {
				switchPlayer();
			}
        }
	}
	
	
	public void newGame() {
		clearLoadedLevels();
		menu = null;
		player = new Player(userInput);

		level = Level.getLoadLevel(this, "prison");
		
		player.posX = level.spawnX;
		player.posZ = level.spawnZ;
		player.rotation = 1.9;
		
		player.level = level;
		level.addEntity(player);
		level.player = player;
	}
	
	
	public void switchLevel(String name, int id) {
	    menu = new LoadingOverlay();
		menu.pauseTime = 30;

		level.removeEntity(player);
		level.player = null;
		if (name == "random") {
			level = new Level();
			level.generateRandomLevel(200, 200);
		} else {
			level = Level.getLoadLevel(this, name);
		}
		level.setSpawn(id);

		player.posX = level.spawnX;
		player.posZ = level.spawnZ;
		player.rotation = 0.2;

		Block spawnBlock = level.getBlock((int) (level.spawnX - 8) / 16, (int) (level.spawnZ - 8) / 16);

		if (spawnBlock instanceof LadderBlock) {
            ((LadderBlock) spawnBlock).disabled = true;
        }
		
		player.level = level;
		level.addEntity(player);
		level.player = player;
	}
	
	public void clearLoadedLevels() {
		loaded.clear();
	}

	public void switchPlayer() {
        pauseTime = 10;

        List<Mob> mobs = new ArrayList<Mob>();
        for (int e = 0; e < level.entities.size(); e++) {
            Entity ent = level.entities.get(e);
            if (ent instanceof Mob) {
                mobs.add((Mob) ent);
            }
        }

        int i = level.entities.indexOf(player);
        if (i + 1 >= mobs.size()) {
            player = mobs.get(0);
        } else {
            player = mobs.get(i + 1);
        }
	}
}
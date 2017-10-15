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
import com.jamie.raycasting.graphics.overlays.menus.*;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.levels.Level;
import com.jamie.raycasting.levels.blocks.Block;
import com.jamie.raycasting.levels.blocks.LadderBlock;

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

            if (userInput.nextMob) { // for fun :)
//				switchPlayer();
                possessNextMob();
            }

            if (player.isDead) {
                if (userInput.action || userInput.pause) {
                    setActiveOverlay(overMenu);
                    activeOverlay.pauseTime = 10;
                }
                return;
            }

            if (userInput.pause) {
                setActiveOverlay(pauseMenu);
                activeOverlay.pauseTime = 10;
            }

            if (userInput.randomLevel) {
				player.rotation = 0.2;
//                switchLevel("random", 999);
                switchLevel("test", 999);
            }

            if (userInput.loadLevel) {
				player.rotation = 0.2;
                switchLevel("island", 999);
            }
        }
	}
	
	
	public void newGame() {
		clearLoadedLevels();
		activeOverlay = null;
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
	    setActiveOverlay(loadingOverlay);
		activeOverlay.pauseTime = 30;

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

		Block spawnBlock = level.getBlock((int) (level.spawnX - 8) / 16, (int) (level.spawnZ - 8) / 16);

		if (spawnBlock instanceof LadderBlock) {
            ((LadderBlock) spawnBlock).disabled = true;
        }
		
		player.level = level;
		level.addEntity(player);
		level.player = player;
	}
	
	private void clearLoadedLevels() {
		loaded.clear();
	}

	private void switchPlayer() {
        pauseTime = 10;

        List<Mob> mobs = new ArrayList<Mob>();
        for (int e = 0; e < level.countEntities(); e++) {
            Entity ent = level.getEntity(e);
            if (ent instanceof Mob) {
                mobs.add((Mob) ent);
            }
        }

        int i = level.getEntities().indexOf(player);
        if (i + 1 >= mobs.size()) {
            player = mobs.get(0);
        } else {
            player = mobs.get(i + 1);
        }
	}

	private void possessNextMob() {
	    // build upon this functionality
		pauseTime = 10;

		List<Mob> mobs = level.getMobEntities();

		int i = mobs.indexOf(player);
		i++;
		if (i >= level.countMobs()) i = 0;


        // check logic here
        player.input = temporaryInput;
        player = level.getMobEntity(i);
        temporaryInput = player.input;
        player.input = userInput;
	}
}
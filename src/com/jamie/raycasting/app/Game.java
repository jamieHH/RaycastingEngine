package com.jamie.raycasting.app;

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

public class Game
{
	public int time;
	public int pauseTime;
	public Level level;
	public Mob player;
	public UserInputHandler userInput;
	private InputHandler temporaryInput = new InputHandler();

	public Overlay activeOverlay;
	
	public Map<String, Level> loaded = new HashMap<String, Level>();
	
	
	public Game(UserInputHandler input) {
		userInput = input;

        setActiveOverlay(new MainMenu());
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
		    System.out.println("game active overlay tick");
        } else {
			level.tick();
			time++;

//            if (userInput.nextMob) {
//                switchPerspective();
////                possessNextMob();
//            }
        }
	}
	
	
	public void newGame() {
		clearLoadedLevels();
		player = new Player(userInput);

		level = Level.getLoadLevel(this, "prison");

		player.setPosition(level.spawnX, level.spawnZ);
		player.rotation = 1.9;

		level.addEntity(player);

		setActiveOverlay(null);
	}
	
	
//	public void switchLevel(String name, int id) { // TODO: attempt to move this method into the mob class.
//	    player.setActiveOverlay(new LoadingOverlay());
//
//		player.remove();
//        level = Level.getLoadLevel(this, name);
//		level.setSpawn(id);
//
//		player.setPosition(level.spawnX, level.spawnZ);
//
//		Block spawnBlock = level.getBlock((int) (level.spawnX - 8) / 16, (int) (level.spawnZ - 8) / 16);
//
//		if (spawnBlock instanceof LadderBlock) {
//            ((LadderBlock) spawnBlock).disabled = true;
//        }
//
//		level.addEntity(player);
//	}
	
	private void clearLoadedLevels() {
		loaded.clear();
	}

	private void switchPerspective() {
        pauseTime = 10;

		int i = level.getMobEntities().indexOf(player);
		i++;
		if (i >= level.countMobs()) i = 0;

		player = level.getMobEntity(i);
	}

	private void possessNextMob() {
		pauseTime = 10;

		List<Mob> mobs = level.getMobEntities();

		int i = mobs.indexOf(player);
		i++;
		if (i >= level.countMobs()) i = 0;

        player.input = temporaryInput;
        player = level.getMobEntity(i);
        temporaryInput = player.input;
        player.input = userInput;
	}
}
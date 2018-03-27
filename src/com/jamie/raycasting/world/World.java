package com.jamie.raycasting.world;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.world.blocks.LadderBlock;
import com.jamie.raycasting.world.levels.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class World
{
    public Game game;

    public Level level;

    public Map<String, Level> loaded = new HashMap<String, Level>();


    public World(Game game) {
        this.game = game;
    }

    public void tick() {
        level.tick();
    }

    public static Level getByName(String name) {
        try {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            return (Level) Class.forName("com.jamie.raycasting.world.levels." + name + "Level").newInstance();
        } catch (Exception e) {
            System.out.println("Failed to get level by name: " + name + "!");
            throw new RuntimeException(e);
        }
    }

    public Level getLoadLevel(String name) {
        if (loaded.containsKey(name)) {
            return loaded.get(name);
        }

        try {
            BufferedImage img = ImageIO.read(new FileInputStream("res/levels/" + name + ".png"));

            int w = img.getWidth();
            int h = img.getHeight();
            int[] pixels = new int[w * h];
            img.getRGB(0, 0, w, h, pixels, 0, w);

            Level level = getByName(name);
            level.create(this.game, w, h, pixels);
            loaded.put(name, level);

            return level;
        } catch (Exception e) {
            System.out.println("Failed to load level: " + name);
            throw new RuntimeException(e);
        }
    }

    public void clearLoadedLevels() {
        loaded.clear();
    }

    public void switchLevel(String name, int id) {
        Mob thisMob = game.player; // TODO: change this to be whatever object is to be teleported;

        game.setActiveOverlay(game.loadingOverlay); //make overlays mob specific
        game.activeOverlay.pauseTime = 30;

        level.removeEntity(thisMob);
        level.player = null;

//        level = Level.makeRandomLevel(1000, 1000);
        level = getLoadLevel(name);

        level.addEntity(thisMob);
        level.player = thisMob;

        LadderBlock spawnBlock = level.getLadderBlockById(id);
        if (spawnBlock != null) {
            thisMob.setPosition(spawnBlock.gridX + 0.5, spawnBlock.gridZ + 0.5);
            spawnBlock.disabled = true;
        } else {
            thisMob.setPosition(level.spawnX, level.spawnZ);
        }
    }
}

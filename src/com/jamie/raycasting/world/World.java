package com.jamie.raycasting.world;

import com.jamie.raycasting.app.App;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;
import com.jamie.raycasting.world.blocks.LevelPortalBlock;
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
        if (level != null) {
            level.tick();
        }
    }

    public static Level getByName(String name) {
        try {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            return (Level) Class.forName("com.jamie.raycasting.world.levels." + name + "Level").getDeclaredConstructor().newInstance();
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

    public void switchLevel(Mob mob, String name, int id) {
        game.setActiveOverlay(new LoadingOverlay(App.width, App.height, name));
        Sound.switchLevel.play();

        level.removeEntity(mob);

        if (!name.equals("random")) {
            level = getLoadLevel(name);
        } else {
            level = Level.makeRandomLevel(1000, 1000);
        }

        LevelPortalBlock spawnBlock = level.getLevelPortalBlockById(id);
        if (spawnBlock != null) {
            level.addEntity(mob, spawnBlock.gridX + 0.5, spawnBlock.gridZ + 0.5);
            spawnBlock.disabled = true;
        } else {
            level.addEntity(mob, level.spawnX, level.spawnZ);
        }
    }
}

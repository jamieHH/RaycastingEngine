package com.jamie.raycasting.world;

import com.jamie.jamapp.App;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Game;
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
    public Map<String, Level> cache = new HashMap<String, Level>();


    public World(Game game) {
        this.game = game;
    }

    public void tick() {
        if (level != null) {
            level.tick();
        }
    }

    public void clearLoadedLevels() {
        cache.clear();
    }

    public void switchLevel(Mob mob, String name, int id) {
        Level newLevel;
        if (!name.equals("random")) {
            newLevel = getLoadLevel(name);
        } else {
            newLevel = Level.makeRandomLevel(1000, 1000);
        }

        game.setActiveOverlay(new LoadingOverlay(App.width, App.height, newLevel.name));
        Sound.switchLevel.play();

        if (level != null) {
            level.removeEntity(mob);
        }
        level = newLevel;

        LevelPortalBlock spawnBlock = level.getLevelPortalBlockById(id);
        if (spawnBlock != null) {
            level.addEntity(mob, spawnBlock.gridX + 0.5, spawnBlock.gridZ + 0.5);
            spawnBlock.disabled = true;
        } else {
            level.addEntity(mob, level.spawnX, level.spawnZ);
        }
    }

    private static Level getByName(String name) {
        try {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            return (Level) Class.forName("com.jamie.raycasting.world.levels." + name + "Level").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Failed to get level by name: " + name);
            throw new RuntimeException(e);
        }
    }

    private Level getLoadLevel(String name) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }

        try {
            BufferedImage img = ImageIO.read(new FileInputStream("res/levels/" + name + ".png"));

            int w = img.getWidth();
            int h = img.getHeight();
            int[] pixels = new int[w * h];
            img.getRGB(0, 0, w, h, pixels, 0, w);

            Level level = getByName(name);
            level.create(this.game, w, h, pixels);
            cache.put(name, level);

            return level;
        } catch (Exception e) {
            System.out.println("Failed to load level: " + name);
            throw new RuntimeException(e);
        }
    }
}

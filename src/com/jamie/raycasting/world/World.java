package com.jamie.raycasting.world;

import com.jamie.jamapp.App;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;
import com.jamie.raycasting.world.blocks.LevelPortalBlock;
import com.jamie.raycasting.world.levels.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class World
{
    public Level level;
    private final Map<String, Level> cache = new HashMap<>();

    public boolean enableTimeCycle = false;
    private int hour = 3;
    private int minute = 0;


    public World() {

    }

    public void tick() {
        if (level != null) {
            level.tick();
            if (level.isOutside) {
                level.fogColor = Texture.fogHours.pixels[hour];
            }
        }

        if (enableTimeCycle) {
            minute++;
            if (minute > 59) {
                minute = 0;
                hour++;
                if (hour > 23) {
                    hour = 0;
                }
            }

        }
    }

    public void clearLoadedLevels() {
        cache.clear();
    }

    public void unloadLevel(String name) {
        cache.remove(name);
    }

    public void switchLevel(Entity entity, String name, int id) {
        if (entity instanceof Player) {
            Level newLevel;
            if (!name.equals("random")) {
                newLevel = getLoadLevel(name);
            } else {
                newLevel = Level.makeRandomLevel(1000, 1000);
            }

            Client.setActiveOverlay(new LoadingOverlay(App.getDisplayWidth(), App.getDisplayHeight(), newLevel.name));
            Sound.switchLevel.play();

            if (level != null) {
                level.removeEntity(entity);
            }
            level = newLevel;

            LevelPortalBlock spawnBlock = level.getLevelPortalBlockById(id);
            if (spawnBlock != null) {
                level.addEntity(entity, spawnBlock.gridX + 0.5, spawnBlock.gridZ + 0.5);
                spawnBlock.disabled = true;
            } else {
                level.addEntity(entity, level.spawnX, level.spawnZ);
            }

            Client.lastLevelName = name;
            Client.lastLevelId = id;
        }
    }

    public Map<String, Level> getLevelCache() {
        return cache;
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
            //BufferedImage img = ImageIO.read(World.class.getClassLoader().getResource("levels/" + name + ".png"));
            BufferedImage img = ImageIO.read(new File("res/levels/" + name + ".png"));

            int w = img.getWidth();
            int h = img.getHeight();
            int[] pixels = new int[w * h];
            img.getRGB(0, 0, w, h, pixels, 0, w);

            Level level = getByName(name);
            level.create(w, h, pixels);
            cache.put(name, level);

            return level;
        } catch (Exception e) {
            System.out.println("Failed to load level: " + name);
            throw new RuntimeException(e);
        }
    }
}

package com.jamie.raycasting.world;

import com.jamie.raycasting.app.Game;
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

            Level level = Level.getByName(name);
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
}

package com.jamie.raycasting.graphics;

import java.util.HashMap;
import java.util.Map;

public class SpriteSet
{
    private Map<String, Sprite> set = new HashMap<>();
    private String setKey = "idle";
    private static final String defaultKey = "idle";
    private int swapTicks = 0;
    private boolean isSetSwapped = false;

    public void tick() {
        if (getSprite() != null) {
            getSprite().tick();

            if (isSetSwapped) {
                swapTicks++;
                if (swapTicks >= getSet(setKey).textures.length * Sprite.INTERVAL) {
                    isSetSwapped = false;
                    switchSet(defaultKey);
                }
            }
        }
    }

    public void putSet(String name, Sprite sprite) {
        set.put(name, sprite);
    }

    public String getActiveSetKey() {
        return setKey;
    }

    public void switchSet(String key) {
        if (getSet(key) != null) {
            getSet(key).reset();
        }
        setKey = key;
    }

    public void runSet(String key) {
        if (getSet(key) != null) {
            switchSet(key);
            isSetSwapped = true;
            swapTicks = 0;
        }
    }

    public Sprite getSprite() {
        return getSet(setKey);
    }

    private Sprite getSet(String key) {
        return set.get(key);
    }
}

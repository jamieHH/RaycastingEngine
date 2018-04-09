package com.jamie.raycasting.graphics;

import java.util.HashMap;
import java.util.Map;

public class SpriteSet
{
    private Map<String, Sprite> set = new HashMap<String, Sprite>();
    private String setKey = "idle";
    private String defaultKey = "idle";
    private int swapTicks = 0;
    private boolean isSetSwapped = false;

    public void tick() {
        if (getSprite() != null) {
            getSprite().tick();

            if (isSetSwapped) {
                if (swapTicks > 0) {
                    swapTicks--;
                } else {
                    switchSet(defaultKey);
                    isSetSwapped = false;
                }
            }
        }
    }

    public Sprite getSet(String key) {
        return set.get(key);
    }

    public void putSet(String name, Sprite sprite) {
        set.put(name, sprite);
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

            swapTicks = getSet(key).countTextures() * getSet(key).interval;
        }
    }

    public Sprite getSprite() {
        return getSet(setKey);
    }
}

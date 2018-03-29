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

    public SpriteSet() {
        addSet("idle", new Sprite(Texture.none));
    }

    public void tick() {
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

    public Sprite getSprite() {
        return set.get(setKey);
    }

    public void addSet(String name, Sprite sprite) {
        set.put(name, sprite);
    }

    public void switchSet(String key) {
        set.get(key).reset();
        setKey = key;
    }

    public void runSet(String key) {
        switchSet(key);
        isSetSwapped = true;

        swapTicks = set.get(key).countTextures() * set.get(key).interval;
    }
}

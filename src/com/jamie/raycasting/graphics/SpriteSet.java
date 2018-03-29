package com.jamie.raycasting.graphics;

import java.util.HashMap;
import java.util.Map;

public class SpriteSet
{
    private Map<String, Sprite> set = new HashMap<String, Sprite>();
    private String setKey = "idle";
    private String defaultKey = "idle";
    private int setSwapTicks = 0;
    private boolean setIsSwapped = false;

    public SpriteSet() {
        addSet("idle", new Sprite(Texture.none));
    }

    public void addSet(String name, Sprite sprite) {
        set.put(name, sprite);
    }

    public Sprite getSet(String key) {
        return set.get(key);
    }

    public void switchSet(String key) {
        getSet(key).reset();
        setKey = key;
    }

    public void runSet(String key) {
        switchSet(key);

        // setSwapTicks is defined by the fist sprite list in the set.
        setSwapTicks = getSet(key).countTextures() * getSet(key).interval;
        setIsSwapped = true;
    }

    public Sprite getSprite() {
        return getSet(setKey);
    }

    public void tick() {
        getSprite().tick();

        if (setIsSwapped) {
            if (setSwapTicks > 0) {
                setSwapTicks--;
            } else {
                switchSet(defaultKey);
                setIsSwapped = false;
            }
        }
    }
}

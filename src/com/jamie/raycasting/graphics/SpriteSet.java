package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteSet
{
    private Map<String, List<Sprite>> set = new HashMap<String, List<Sprite>>();
    private String setKey = "idle";
    private String defaultKey = "idle";
    private int setSwapTicks = 0;
    private boolean setIsSwapped = false;

    public SpriteSet() {
        addSet("idle", new ArrayList<Sprite>());
    }

    public void addSet(String name, List<Sprite> sprites) {
        set.put(name, sprites);
    }

    public List<Sprite> getSet(String key) {
        return set.get(key);
    }

    public void switchSet(String key) {
        getSet(key).get(0).reset();
        setKey = key;
    }

    public void runSet(String key) {
        switchSet(key);

        // setSwapTicks is defined by the fist sprite list in the set.
        setSwapTicks = getSet(key).get(0).countTextures() * getSet(key).get(0).interval;
        setIsSwapped = true;
    }

    public List<Sprite> getSprites() {
        return getSet(setKey);
    }

    public void tick() {
        for (int i = 0; i < getSet(setKey).size(); i++) {
            getSprites().get(i).tick();
        }

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

package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteSet
{
    private Map<String, List<Sprite>> set = new HashMap<String, List<Sprite>>();
    public String setKey = "idle";
    public String defaultKey = "idle";
    private int setSwapTicks = 0;
    private boolean setIsSwapped = false;

    public SpriteSet() {
        addSet("idle", new ArrayList<Sprite>());
    }

    public void addSet(String name, List<Sprite> sprites) {
        set.put(name, sprites);
    }

    public void switchSet(String key) {
        set.get(key).get(0).reset();
        setKey = key;
    }

    public void runSet(String key) {
        switchSet(key);

        setSwapTicks = 0; // setSwapTicks is defined by the fist sprite list in the set.
        setSwapTicks += set.get(key).get(0).countTextures() * set.get(key).get(0).interval;

        setIsSwapped = true;
    }

    public List<Sprite> getSprites() {
        return set.get(setKey);
    }

    public void tick() {
        for (int i = 0; i < set.get(setKey).size(); i++) {
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

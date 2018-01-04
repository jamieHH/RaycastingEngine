package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteSet
{
    private Map<String, List<Sprite>> set = new HashMap<String, List<Sprite>>();
    public String setKey = "idle";
    private String previousSetKey;
    private int setSwapTicks = 0;
    private boolean setIsSwapped = false;

    public SpriteSet() {
        addSet("idle", new ArrayList<Sprite>());
    }

    public void addSet(String name, List<Sprite> sprites) {
        set.put(name, sprites);
    }

    public void addSpriteToSet(String name, Sprite sprite) {
        set.get(name).add(sprite);
    }

    public List<Sprite> getSet(String name) {
        return set.get(name);
    }

    public Sprite getSpriteInSet(String name, int index) {
        return set.get(name).get(index);
    }

    public void switchSet(String key) {
        setKey = key;
    }

    public void runSet(String key) {
        previousSetKey = setKey;
        switchSet(key);

        // TODO: check setSwapTicks is adding all of the sprites intervals together properly.
        setSwapTicks = 0;
        for (int i = 0; i < set.get(key).size(); i++) {
            setSwapTicks += set.get(key).get(i).interval;
        }

        setIsSwapped = true;
    }

    public List<Sprite> getSprites() {
        return set.get(setKey);
    }

    public void tick() {
        for (int i = 0; i < set.get(setKey).size(); i++) {
            getSprites().get(i).tick();
        }

        // Looks like not all textures are being rendered on runSet animations

        if (setIsSwapped) {
            if (setSwapTicks > 0) {
                System.out.println(setSwapTicks);
                setSwapTicks--;
            } else {
                System.out.println("done");
                switchSet(previousSetKey);
                previousSetKey = null;
                setIsSwapped = false;
            }
        }
    }
}

package com.jamie.raycasting.items;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;

import java.util.HashMap;
import java.util.Map;

public abstract class Item
{
    public String name = "Item";
    public String type = "item";
    private SpriteSet spriteSet = new SpriteSet();
    public Bitmap icon = new Bitmap(16, 16);

    public int damage = 1;
    public int reach = 0;

    public int duration = 0;
    public int magnitude = 0;

    protected Mob user;
    public boolean removed = false;
    protected int useCount = 0;

    public int useWait = 15;
    public int useTicks = 0;
    public boolean canStrike = true;

    protected Map<String, String> info = new HashMap<String, String>();


    public void setSpriteSet(String name, Sprite sprite) {
        spriteSet.putSet(name, sprite);
    }

    public void setIdleSprite(Sprite sprite) {
        setSpriteSet("idle", sprite);
    }

    public void setUseSprite(Sprite s) {
        setSpriteSet("use", s);
    }

    public Sprite getSprite() {
        return spriteSet.getSprite();
    }

    public void tick() {
        spriteSet.tick();
        if (useTicks > 0) useTicks--;
    }

    public void use() {
        spriteSet.runSet("use");
        useCount++;
        useTicks = useWait;
    }

    public Bitmap bitmap() {
        if (getSprite() != null) {
            return getSprite().bitmap();
        }

        return null;
    }

    public void setUser(Mob mob) {
        user = mob;
    }

    public void remove() {
        removed = true;
    }

    public Map<String, String> getInfo() {
        info.put("name", name);
        info.put("type", type);
        info.put("damage", Integer.toString(damage));
        info.put("reach", Integer.toString(reach));
        info.put("duration", Integer.toString(duration / 60) + "s");
        info.put("magnitude", Integer.toString(magnitude));
        return info;
    }
}

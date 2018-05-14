package com.jamie.raycasting.items;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;

import java.util.HashMap;
import java.util.Map;

public abstract class Item
{
    public String name = "Item";
    public String type = "item";
    private SpriteSet spriteSet = new SpriteSet();
    public Render icon = new Render(8, 8);

    public int weight = 1;
    public int damage = 1;
    public int value = 0;
    public int reach = 0;

    protected Mob user;
    public boolean removed = false;
    protected int useCount = 0;

    public int useTicks = 0;
    public int useWait = 15;
    public boolean canStrike = true;


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

    public Render render() {
        return getSprite().render();
    }

    public void setUser(Mob mob) {
        user = mob;
    }

    public void remove() {
        removed = true;
    }

    public Map<String, String> getInfo(){
        Map<String, String> info = new HashMap<String, String>();
        info.put("name", name);
        info.put("type", type);
        info.put("damage", Integer.toString(damage));
        info.put("weight", Integer.toString(weight));
        info.put("value", Integer.toString(value));
        info.put("reach", Integer.toString(reach));
        return info;
    }
}

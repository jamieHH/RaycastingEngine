package com.jamie.raycasting.items;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;
import com.jamie.raycasting.graphics.Texture;

public abstract class Item
{
    public String name = "Item";
    private SpriteSet spriteSet = new SpriteSet();

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

    public Render icon = new Render(8, 8);

    public Item() {
        setIdleSprite(new Sprite(Texture.none));
        setUseSprite(new Sprite(Texture.none));
    }

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
}

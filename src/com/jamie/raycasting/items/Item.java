package com.jamie.raycasting.items;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;

public abstract class Item
{
    public static final String TYPE_WEAPON = "WEAPON";
    public static final String TYPE_CONSUMABLE = "CONSUMABLE";
    public static final String TYPE_MISC = "MISC";
    public static final String TYPE_SPELL = "SPELL";

    public String name;
    public String type;
    private final SpriteSet spriteSet = new SpriteSet();
    public Bitmap icon = new Bitmap(16, 16);
    public Sprite dropSprite = null;

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

    public boolean equalTo(Item item) {
        return (
            this.name.equals(item.name) &&
            this.type.equals(item.type) &&
            this.damage == item.damage &&
            this.duration == item.duration &&
            this.magnitude == item.magnitude &&
            this.useWait == item.useWait &&
            this != user.getRightHandItem()
        );
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage + user.baseDamage;
    }

    public int getReach() {
        return reach;
    }

    public int getDuration() {
        return (duration / 60);
    }

    public int getMagnitude() {
        return magnitude;
    }
}

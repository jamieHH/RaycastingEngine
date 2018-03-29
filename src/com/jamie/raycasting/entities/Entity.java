package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;
import com.jamie.raycasting.world.levels.Level;

import java.util.Random;

public class Entity
{
	protected static final Random random = new Random();

    private SpriteSet spriteSet = new SpriteSet();

	public Level level;
    public boolean removed = false;

    public boolean isSolid = true;
    public double radius = 0.25;

    public double rotation;
    public double posX, posZ;
    public double posY;


	public void remove() {
        removed = true;
	}

    public Sprite getSprite() {
        return spriteSet.getSprite();
    }

    protected void addSpriteSet(String name, Sprite sprite) {
        spriteSet.addSet(name, sprite);
    }

    public void addIdleSprite(Sprite s) {
        addSpriteSet("idle", s);
    }
    protected void runSpriteSet(String name) {
        spriteSet.runSet(name);
    }

    public void tick() {
        spriteSet.tick();
    }

    public void setPosition(double x, double z) {
        posX = x;
        posZ = z;
    }

	protected double distanceFrom(double x, double z) {
        return Math.hypot(Math.abs(posX - x), Math.abs(posZ - z));
    }

    public double squareDistanceFrom(double x, double z)
    {
        double xDist = Math.abs(posX - x);
        double zDist = Math.abs(posZ - z);

        return (xDist > zDist) ? xDist: zDist;
    }

    public boolean contains(double x2, double z2) {
        if (posX + radius <= x2) return false;
        if (posX - radius >= x2) return false;

        return !(posZ + radius <= z2) && !(posZ - radius >= z2);
    }

    public boolean isInside(double x0, double z0, double x1, double z1) {
        if (posX + radius <= x0) return false;
        if (posX - radius >= x1) return false;

        return !(posZ + radius <= z0) && !(posZ - radius >= z1);
    }
}

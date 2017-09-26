package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.levels.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Entity {
	protected static final Random random = new Random();
	private List<Sprite> sprites = new ArrayList<Sprite>();
    protected int animTime = 5;
    public int spriteIndex = 0;

	public Level level;
    public boolean solid = true;
    public double radius = 6.0;

    public double rotation;
	public double posX, posZ;
	public double posY;

	public Boolean removed = false;

	public void remove() {
        removed = true;
	}

	public void addSprite(Sprite s) {
        sprites.add(s);
    }

    public Sprite getSprite(int i) {
        return sprites.get(i);
    }

    public int countSprites() {
        return sprites.size();
    }

    public void clearSprites() {
        sprites.clear();
    }

	protected void spriteTick() {
        if (animTime > 0) {
            animTime--;
            return;
        }

        int i = spriteIndex;
        i++;
        if (i >= countSprites()) {
            i = 0;
        }

        spriteIndex = i;
        animTime = 5;
    }

	public void tick() {
        spriteTick();
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

        if (posZ + radius <= z2) return false;
        if (posZ - radius >= z2) return false;
        return true;
    }

    public boolean isInside(double x0, double z0, double x1, double z1) {
        if (posX + radius <= x0) return false;
        if (posX - radius >= x1) return false;

        if (posZ + radius <= z0) return false;
        if (posZ - radius >= z1) return false;
        return true;
    }
}

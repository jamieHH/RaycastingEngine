package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;
import com.jamie.raycasting.levels.Level;

import java.util.ArrayList;
import java.util.List;
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

    public List<Sprite> getSprites() {
        return spriteSet.getSprites();
    }

    public void addIdleSprite(Sprite s) {
        List<Sprite> set = new ArrayList<Sprite>();
        set.add(s);
        addSpriteSet("idle", set);
    }

    public Sprite getSprite(int i) {
        return getSprites().get(i);
    }

    public int countSprites() {
        return getSprites().size();
    }

    public void addSpriteSet(String name, List<Sprite> sprites) {
	    spriteSet.addSet(name, sprites);
    }

    protected void switchSpriteSet(String name) {
        spriteSet.switchSet(name);
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

	public double correctRotation(double rotation) {
	    // TODO: normalise rotation between: 0 - 6.28319

//        double pi = 6.28319;
//        while (rotation > pi) {
//            rotation -= 2 * pi;
//        }
//        while (rotation < -pi) {
//            rotation += 2 * pi;
//        }

//        ???

//        if (rotation > 6.28319) {
//            return rotation - 6.28319;
//        } else if (rotation < 0) {
//            return 6.28319 - rotation;
//        }
//
        return rotation;
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

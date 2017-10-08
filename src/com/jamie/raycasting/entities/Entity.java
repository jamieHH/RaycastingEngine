package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.levels.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Entity
{
	protected static final Random random = new Random();

	private List<Sprite> sprites = new ArrayList<Sprite>();
    private List<Sprite> tmpSprites = new ArrayList<Sprite>();
    private int swapSpriteTicks = 0;
    private boolean spritesAreSwapped = false;

	public Level level;
    public boolean solid = true;
    public double radius = 6.0;

    public double rotation;
	public double posX, posZ;
	public double posY;

	public Boolean removed = false;

	protected void remove() {
        removed = true;
	}

	public void setSprites(List<Sprite> ss) {
        resetSprites();
        sprites = ss;
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

	public void tick() {
        for (int i = 0; i < countSprites(); i++) {
            sprites.get(i).tick();
        }

        if (swapSpriteTicks > 0) {
            swapSpriteTicks--;
        } else if (swapSpriteTicks == 0 && spritesAreSwapped) {
            resetSprites();
        }
	}

	private void resetSprites() {
        if (spritesAreSwapped) {
            spritesAreSwapped = false;
            swapSpriteTicks = 0;
            this.sprites = tmpSprites;
        }
    }

    protected void swapSprites(List<Sprite> sprites, int ticks) {
        if (spritesAreSwapped) return;
        swapSpriteTicks = ticks;
        spritesAreSwapped = true;
        tmpSprites = this.sprites;
        this.sprites = sprites;
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

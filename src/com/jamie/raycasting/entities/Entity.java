package com.jamie.raycasting.entities;

import com.jamie.jamapp.Sfx;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;
import com.jamie.raycasting.world.levels.Level;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity
{
	public Level level;
    public boolean removed = false;

    public boolean isSolid = true;
    public double radius = 0.25;

    private double rotation;
    public double posX = 0;
    public double posZ = 0;

    private SpriteSet spriteSet = new SpriteSet();

    protected abstract Sprite getSprite();


    public Entity() {
        setSpriteSet("idle", getSprite());
    }

    public void tick() {
        spriteSet.tick();
    }

    public boolean use(Mob source) {
        return false;
    }

    public void setPosition(double x, double z) {
        posX = x;
        posZ = z;
    }

    public void setRotation(double r) {
        double max = Math.toRadians(360);
        if (r > max) {
            rotation = max % r;
        } else if (r < 0) {
            rotation = max + r;
        } else {
            rotation = r;
        }
    }

    public double getRotation() {
        return rotation;
    }

    public void rotate(double r) {
        double max = Math.PI * 2.0;
        rotation = (rotation + r) % max;
        if (rotation < 0) {
            rotation += max;
        }
    }

    public void remove() {
        removed = true;
    }

    public void emitSound(Sfx sound) {
        level.playSound(sound, posX, posZ);
    }

	protected double distanceFrom(double x, double z) {
        return Math.hypot(Math.abs(posX - x), Math.abs(posZ - z));
    }

    public double squareDistanceFrom(double x, double z) {
        double xDist = Math.abs(posX - x);
        double zDist = Math.abs(posZ - z);

        return (xDist > zDist) ? xDist: zDist;
    }

    public boolean contains(double x, double z) {
        if (posX + radius <= x) return false;
        if (posX - radius >= x) return false;
        if (posZ + radius <= z) return false;
        if (posZ - radius >= z) return false;
        return true;
    }

    public boolean isInside(double x0, double z0, double x1, double z1) {
        if (posX + radius <= x0) return false;
        if (posX - radius >= x1) return false;
        if (posZ + radius <= z0) return false;
        if (posZ - radius >= z1) return false;
        return true;
    }

    public List<Entity> getEntitiesInRadius(double radius) {
        List<Entity> entities = new ArrayList<Entity>();
        for (int e = 0; e < level.countEntities(); e++) {
            Entity ent = level.getEntity(e);
            if (distanceFrom(ent.posX, ent.posZ) < radius) {
                entities.add(ent);
            }
        }

        return entities;
    }

    public List<Mob> getMobsInRadius(double radius) {
        List<Mob> mobs = new ArrayList<Mob>();
        for (int e = 0; e < level.countEntities(); e++) {
            Entity ent = level.getEntity(e);
            if (distanceFrom(ent.posX, ent.posZ) < radius && ent instanceof Mob) {
                mobs.add((Mob) ent);
            }
        }

        return mobs;
    }

    protected void setSpriteSet(String name, Sprite sprite) {
        spriteSet.putSet(name, sprite);
    }

    protected void runSpriteSet(String name) {
        spriteSet.runSet(name);
    }

    public Sprite getRenderSprite() {
        return spriteSet.getSprite();
    }
}

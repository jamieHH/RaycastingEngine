package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Sprite;

public abstract class Projectile extends Entity
{
    protected double moveSpeed = 0.2;
    protected int life = 600;

    public double detonationRadius = 2;
    public int detonationMagnitude = 1;
    public int damage = 0;

    private int dieTime = 20;
    private boolean isDetonating = false;


    public Projectile(double detonationRadius, int detonationMagnitude) { // used for magic projectiles
        super();

        radius = 0.1;
        isSolid = false;
        this.detonationRadius = detonationRadius;
        this.detonationMagnitude = detonationMagnitude;
    }

    public Projectile(int damage) { // used for bolt projectiles
        super();

        radius = 0.1;
        isSolid = false;
        this.damage = damage;
    }

    public void tick() {
        super.tick();

        if (!isDetonating) {
            life -= 1;
            if (life <= 0) {
                remove();
            } else {
                int divs = 10; // increase this to improve accuracy of collision
                for (int i = 0; i < divs; i++) {
                    double nextX = (moveSpeed * Math.sin(getRotation())) / divs;
                    if (getBlockingMob(posX + nextX, posZ) != null) {
                        getBlockingMob(posX + nextX, posZ).hurt(this, damage);
                        detonate();
                        break;
                    }
                    if (isWallBlocked(posX + nextX, posZ)) {
                        detonate();
                        break;
                    }
                    posX += nextX;

                    double nextZ = (moveSpeed * Math.cos(getRotation())) / divs;
                    if (getBlockingMob(posX, posZ + nextZ) != null) {
                        getBlockingMob(posX, posZ + nextZ).hurt(this, damage);
                        detonate();
                        break;
                    }
                    if (isWallBlocked(posX, posZ + nextZ)) {
                        detonate();
                        break;
                    }
                    posZ += nextZ;
                }
            }
        } else {
            dieTime--;
            if (dieTime == 0) {
                remove();
            }
        }
    }

    public void setDetonationSprite(Sprite s) {
        setSpriteSet("detonation", s);
    }

    private boolean isWallBlocked(double x, double z) {
        int x0 = (int) (Math.floor(x + radius));
        int z0 = (int) (Math.floor(z + radius));
        int x1 = (int) (Math.floor(x - radius));
        int z1 = (int) (Math.floor(z - radius));

        if (level.getBlock(x0, z0).isSolid) return true;
        if (level.getBlock(x1, z0).isSolid) return true;
        if (level.getBlock(x0, z1).isSolid) return true;
        if (level.getBlock(x1, z1).isSolid) return true;
        return false;
    }

    private Mob getBlockingMob(double x, double z) {
        for (int i = 0; i < level.countMobs(); i++) {
            Mob e = level.getMobEntity(i);
            if (e.isSolid) {
                double entX = e.posX;
                double entZ = e.posZ;
                double entRadius = e.radius;
                if (((Math.abs(x - entX)) - entRadius < radius) && ((Math.abs(z - entZ)) - entRadius < radius)) {
                    return e;
                }
            }
        }
        return null;
    }

    public void detonate() {
        runSpriteSet("detonation");
        isDetonating = true;
    }
}

package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

import java.util.List;

public class Projectile extends Entity {
    protected double moveSpeed = 0.25;
    protected int life = 1200;

    public double detonationRadius = 2;
    public int detonationMagnitude = 1;

    public Projectile() {
        radius = 0.1;
        isSolid = false;

        setIdleSprite(new Sprite(Texture.poof0));
    }

    public void tick() {
        super.tick();

        life -= 1;
        if (life <= 0) {
            remove();
        } else {
            double nextX = moveSpeed * Math.sin(rotation);
            double nextZ = moveSpeed * Math.cos(rotation);

            if (isWallBlocked(posX + nextX, posZ) || isEntityBlocked(posX + nextX, posZ)) {
                detonate();
                return;
            }
            posX += nextX;

            if (isWallBlocked(posX, posZ + nextZ) || isEntityBlocked(posX, posZ + nextZ)) {
                detonate();
                return;
            }
            posZ += nextZ;
        }
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

    private boolean isEntityBlocked(double x, double z) {
        for (int i = 0; i < level.countEntities(); i++) {
            Entity e = level.getEntity(i);
            if (e.isSolid) {
                double entX = e.posX;
                double entZ = e.posZ;
                double entRadius = e.radius;
                if (level.getEntity(i) != this) {
                    if (((Math.abs(x - entX)) - entRadius < radius) && ((Math.abs(z - entZ)) - entRadius < radius)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void detonate() {
        List<Mob> mobs = getMobsInRadius(detonationRadius);

        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).hurt(this, detonationMagnitude);
        }

        for (int i = 0; i < 6; i++) {
            level.addEntity(new PoofParticle(posX, posZ));
        }
        this.remove();
    }
}

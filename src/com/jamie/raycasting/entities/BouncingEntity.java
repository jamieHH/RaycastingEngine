package com.jamie.raycasting.entities;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Sprite;

public class BouncingEntity extends Entity
{
    protected double moveX, moveZ;
    protected double moveSpeed;
    protected String direction;
    protected int damage;


    public BouncingEntity(Sprite sprite, String direction, double moveSpeed, int damage) {
        setIdleSprite(sprite);

        this.direction = direction;
        this.moveSpeed = moveSpeed;
        this.damage = damage;
//        isSolid = false;
    }

    public void tick() {
        super.tick();

        if (direction.equals("N")) {
            moveX = 0;
            moveZ = moveSpeed;
        } else if (direction.equals("S")) {
            moveX = 0;
            moveZ = -moveSpeed;
        } else if (direction.equals("E")) {
            moveX = moveSpeed;
            moveZ = 0;
        } else if (direction.equals("W")) {
            moveX = -moveSpeed;
            moveZ = 0;
        }

        int divs = 10;
        for (int i = 0; i < divs; i++) {
            double nextX = moveX / divs;
            Entity entity = getBlockingEntity(posX + nextX, posZ);
            if (entity != null) {
                if (entity instanceof Mob) {
                    System.out.println("hitx");
                    ((Mob) entity).hurt(this, damage);
                } else if (entity instanceof BarrelEntity) {
                    ((BarrelEntity) entity).smash();
                }
            }
            if (isWallBlocked(posX + nextX, posZ)) {
                direction = negativeDirection(direction);
                nextX *= -1;
            }
            posX += nextX;

            double nextZ = moveZ / divs;
            entity = getBlockingEntity(posX, posZ + nextZ);
            if (entity != null) {
                if (entity instanceof Mob) {
                    System.out.println("hitz");
                    ((Mob) entity).hurt(this, damage);
                } else if (entity instanceof BarrelEntity) {
                    ((BarrelEntity) entity).smash();
                }
            }
            if (isWallBlocked(posX, posZ + nextZ)) {
                direction = negativeDirection(direction);
                nextZ *= -1;
            }
            posZ += nextZ;
        }
    }

    private String negativeDirection(String direction) {
        switch(direction) {
            case "N":
                return "S";
            case "S":
                return "N";
            case "E":
                return "W";
            case "W":
                return "E";
            default:
                return null;
        }
    }

    private Entity getBlockingEntity(double x, double z) {
        for (Entity entity : level.getEntities()) {
            if (entity.isSolid && entity != this) {
                double entX = entity.posX;
                double entZ = entity.posZ;
                double entRadius = entity.radius;
                if (((Math.abs(x - entX)) - entRadius < radius) && ((Math.abs(z - entZ)) - entRadius < radius)) {
                    System.out.println(entity);
                    return entity;
                }
            }
        }
        return null;
    }

    private boolean isWallBlocked(double x, double z) {
        int x0 = (int) (x + radius);
        int z0 = (int) (z + radius);
        int x1 = (int) (x - radius);
        int z1 = (int) (z - radius);

        if (level.getBlock(x0, z0).isSolid) return true;
        if (level.getBlock(x1, z0).isSolid) return true;
        if (level.getBlock(x0, z1).isSolid) return true;
        if (level.getBlock(x1, z1).isSolid) return true;

        if (!this.isFloating) {
            if (!level.getBlock(x0, z0).isWalkable) return true;
            if (!level.getBlock(x1, z0).isWalkable) return true;
            if (!level.getBlock(x0, z1).isWalkable) return true;
            if (!level.getBlock(x1, z1).isWalkable) return true;
        }
        return false;
    }
}

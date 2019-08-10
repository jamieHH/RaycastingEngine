package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.projectiles.FireballProjectile;
import com.jamie.raycasting.entities.projectiles.Projectile;
import com.jamie.raycasting.graphics.Texture;

public class TurretBlock extends TriggerableBlock
{
    protected boolean isActive = true;
    protected int fireWait = 30;
    protected int fireTicks = 0;
    protected String direction;


    private Projectile getProjectile() {
        return new FireballProjectile(1, 1);
    }

    public TurretBlock(String direction) {
        isOpaque = true;
        isSolid = true;

        wallTex = Texture.wall;
        floorTex = Texture.floor;
        ceilTex = Texture.floor;

        this.direction = direction;
    }

    public void trigger() {
        isActive = !isActive;
    }

    public void tick() {
        if (isActive) {
            if (fireTicks > 0) {
                fireTicks--;
            } else {
                Projectile projectile = getProjectile();
                if (direction.equals("N")) {
                    projectile.setRotation(4.71239);
                    level.addEntity(projectile, gridX - 0.5, gridZ + 0.5);
                } else if (direction.equals("S")) {
                    projectile.setRotation(1.5708);
                    level.addEntity(projectile, gridX + 1.5, gridZ + 0.5);
                } else if (direction.equals("E")) {
                    projectile.setRotation(0);
                    level.addEntity(projectile, gridX + 0.5, gridZ + 1.5);
                } else if (direction.equals("W")) {
                    projectile.setRotation(3.14159);
                    level.addEntity(projectile, gridX + 0.5, gridZ - 0.5);
                }

                fireTicks = fireWait;
            }
        }
    }
}

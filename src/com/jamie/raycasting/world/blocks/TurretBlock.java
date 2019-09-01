package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.projectiles.FireballProjectile;
import com.jamie.raycasting.entities.projectiles.Projectile;
import com.jamie.raycasting.graphics.Texture;

public class TurretBlock extends TriggerableBlock
{
    private boolean isActive = true;
    private boolean isFiring = true;
    private String direction;
    private static final int fireWait = 30;
    private int fireTicks = 0;

    private static final int texWait = 10;
    private int texTicks = 0;


    private Projectile getProjectile() {
        return new FireballProjectile(1, 1);
    }

    public TurretBlock(String direction) {
        isOpaque = true;
        isSolid = true;

        wallTex = Texture.wallBoltSwitch0;
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
                emitSound(Sound.clickAction);
                isFiring = true;
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
        } else {
            fireTicks = 0;
        }

        if (isFiring) {
            if (texTicks > 0) {
                texTicks--;
            } else {
                isFiring = false;
                texTicks = texWait;
            }

            wallTex = Texture.wallBoltSwitch1;
        } else {
            wallTex = Texture.wallBoltSwitch0;
        }
    }
}

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

    private static final int TEX_WAIT = 10;
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

    public void tick() {
        if (isFiring) {
            if (texTicks > 0) {
                texTicks--;
            } else {
                isFiring = false;
                texTicks = TEX_WAIT;
            }

            wallTex = Texture.wallBoltSwitch1;
        } else {
            wallTex = Texture.wallBoltSwitch0;
        }
    }

    public void trigger() {
        emitSound(Sound.clickAction);
        isFiring = true;
        Projectile projectile = getProjectile();
        switch (direction) {
            case "N":
                projectile.setRotation(4.71239);
                level.addEntity(projectile, gridX - 0.5, gridZ + 0.5);
                break;
            case "S":
                projectile.setRotation(1.5708);
                level.addEntity(projectile, gridX + 1.5, gridZ + 0.5);
                break;
            case "E":
                projectile.setRotation(0);
                level.addEntity(projectile, gridX + 0.5, gridZ + 1.5);
                break;
            case "W":
                projectile.setRotation(3.14159);
                level.addEntity(projectile, gridX + 0.5, gridZ - 0.5);
                break;
        }
    }

    public boolean getState() {
        return isActive;
    }

    public void setState(boolean state) {
        isActive = state;
    }
}

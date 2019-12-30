package com.jamie.raycasting.entities;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.WoodParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BarrelEntity extends Entity
{
    private Entity drop;
    private int quantity;

    public BarrelEntity(Entity drop, int quantity) {
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    public BarrelEntity(Mob drop) {
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    protected Sprite getSprite() {
        return new Sprite(new Bitmap[] {
                Texture.barrel0
        });
    }

    public boolean use(Mob source) {
        emitSound(Sound.smash);
        level.addEntity(drop, posX, posZ);
        level.addEntity(new WoodParticle(4), posX, posZ);
        remove();
        return true;
    }
}

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
    private boolean used = false;

    public BarrelEntity(Entity drop) {
        setupSprites();
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    public BarrelEntity(Mob drop) {
        setupSprites();
        this.isSolid = true;
        this.radius = 0.25;
        this.drop = drop;
    }

    private void setupSprites() {
        Bitmap[] ts0 = {
                Texture.barrel0,
        };
        setIdleSprite(new Sprite(ts0));

        Bitmap[] ts1 = {
                Texture.barrel1,
        };
        setSpriteSet("broken", new Sprite(ts1));
    }

    public boolean use(Mob source) {
        if (!used) {
            used = true;
            isSolid = false;
            emitSound(Sound.smash);
            switchSpriteSet("broken");
            level.addEntity(new WoodParticle(4), posX, posZ);
            level.addEntity(drop, posX, posZ);
            return true;
        }

        return false;
    }

    protected Sprite getSprite() {
        return null; // gets overwritten in setupSprites
    }
}

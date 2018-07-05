package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class ArrowProjectile extends Projectile
{
    public ArrowProjectile(int damage) {
        super(damage);

        setIdleSprite(new Sprite(Texture.boltProjectile0));

        Render[] ts = {
                Texture.invisible,
                Texture.invisible,
                Texture.invisible,
                Texture.invisible
        };

        setDetonationSprite(new Sprite(ts));
    }
}

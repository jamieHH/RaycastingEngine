package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class ArrowProjectile extends Projectile
{
    public ArrowProjectile() {
        damage = 2;

        setIdleSprite(new Sprite(Texture.boltProjectile0));

        setDetonationSprite(new Sprite(Texture.invisible, 20));
    }
}

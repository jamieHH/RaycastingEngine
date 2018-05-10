package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteParticle;
import com.jamie.raycasting.graphics.Texture;

public class ArrowProjectile extends Projectile
{
    public ArrowProjectile() {
        damage = 2;

        setIdleSprite(new Sprite(Texture.poof0)); // TODO: arrow texture make

        SpriteParticle t = new SpriteParticle(Texture.poof0, 20);
        t.y = 0.5;

        setDetonationSprite(t);
    }
}

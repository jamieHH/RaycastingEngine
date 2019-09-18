package com.jamie.raycasting.entities.projectiles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;

public class ArrowProjectile extends Projectile
{
    protected Sprite getSprite() {
        Bitmap r = new Bitmap(1, 1);
        r.fill(0xF8F8F8);
        Sprite s = new Sprite(r);
        s.setOffset(0, 0.5, 0);
        return s;
    }


    public ArrowProjectile(int damage) {
        super(damage);

        Bitmap[] ts = {
                null,
                null,
                null,
                null,
        };
        setDetonationSprite(new Sprite(ts));
    }
}

package com.jamie.raycasting.entities.projectiles;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.graphics.Sprite;

public class ArrowProjectile extends Projectile
{
    public ArrowProjectile(int damage) {
        super(damage);

        Bitmap r = new Bitmap(1, 1);
        r.fill(0xF8F8F8);
        Sprite s = new Sprite(r);
        s.setOffset(0, 0.5, 0);
        setIdleSprite(s);

        Bitmap[] ts1 = {
                null,
                null,
                null,
                null,
        };
        setDetonationSprite(new Sprite(ts1));
    }

    public void detonate() {
        super.detonate();
        emitSound(Sound.clickAction);
    }
}

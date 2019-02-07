package com.jamie.raycasting.entities.projectiles;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class ArrowProjectile extends Projectile
{
    protected Sprite setSprite() {
        Render r = new Render(1, 1);
        r.fill(0xF8F8F8);
        Sprite s = new Sprite(r);
        s.setOffset(0, 0.5, 0);
        return s;
    }


    public ArrowProjectile(int damage) {
        super(damage);

        Render[] ts = {
                Texture.invisible,
                Texture.invisible,
                Texture.invisible,
                Texture.invisible
        }; // TODO: fix this nonsense ffs
        setDetonationSprite(new Sprite(ts));
    }
}

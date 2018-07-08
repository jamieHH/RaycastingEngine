package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.entities.projectiles.FireballProjectile;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class WandWeapon extends Weapon
{
    public WandWeapon() {
        super();

        canStrike = false;

        setIdleSprite(new Sprite(Texture.screenWand0));

        Render[] ts = {
                Texture.screenWand1,
                Texture.screenWand1
        };

        setUseSprite(new Sprite(ts));

        name = "Wand";
        damage = 4;
    }

    public void use() {
        super.use();

        double nextX = Math.sin(user.rotation);
        double nextZ = Math.cos(user.rotation);

        FireballProjectile f = new FireballProjectile(2, damage);
        f.setRotation(user.rotation);

        user.level.addEntity(f, user.posX + nextX, user.posZ + nextZ);
    }
}

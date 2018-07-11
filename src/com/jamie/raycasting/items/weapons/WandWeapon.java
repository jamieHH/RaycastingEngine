package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.entities.projectiles.FireballProjectile;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class WandWeapon extends Weapon
{
    public WandWeapon() {
        super();

        name = "Wand";
        damage = 3;
        canStrike = false;

        icon = Texture.wandIcon;

        Render[] ts = {
                Texture.screenWand0,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts1 = {
                Texture.screenWand1,
                Texture.screenWand1
        };
        setUseSprite(new Sprite(ts1));
    }

    public void use() {
        super.use();

        double nextX = Math.sin(user.rotation);
        double nextZ = Math.cos(user.rotation);

        FireballProjectile p = new FireballProjectile(1, damage);
        p.setRotation(user.rotation);

        user.level.addEntity(p, user.posX + nextX, user.posZ + nextZ);
    }
}

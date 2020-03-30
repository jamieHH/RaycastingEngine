package com.jamie.raycasting.items.weapons;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.projectiles.FireballProjectile;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SuperWandWeapon extends Weapon
{
    public SuperWandWeapon() {
        super();

        name = "Super Wand";
        damage = 3;
        reach = 0;
        useWait = 2;
        canStrike = false;

        icon = Texture.wandIcon;

        Bitmap[] ts = {
                Texture.guiWand0,
        };
        setIdleSprite(new Sprite(ts));

        Bitmap[] ts1 = {
                Texture.screenWand1,
                Texture.screenWand2,
                Texture.screenWand3,
                Texture.screenWand4,
        };
        setUseSprite(new Sprite(ts1));
    }

    public void use() {
        super.use();

        double nextX = Math.sin(user.getRotation());
        double nextZ = Math.cos(user.getRotation());

        FireballProjectile p = new FireballProjectile(1, damage);
        p.setRotation(user.getRotation());

        user.level.addEntity(p, user.posX + nextX, user.posZ + nextZ);
    }
}

package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.entities.projectiles.ArrowProjectile;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class XbowWeapon extends Weapon
{
    public XbowWeapon() {
        super();

        name = "Crossbow";
        damage = 2;
        canStrike = false;

        icon = Texture.xbowIcon;

        Render[] ts = {
                Texture.screenXbow0,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts1 = {
                Texture.screenXbow1,
                Texture.screenXbow1
        };
        setUseSprite(new Sprite(ts1));
    }

    public void use() {
        super.use();

        double nextX = Math.sin(user.rotation);
        double nextZ = Math.cos(user.rotation);

        ArrowProjectile f = new ArrowProjectile(damage);
        f.setRotation(user.rotation);

        user.level.addEntity(f, user.posX + nextX, user.posZ + nextZ);
    }
}

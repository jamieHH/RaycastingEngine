package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.entities.projectiles.ArrowProjectile;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class XbowWeapon extends Weapon
{
    public XbowWeapon() {
        super();

        canStrike = false;

        setIdleSprite(new Sprite(Texture.screenXbow0));

        setUseSprite(new Sprite(Texture.screenXbow1));

        name = "Crossbow";
        damage = 2;
    }

    public void use() {
        super.use();

        double nextX = Math.sin(user.rotation);
        double nextZ = Math.cos(user.rotation);

        ArrowProjectile f = new ArrowProjectile(damage);
        f.setPosition(user.posX + nextX, user.posZ + nextZ);
        f.setRotation(user.rotation);

        user.level.addEntity(f);
    }
}

package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpearWeapon extends Weapon
{
    public SpearWeapon() {
        super();

        idleSprite = new Sprite(Texture.screenSpear0);
        useSprite = new Sprite(Texture.screenSpear1);

        name = "Spear";
        reach = 16;
    }
}

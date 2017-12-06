package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpearWeapon extends Weapon
{
    public Sprite idleSprite = new Sprite(Texture.screenSpear0);
    public Sprite useSprite = new Sprite(Texture.screenSpear1);

    public SpearWeapon() {
        super();

        name = "Spear";
        reach = 16;
    }
}

package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class AxeWeapon extends Weapon
{
    public AxeWeapon() {
        super();

        idleSprite = new Sprite(Texture.screenAxe0);
        useSprite = new Sprite(Texture.screenAxe1);

        name = "Hatchet";
        reach = 4;
    }
}

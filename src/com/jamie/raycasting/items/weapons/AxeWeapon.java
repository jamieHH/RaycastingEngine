package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class AxeWeapon extends Weapon
{
    public Sprite idleSprite = new Sprite(Texture.screenSpear0);
    public Sprite useSprite = new Sprite(Texture.screenSpear1);

    public AxeWeapon() {
        super();

        name = "Hatchet";
        reach = 4;
    }
}

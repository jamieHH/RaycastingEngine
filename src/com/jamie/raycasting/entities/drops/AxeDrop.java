package com.jamie.raycasting.entities.drops;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.weapons.AxeWeapon;

public class AxeDrop extends Drop
{
    public AxeDrop(double x, double z) {
        super(x, z);
        item = new AxeWeapon();
        addSprite(new Sprite(Texture.blood0));
    }
}

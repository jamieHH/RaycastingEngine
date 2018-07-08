package com.jamie.raycasting.entities.drops;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.weapons.AxeWeapon;

public class AxeDrop extends Drop
{
    public AxeDrop() {
        super();
        item = new AxeWeapon();
        setIdleSprite(new Sprite(Texture.blood0));
    }
}

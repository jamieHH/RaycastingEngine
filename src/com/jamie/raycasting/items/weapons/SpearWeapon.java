package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpearWeapon extends Weapon
{
    public SpearWeapon() {
        super();

        Render[] ts = {
                Texture.screenSpear0,
        };
        setIdleSprite(new Sprite(ts));


        Render[] ts1 = {
            Texture.screenSpear1,
            Texture.screenSpear2
        };

        setUseSprite(new Sprite(ts1));

        name = "Spear";
        reach = 16;
    }
}

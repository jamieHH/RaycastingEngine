package com.jamie.raycasting.items.weapons;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class AxeWeapon extends Weapon
{
    public AxeWeapon() {
        super();

        name = "Axe";
        damage = 3;
        reach = 1;
        useWait = 30;

        icon = Texture.axeIcon;

        Bitmap[] ts = {
                Texture.guiAxe0,
        };
        setIdleSprite(new Sprite(ts));

        Bitmap[] ts1 = {
                Texture.guiAxe1,
                Texture.guiAxe2,
                null,
                null,
                null,
                null
        };
        setUseSprite(new Sprite(ts1));
    }
}

package com.jamie.raycasting.items.weapons;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpearWeapon extends Weapon
{
    public SpearWeapon() {
        super();

        name = "Spear";
        damage = 2;
        reach = 2;
        useWait = 20;

        icon = Texture.spearIcon;

        Bitmap[] ts = {
                Texture.guiSpear0,
        };
        setIdleSprite(new Sprite(ts));

        Bitmap[] ts1 = {
            Texture.guiSpear1,
            Texture.giuSpear2
        };
        setUseSprite(new Sprite(ts1));
    }
}

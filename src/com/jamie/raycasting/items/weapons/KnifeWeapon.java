package com.jamie.raycasting.items.weapons;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class KnifeWeapon extends Weapon
{
    public KnifeWeapon() {
        super();

        name = "Knife";
        damage = 1;
        reach = 0;
        useWait = 15;

        icon = Texture.knifeIcon;

        Bitmap[] ts = {
                Texture.guiDagger0,
        };
        setIdleSprite(new Sprite(ts));

        Bitmap[] ts1 = {
                Texture.guiDagger1,
                Texture.guiDagger2,
        };
        setUseSprite(new Sprite(ts1));
    }
}

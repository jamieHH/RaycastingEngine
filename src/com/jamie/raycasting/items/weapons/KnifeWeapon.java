package com.jamie.raycasting.items.weapons;

import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.jamapp.Texture;

public class KnifeWeapon extends Weapon
{
    public KnifeWeapon() {
        super();

        name = "Knife";
        damage = 1;
        reach = 0;
        useWait = 15;

        icon = Texture.knifeIcon;

        Render[] ts = {
                Texture.screenKnife0,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts1 = {
                Texture.screenKnife1,
                Texture.screenKnife2,
        };
        setUseSprite(new Sprite(ts1));
    }
}

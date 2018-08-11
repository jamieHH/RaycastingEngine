package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class KnifeWeapon extends Weapon
{
    public KnifeWeapon() {
        super();

        name = "Knife";
        damage = 1;
        reach = 1;

        icon = Texture.knifeIcon;

        Render[] ts = {
                Texture.screenKnife0,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts1 = {
                Texture.screenKnife1,
        };
        setUseSprite(new Sprite(ts1));
    }
}

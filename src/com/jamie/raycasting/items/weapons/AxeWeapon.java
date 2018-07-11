package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class AxeWeapon extends Weapon
{
    public AxeWeapon() {
        super();

        name = "Hatchet";
        damage = 3;
        reach = 1;

        icon = Texture.axeIcon;

        Render[] ts = {
                Texture.screenAxe0,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts1 = {
                Texture.screenAxe1,
        };
        setUseSprite(new Sprite(ts1));
    }
}

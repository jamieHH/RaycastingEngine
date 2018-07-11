package com.jamie.raycasting.items.weapons;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class PlanksWeapon extends Weapon
{
    public PlanksWeapon() {
        super();

        name = "Planks";
        damage = 1;
        reach = 1;

        icon = Texture.planksIcon;

        Render[] ts = {
                Texture.screenPlank0,
        };
        setIdleSprite(new Sprite(ts));

        Render[] ts1 = {
                Texture.screenPlank1,
        };
        setUseSprite(new Sprite(ts1));
    }
}

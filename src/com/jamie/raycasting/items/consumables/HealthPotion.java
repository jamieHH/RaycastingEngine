package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class HealthPotion extends Consumable
{
    public HealthPotion() {
        super();

        Render[] ts = {
                Texture.screenHealthPotion0,
        };
        addIdleSprite(new Sprite(ts));


        Render[] ts1 = {
                Texture.screenHealthPotion1,
        };
        addUseSprite(new Sprite(ts1));

        name = "Health Potion";
    }

    public void use() {
        super.use();

        user.addHealth(8);
    }
}

package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModSpeedEffect;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class SpeedPotion extends Consumable
{
    public SpeedPotion() {
        super();

        Render[] ts = {
                Texture.screenHealthPotion0,
        };
        setIdleSprite(new Sprite(ts));


        Render[] ts1 = {
                Texture.screenHealthPotion1,
        };
        setUseSprite(new Sprite(ts1));

        name = "Speed Potion";
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModSpeedEffect(user, 240, 100));
    }
}

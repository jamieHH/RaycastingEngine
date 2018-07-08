package com.jamie.raycasting.entities.drops;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.consumables.HealthPotion;

public class HealthPotionDrop extends Drop
{
    public HealthPotionDrop() {
        super();
        item = new HealthPotion();
        setIdleSprite(new Sprite(Texture.blood0));
    }
}

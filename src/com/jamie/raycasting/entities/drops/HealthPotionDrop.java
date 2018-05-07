package com.jamie.raycasting.entities.drops;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.consumables.HealthPotion;

public class HealthPotionDrop extends Drop
{
    public HealthPotionDrop(double x, double z) {
        super(x, z);
        item = new HealthPotion();
        setIdleSprite(new Sprite(Texture.blood0));
    }
}

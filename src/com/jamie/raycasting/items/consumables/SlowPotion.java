package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModSpeedEffect;
import com.jamie.raycasting.graphics.Texture;

public class SlowPotion extends Consumable
{
    public SlowPotion() {
        super();

        name = "Slow Potion";
        duration = 640;
        magnitude = -3;

        icon = Texture.greenPotionIcon;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModSpeedEffect(user, duration, magnitude));
    }
}

package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;
import com.jamie.jamapp.Texture;

public class HealthPotion extends Consumable
{
    public HealthPotion() {
        super();

        name = "Health Potion";
        duration = 480;
        magnitude = 1;

        icon = Texture.redPotionIcon;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModHealthEffect(user, duration, magnitude));
    }
}

package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;
import com.jamie.raycasting.graphics.Texture;

public class HealthPotion extends Consumable
{
    public HealthPotion() {
        super();

        icon = Texture.potionIcon;

        name = "Health Potion";
        duration = 480;
        magnitude = 1;
    }

    public void use() {
        super.use();
        user.addMobEffect(new ModHealthEffect(user, duration, magnitude));
    }
}

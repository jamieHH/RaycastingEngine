package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModSpeedEffect;

public class SlowPotion extends Consumable
{
    public SlowPotion() {
        super();

        name = "Slow Potion";
        duration = 640;
        magnitude = -3;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModSpeedEffect(user, duration, magnitude));
    }
}

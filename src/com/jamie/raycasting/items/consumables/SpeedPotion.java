package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModSpeedEffect;

public class SpeedPotion extends Consumable
{
    public SpeedPotion() {
        super();

        name = "Speed Potion";
        duration = 920;
        magnitude = 3;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModSpeedEffect(user, duration, magnitude));
    }
}

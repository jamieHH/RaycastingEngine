package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModSpeedEffect;

public class SpeedPotion extends Consumable
{
    public SpeedPotion() {
        super();

        name = "Speed Potion";
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModSpeedEffect(user, 240, 100));
    }
}

package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;

public class HealthPotion extends Consumable
{
    public HealthPotion() {
        super();

        name = "Health Potion";
    }

    public void use() {
        super.use();
        user.addMobEffect(new ModHealthEffect(user, 240, 1));
    }
}

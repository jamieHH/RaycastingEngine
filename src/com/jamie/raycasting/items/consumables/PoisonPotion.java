package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;

public class PoisonPotion extends Consumable
{
    public PoisonPotion() {
        super();

        name = "Poison Potion";
        duration = 240;
        magnitude = -1;
    }

    public void use() {
        super.use();
        user.addMobEffect(new ModHealthEffect(user, duration, magnitude));
    }
}

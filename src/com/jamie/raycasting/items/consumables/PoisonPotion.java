package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;

public class PoisonPotion extends Consumable
{
    public PoisonPotion() {
        super();

        name = "Poison Potion";
    }

    public void use() {
        super.use();
        user.addMobEffect(new ModHealthEffect(user, 240, -1));
    }
}

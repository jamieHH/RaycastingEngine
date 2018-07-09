package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;
import com.jamie.raycasting.graphics.Texture;

public class PoisonPotion extends Consumable
{
    public PoisonPotion() {
        super();

        icon = Texture.potionIcon;

        name = "Poison Potion";
        duration = 240;
        magnitude = -1;
    }

    public void use() {
        super.use();
        user.addMobEffect(new ModHealthEffect(user, duration, magnitude));
    }
}

package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModDamageEffect;
import com.jamie.raycasting.graphics.Texture;

public class StrengthPotion extends Consumable
{
    public StrengthPotion() {
        super();

        icon = Texture.potionIcon;

        name = "Strength Potion";
        duration = 920;
        magnitude = 4;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModDamageEffect(user, duration, magnitude));
    }
}

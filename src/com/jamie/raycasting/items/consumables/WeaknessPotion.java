package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModDamageEffect;
import com.jamie.jamapp.Texture;

public class WeaknessPotion extends Consumable
{
    public WeaknessPotion() {
        super();

        name = "Weakness Potion";
        duration = 920;
        magnitude = -4;

        icon = Texture.greenPotionIcon;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModDamageEffect(user, duration, magnitude));
    }
}

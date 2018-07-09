package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModDamageEffect;
import com.jamie.raycasting.graphics.Texture;

public class WeaknessPotion extends Consumable
{
    public WeaknessPotion() {
        super();

        icon = Texture.potionIcon;

        name = "Weakness Potion";
        duration = 920;
        magnitude = -4;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModDamageEffect(user, duration, magnitude));
    }
}

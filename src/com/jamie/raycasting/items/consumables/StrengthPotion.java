package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModDamageEffect;
import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Texture;

public class StrengthPotion extends Consumable
{
    public StrengthPotion() {
        super();

        name = "Strength Potion";
        duration = 920;
        magnitude = 4;

        icon = Texture.yellowPotionIcon;
        dropSprite = new DropSprite(Texture.yellowPotion);
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModDamageEffect(user, duration, magnitude));
    }
}

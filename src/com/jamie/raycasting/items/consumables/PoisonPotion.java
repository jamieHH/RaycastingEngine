package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;
import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Texture;

public class PoisonPotion extends Consumable
{
    public PoisonPotion() {
        super();

        name = "Poison Potion";
        duration = 240;
        magnitude = -1;

        icon = Texture.greenPotionIcon;
        dropSprite = new DropSprite(Texture.greenPotion);
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModHealthEffect(user, duration, magnitude));
    }
}

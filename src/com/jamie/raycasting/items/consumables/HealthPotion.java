package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;
import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Texture;

public class HealthPotion extends Consumable
{
    public HealthPotion() {
        super();

        name = "Health Potion";
        duration = 480;
        magnitude = 1;

        icon = Texture.redPotionIcon;
        dropSprite = new DropSprite(Texture.redPotion);
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModHealthEffect(user, duration, magnitude));
    }
}

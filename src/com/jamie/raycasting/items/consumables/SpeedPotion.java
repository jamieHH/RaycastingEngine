package com.jamie.raycasting.items.consumables;

import com.jamie.raycasting.entities.mobs.mobEffects.ModSpeedEffect;
import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Texture;

public class SpeedPotion extends Consumable
{
    public SpeedPotion() {
        super();

        name = "Speed Potion";
        duration = 920;
        magnitude = 3;

        icon = Texture.bluePotionIcon;
        dropSprite = new DropSprite(Texture.bluePotion);
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModSpeedEffect(user, duration, magnitude));
    }
}

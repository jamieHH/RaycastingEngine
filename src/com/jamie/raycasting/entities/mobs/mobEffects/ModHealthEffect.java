package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Texture;

public class ModHealthEffect extends MobEffect
{
    public ModHealthEffect(Mob mob, int duration, int magnitude) {
        super(mob, duration, magnitude);

        name = "Mod Health";

        if (magnitude < 0) {
            effectHudColour = 0x408040;
            effectHudIcon = Texture.poisonIcon;
        } else {
            effectHudColour = 0xF0F040;
            effectHudIcon = Texture.heartIcon;
        }
    }

    public void effect() {
        mob.modHealth(magnitude);
    }
}

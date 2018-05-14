package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class ModHealthEffect extends MobEffect
{
    public ModHealthEffect(Mob mob, int duration, int magnitude) {
        super(mob, duration, magnitude);

        name = "Mod Health";
        effectHudColour = 0xF0F040;

        if (magnitude < 0) {
            effectHudIcon = Texture.poisonIcon;
        } else {
            effectHudIcon = Texture.heartIcon;
        }
    }

    public void effect() {
        mob.modHealth(magnitude);
    }

    public void deactivate() {
        // reset any mob variables changed to the original state
    }
}

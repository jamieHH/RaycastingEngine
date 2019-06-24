package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Texture;

public class ModDamageEffect extends MobEffect
{
    public ModDamageEffect(Mob mob, int duration, int magnitude) {
        super(mob, duration, magnitude);

        name = "Mod Damage";

        if (magnitude < 0) {
            effectHudColour = 0x408040;
            effectHudIcon = Texture.weaknessIcon;
        } else {
            effectHudColour = 0xF0F040;
            effectHudIcon = Texture.strengthIcon;
        }

        mob.modBaseDamage((double) magnitude);
    }

    public void deactivate() {
        mob.modBaseDamage((double) -magnitude);
    }
}

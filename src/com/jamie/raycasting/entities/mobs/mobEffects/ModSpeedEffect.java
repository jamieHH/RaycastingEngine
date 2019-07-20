package com.jamie.raycasting.entities.mobs.mobEffects;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class ModSpeedEffect extends MobEffect
{
    public ModSpeedEffect(Mob mob, int duration, int magnitude) {
        super(mob, duration, magnitude);

        name = "Mod Speed";

        if (magnitude < 0) {
            effectHudColour = 0x507000;
            effectHudIcon = Texture.slowIcon;
        } else {
            effectHudColour = 0x4040F0;
            effectHudIcon = Texture.speedIcon;
        }

        mob.modSpeed((double) magnitude / 100.0);
    }

    public void deactivate() {
        mob.modSpeed((double) -magnitude / 100.0);
    }
}

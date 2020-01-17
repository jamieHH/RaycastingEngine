package com.jamie.raycasting.items.specials;

import com.jamie.raycasting.entities.mobs.mobEffects.ModHealthEffect;

public class HealingSpell extends Spell
{
    public HealingSpell() {
        name = "Healing Spell";
        type = TYPE_SPELL;

        useWait = 240;
        canStrike = true;
    }

    public void use() {
        super.use();

        user.addMobEffect(new ModHealthEffect(user, 60, 2));
    }
}

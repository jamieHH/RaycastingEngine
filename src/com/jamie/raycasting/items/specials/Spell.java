package com.jamie.raycasting.items.specials;
import com.jamie.raycasting.items.Item;

public abstract class Spell extends Item
{

    public Spell() {
        super();
        type = TYPE_SPELL;
        canStrike = true;
    }
}

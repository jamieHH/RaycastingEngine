package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.consumables.HealthPotion;
import com.jamie.raycasting.items.weapons.KnifeWeapon;

public class PitLevel extends Level
{
	public PitLevel() {
        name = "Pit";
	}

    protected Bitmap getFloorTexture() {
        return Texture.dirt;
    }

    protected Bitmap getCeilingTexture() {
        return Texture.dirt;
    }

	protected void postCreate() {

    }

    public void switchLevel(Mob mob, int id) {
        if (id == 1) world.switchLevel(mob, "island", 4);
    }

    public void triggerBlock(int id) {
        if (id == 2) super.triggerBlock(15);
    }
}

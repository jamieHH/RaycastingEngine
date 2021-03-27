package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.consumables.HealthPotion;

public class CastleHallLevel extends Level
{
	public CastleHallLevel() {
	    name = "Castle Hall";
	}

    protected Bitmap getWallTexture() {
        return Texture.wall4;
    }

    protected void postCreate() {
        Drop d0 = new Drop(new HealthPotion());
        addEntity(d0, 10.5, 6.5);
    }

    public void switchLevel(Entity entity, String ref) {
        if (ref.equals("1010")) world.switchLevel(entity, "island", "2017");
        if (ref.equals("1306")) world.switchLevel(entity, "barracks", "0302");
    }
}

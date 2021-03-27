package com.jamie.raycasting.world.levels;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Texture;

public class SewerLevel extends Level
{
	public SewerLevel() {
	    name = "Sewer";
	}

    protected Bitmap getWallTexture() {
        return Texture.wall3;
    }

    protected void postCreate() {

    }

    public void switchLevel(Entity entity, String ref) {
        if (ref.equals("0813")) world.switchLevel(entity, "prison", "3015");
        if (ref.equals("3726")) world.switchLevel(entity, "island", "3544");
    }
}

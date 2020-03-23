package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class AreaAlertEntity extends Entity
{
    public String message;

    protected Sprite getDefaultSprite() {
        return new DropSprite(Texture.bag);
    }


    public AreaAlertEntity(String message) {
        radius = 1;
        isSolid = false;

        this.message = message;
    }
}

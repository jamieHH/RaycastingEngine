package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Texture;

public class AddLifeEntity extends Entity
{
    public AddLifeEntity() {
        setIdleSprite(new DropSprite(Texture.life0));

        radius = 1;
        isSolid = false;
    }


}
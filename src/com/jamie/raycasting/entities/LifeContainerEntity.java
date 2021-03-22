package com.jamie.raycasting.entities;

import com.jamie.raycasting.graphics.DropSprite;
import com.jamie.raycasting.graphics.Texture;

public class LifeContainerEntity extends Entity
{
    public LifeContainerEntity() {
        setIdleSprite(new DropSprite(Texture.life0));

        radius = 1;
        isSolid = false;
    }
}
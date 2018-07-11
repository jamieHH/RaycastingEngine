package com.jamie.raycasting.entities.drops;

import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Key;

public class KeyDrop extends Drop
{
    public KeyDrop(String name, String reference) {
        super();
        item = new Key(name, reference);
        setIdleSprite(new Sprite(Texture.blood0));
    }
}

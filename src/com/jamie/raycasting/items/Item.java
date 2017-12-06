package com.jamie.raycasting.items;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public abstract class Item
{
    public String name = "Item";
    public Sprite idleSprite = new Sprite(Texture.screenSpear0);
    public Sprite useSprite = new Sprite(Texture.screenSpear1);
    public int weight = 1;
    public int damage = 1;
    public int value = 0;
    public int reach = 0;

    public int useTicks = 0;
    public int useWait = 15;

    public Render icon = new Render(8, 8);

    public Item() {

    }

    public void tick() {
        if (useTicks > 0) useTicks--;
    }

    public void use() {
        useTicks = useWait;
    }

    public Render getTexture() {
        if (useTicks > 0) {
            return useSprite.getTexture(useSprite.index);
        }

        return idleSprite.getTexture(idleSprite.index);
    }
}

package com.jamie.raycasting.items;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;
import com.jamie.raycasting.graphics.Texture;

public abstract class Item
{
    public String name = "Item";
    private SpriteSet spriteSet = new SpriteSet();

    public int weight = 1;
    public int damage = 1;
    public int value = 0;
    public int reach = 0;

    public int useTicks = 0;
    public int useWait = 15;

    public Render icon = new Render(8, 8);

    public Item() {
        addIdleSprite(new Sprite(Texture.none));
        addUseSprite(new Sprite(Texture.none));
    }

    public void addSpriteSet(String name, Sprite sprite) {
        spriteSet.addSet(name, sprite);
    }

    public void addIdleSprite(Sprite s) {
        addSpriteSet("idle", s);
    }

    public void addUseSprite(Sprite s) {
        addSpriteSet("use", s);
    }

    public Sprite getSprite() {
        return spriteSet.getSprite();
    }

    public void tick() {
        spriteSet.tick();
        if (useTicks > 0) useTicks--;
    }

    public void use() {
        spriteSet.runSet("use");
        useTicks = useWait;
    }

    public Render render() {
        return getSprite().render();
    }
}

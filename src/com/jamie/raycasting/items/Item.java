package com.jamie.raycasting.items;

import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteSet;
import com.jamie.raycasting.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

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

    public void addSpriteSet(String name, List<Sprite> sprites) {
        spriteSet.addSet(name, sprites);
    }

    public void addIdleSprite(Sprite s) {
        List<Sprite> set = new ArrayList<Sprite>();
        set.add(s);
        addSpriteSet("idle", set);
    }

    public void addUseSprite(Sprite s) {
        List<Sprite> set = new ArrayList<Sprite>();
        set.add(s);
        addSpriteSet("use", set);
    }

    public List<Sprite> getSprites() {
        return spriteSet.getSprites();
    }

    public Sprite getSprite(int i) {
        return getSprites().get(i);
    }

    protected void runSpriteSet(String name) {
        spriteSet.runSet(name);
    }

    public void tick() {
        spriteSet.tick();
        if (useTicks > 0) useTicks--;
    }

    public void use() {
        runSpriteSet("use");

        useTicks = useWait;
    }

    public Render render() {
        return getSprite(0).render();
    }
}

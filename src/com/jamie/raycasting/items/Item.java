package com.jamie.raycasting.items;

import com.jamie.raycasting.graphics.Render;

public abstract class Item
{
    public String name = "Item";
    public int weight = 1;
    public int damage = 1;
    public int value = 0;
    public int reach = 0;

    public Render icon = new Render(8, 8);

    public Item() {

    }
}

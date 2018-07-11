package com.jamie.raycasting.items;

import com.jamie.raycasting.graphics.Texture;

import java.util.Map;

public class Key extends Item
{
    public String reference = "";

    public Key(String name, String reference) {
        super();

        this.name = name;
        this.reference = reference;
        type = "key";

        icon = Texture.keyIcon;
    }

    public Map<String, String> getInfo() {
        info = super.getInfo();
        info.put("reference", reference);
        return info;
    }
}

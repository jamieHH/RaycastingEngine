package com.jamie.raycasting.graphics;

import com.jamie.jamapp.Render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sprite
{
	public double x = 0;
	public double y = 0;
	public double z = 0;

	protected Render[] textures;
    private int index = 0;
    private int animTick = 0;
    protected static final int INTERVAL = 5;

    public Sprite(Render t) {
        textures = new Render[]{t};
    }

    public Sprite(Render[] ts) {
        textures = ts;
    }

    public void tick() {
        if (textures.length > 1) {
            if (animTick > 0) {
                animTick--;
            } else {
                index++;
                if (index == textures.length) {
                    index = 0;
                }

                animTick = INTERVAL;
            }
        }
    }

    public void setOffset(double xOffs, double yOffs, double zOffs) {
        x = xOffs;
        y = yOffs;
        z = zOffs;
    }

    public Render render() {
        if (textures.length > 0) {
            return textures[index];
        } else {
            return null;
        }
    }

    protected void reset() {
        index = 0;
        animTick = INTERVAL;
    }
}

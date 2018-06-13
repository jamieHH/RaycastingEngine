package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sprite
{
	public double x, y, z = 0;

	private boolean isAnimated = false;
	private List<Render> textures = new ArrayList<Render>();
    private int index = 0;
    private int animTick = 0;
    protected int interval = 5;

	public Sprite(Render t, double xOffs, double yOffs, double zOffs) {
		x = xOffs;
		y = yOffs;
		z = zOffs;
        addTexture(t);
	}

    public Sprite(Render t) {
        addTexture(t);
    }

    public Sprite(Render[] ts) {
        addTextures(ts);
        this.isAnimated = true;
    }

    public void tick() {
        if (isAnimated) {
            if (animTick > 0) {
                animTick--;
            } else {
                index++;
                if (index == countTextures()) {
                    index = 0;
                }

                animTick = interval;
            }
        }
    }

    public void setOffset(double xOffs, double yOffs, double zOffs) {
        x = xOffs;
        y = yOffs;
        z = zOffs;
    }

	public void addTexture(Render t) {
	    textures.add(t);
    }

    public void addTextures(Render[] ts) {
        Collections.addAll(textures, ts);
    }

    public int countTextures() {
        return textures.size();
    }

    public Render render() {
        return textures.get(index);
    }

    public void reset() {
        index = 0;
        animTick = interval;

    }
}

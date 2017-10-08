package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.List;

public class Sprite
{
	public double x, y, z;
	private int colour = 0x202020;

	private boolean isAnimated = false;
	private List<Render> textures = new ArrayList<Render>();
    public int index = 0;
    private int animTick = 0;
    private int interval = 5;

	public Sprite(Render t, double xOffs, double yOffs, double zOffs) {
		x = xOffs;
		y = yOffs;
		z = zOffs;
        addTexture(t);
	}

    public Sprite(Render t) {
        x = 0;
        y = 0;
        z = 0;
        addTexture(t);
    }

    public Sprite(Render[] ts, double xOffs, double yOffs, double zOffs) {
        x = xOffs;
        y = yOffs;
        z = zOffs;
        addTextures(ts);
        this.isAnimated = true;
    }

    public Sprite(Render[] ts) {
        x = 0;
        y = 0;
        z = 0;
        addTextures(ts);
        this.isAnimated = true;
    }

    public Sprite(Render[] ts, int interval, double xOffs, double yOffs, double zOffs) {
        x = xOffs;
        y = yOffs;
        z = zOffs;
        this.interval = interval;
        addTextures(ts);
        this.isAnimated = true;
    }

    public Sprite(Render[] ts, int interval) {
        x = 0;
        y = 0;
        z = 0;
        this.interval = interval;
        addTextures(ts);
        this.isAnimated = true;
    }

	public void addTexture(Render t) {
	    textures.add(t);
    }

    public void addTextures(Render[] ts) {
        for (int i = 0; i < ts.length; i++) {
            textures.add(ts[i]);
        }
    }

    public Render getTexture(int i) {
        return textures.get(i);
    }

    public int countTextures() {
        return textures.size();
    }

    public void clearTextures() {
        textures.clear();
    }

    public void tick() {
	    if (!isAnimated) {
	        return;
        }

        if (animTick > 0) {
            animTick--;
            return;
        }

        int i = index;
        i++;
        if (i >= countTextures()) {
            i = 0;
        }

        index = i;
        animTick = interval;
    }
}

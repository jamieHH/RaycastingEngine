package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sprite
{
	public double x, y, z;

	private boolean isAnimated = false;
	private List<Render> textures = new ArrayList<Render>();
    private int index = 0;
    private int animTick = 0;
    public int interval = 5;

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

    public Sprite(Render t, int interval) {
        x = 0;
        y = 0;
        z = 0;
        this.interval = interval;
        addTexture(t);
    }

    public Sprite(Render[] ts) {
        x = 0;
        y = 0;
        z = 0;
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

package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sprite
{
	public double x = 0;
	public double y = 0;
	public double z = 0;

	private boolean isAnimated = false;
	private List<Render> textures = new ArrayList<Render>();
    private int index = 0;
    private int animTick = 0;
    protected static final int INTERVAL = 5;

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

                animTick = INTERVAL;
            }
        }
    }

    public void setOffset(double xOffs, double yOffs, double zOffs) {
        x = xOffs;
        y = yOffs;
        z = zOffs;
    }

    public int countTextures() {
        return textures.size();
    }

    public Render render() {
        return textures.get(index);
    }

    protected void reset() {
        index = 0;
        animTick = INTERVAL;

    }

    private void addTexture(Render t) {
        textures.add(t);
    }

    private void addTextures(Render[] ts) {
        Collections.addAll(textures, ts);
    }
}

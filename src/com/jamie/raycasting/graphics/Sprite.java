package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.List;

public class Sprite
{
	public double x, y, z;
	public Render texture = Texture.noTex;
	private int colour = 0x202020;

	private boolean isAnimated = false;
	private List<Render> textures = new ArrayList<Render>();
    public int texIndex = 0;
    private int animTick = 0;
    private int interval = 5;

	public Sprite(Render tex, double xOffs, double yOffs, double zOffs) {
		this.x = xOffs;
		this.y = yOffs;
		this.z = zOffs;
		this.texture = tex;
	}

    public Sprite(Render tex) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.texture = tex;
    }

    public Sprite(Render[] texs) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        for (int i = 0; i < texs.length; i++) {
            textures.add(texs[i]);
        }
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

        int i = texIndex;
        i++;
        if (i >= countTextures()) {
            i = 0;
        }

        texIndex = i;
        animTick = interval;
    }
}

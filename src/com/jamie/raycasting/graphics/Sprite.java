package com.jamie.raycasting.graphics;

public class Sprite
{
	public double x, y, z;
	public Render texture = Texture.noTex;
	public int colour = 0x202020; // TODO: build color tint into sprite renderer

	public Sprite(Render texture, double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.texture = texture;
		this.colour = 0x202020;
	}

    public void tick() {

    }
}

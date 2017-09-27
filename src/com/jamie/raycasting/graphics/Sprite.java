package com.jamie.raycasting.graphics;

public class Sprite
{
	public double x, y, z;
	public Render texture = Texture.noTex;
	public int col = 0x202020; // TODO: build color tint into sprite renderer

	public Sprite(double x, double y, double z, Render texture) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.texture = texture;
		this.col = 0x202020;
	}

//    public Sprite(double x, double y, double z, Render texture, int col) {
//        this.x = x;
//        this.y = y;
//        this.z = z;
//        this.texture = texture;
//        this.col = col;
//    }

	public void tint(int col) {

    }

    public void tick() {

    }
}

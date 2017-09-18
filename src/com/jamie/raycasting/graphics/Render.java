package com.jamie.raycasting.graphics;

public class Render {
	public int width;
	public int height;
	public int[] pixels;

	private static final String chars = "" + //
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ.,!?\"'/\\<>()[]{}" + //
			"abcdefghijklmnopqrstuvwxyz_               " + //
			"0123456789+-=*:;                          " + //
			"";
	
	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void draw(Render render, int xOffs, int yOffs) {
	    // TODO: fix to render Texture static classes;
		for (int y = 0; y < render.height; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= height) continue;

			for (int x = 0; x < render.width; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= width) continue;

				int alpha = render.pixels[x + y * render.width];
				if (alpha > 0) {
					pixels[xPix + yPix * width] = alpha;
				}
			}
		}
	}

    public void draw(Render render, int xOffs, int yOffs, int xo, int yo, int w, int h, int col) {
        for (int y = 0; y < h; y++) {
            int yPix = y + yOffs;
            if (yPix < 0 || yPix >= height) continue;

            for (int x = 0; x < w; x++) {
                int xPix = x + xOffs;
                if (xPix < 0 || xPix >= width) continue;

                int src = -render.pixels[(x + xo) + (y + yo) * render.width];
                if (src != 65281) {
                    pixels[xPix + yPix * width] = src * col;
                }
            }
        }
    }

    public void draw(String string, int x, int y, int col) {
        for (int i = 0; i < string.length(); i++) {
            int ch = chars.indexOf(string.charAt(i));
            if (ch < 0) continue;

            int xx = ch % 42;
            int yy = ch / 42;
            draw(Texture.font, (x + i * 6) + 1, y + 1, xx * 6, yy * 8, 5, 8, 0x000000);
            draw(Texture.font, x + i * 6, y, xx * 6, yy * 8, 5, 8, col);
        }
    }
}

package com.jamie.jamapp;

public class Render
{
	public int width;
	public int height;
	public int[] pixels;
	public static final int INVISIBLE = 0;

	private static final String chars = "" +
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ.,!?\"'/\\<>()[]{}" +
			"abcdefghijklmnopqrstuvwxyz_               " +
			"0123456789+-=*:;                          " +
			"";
	public static final Render font = PngLoader.loadBitmap("/gui/font2.png");
	
	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void draw(Render render, int xOffs, int yOffs) {
		for (int y = 0; y < render.height; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= height) continue;

			for (int x = 0; x < render.width; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= width) continue;

				int alpha = render.pixels[x + y * render.width];
				if (alpha != INVISIBLE) {
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

                int alpha = render.pixels[(x + xo) + (y + yo) * render.width];
                if (alpha != INVISIBLE) {
                    pixels[xPix + yPix * width] = -alpha * col;
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
            draw(font, (x + i * 6) + 1, y + 1, xx * 6, yy * 8, 5, 8, 0x020202);
            draw(font, x + i * 6, y, xx * 6, yy * 8, 5, 8, col);
        }
    }

    public void fill(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public void fill(int x0, int y0, int x1, int y1, int color) {
		for (int y = y0; y < y1; y++) {
			if (y < 0 || y >= height) continue;
			for (int x = x0; x < x1; x++) {
				if (x < 0 || x >= width) continue;
				pixels[x + y * width] = color;
			}
		}
	}

	public void box(int x0, int y0, int x1, int y1, int color) {
		fill(x0, y0, x1, 1, color);
		fill(x0, y0, 1, y1, color);
		fill(x1 - 1, 1, x1, y1, color);
		fill(1, y1 - 1, x1, y1, color);
	}
}

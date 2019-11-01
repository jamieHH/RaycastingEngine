package com.jamie.jamapp;

public class Bitmap
{
	public int width;
	public int height;
	public int[] pixels;
	public static final int INVISIBLE = 0;

	public static boolean IS_SMALL_FONT = false;

	private static final String chars = "" +
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ.,!?\"'/\\<>()[]{}" +
			"abcdefghijklmnopqrstuvwxyz_               " +
			"0123456789+-=*:;                          " +
			"";

	public Bitmap(int width, int height) {
		setSize(width, height);
	}

	private static Bitmap getFont() {
        if (IS_SMALL_FONT) {
            return PngLoader.loadBitmap("/gui/fontS.png");
        }

        return PngLoader.loadBitmap("/gui/font.png");
    }

	public static int getFontWidth() {
        if (IS_SMALL_FONT) {
            return 5;
        }

        return 6;
    }

    public static int getFontHeight() {
	    if (IS_SMALL_FONT) {
	        return 7;
        }

	    return 8;
    }

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void draw(Bitmap bitmap, int xOffs, int yOffs) {
		for (int y = 0; y < bitmap.height; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= height) continue;

			for (int x = 0; x < bitmap.width; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= width) continue;

				int alpha = bitmap.pixels[x + y * bitmap.width];
				if (alpha != INVISIBLE) {
					pixels[xPix + yPix * width] = alpha;
				}
			}
		}
	}

    public void draw(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int col) {
        for (int y = 0; y < h; y++) {
            int yPix = y + yOffs;
            if (yPix < 0 || yPix >= height) continue;

            for (int x = 0; x < w; x++) {
                int xPix = x + xOffs;
                if (xPix < 0 || xPix >= width) continue;

                int alpha = bitmap.pixels[(x + xo) + (y + yo) * bitmap.width];
                if (alpha != INVISIBLE) {
                    pixels[xPix + yPix * width] = -alpha * col;
                }
            }
        }
    }

    public void draw(String string, int x, int y, int col) {
	    Bitmap font = getFont();
        for (int i = 0; i < string.length(); i++) {
            int ch = chars.indexOf(string.charAt(i));
            if (ch < 0) continue;

            int xx = ch % 42;
            int yy = ch / 42;
            draw(font, (x + i * getFontWidth()) + 1, y + 1, xx * 6, yy * 8, getFontWidth(), getFontHeight(), 0x020202);
            draw(font, x + i * getFontWidth(), y, xx * 6, yy * 8, getFontWidth(), getFontHeight(), col);
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
		box(x0, y0, x1, y1, color, color);
	}

    public void box(int x0, int y0, int x1, int y1, int color, int color2) {
        fill(x0, y0, x1, 1, color);
        fill(x0, y0, 1, y1, color);
        fill(x1 - 1, 1, x1, y1, color2);
        fill(1, y1 - 1, x1, y1, color2);
    }

	public void draw(Bitmap bitmap, int xOffs, int yOffs, double density) {
		for (int y = 0; y < bitmap.height; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= height) continue;

			for (int x = 0; x < bitmap.width; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= width) continue;

				int alpha = bitmap.pixels[x + y * bitmap.width];
				if (alpha != INVISIBLE) {
					pixels[xPix + yPix * width] = blendColor(alpha, pixels[xPix + yPix * width], density);
				}
			}
		}
	}

	public static int blendColorBand(int color, int colorB, double density) {
		double difColor = color - colorB;
		return (int) ((difColor / 100) * density) + colorB;
	}

	public static int blendColor(int color, int colorB, double density) {
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = (color) & 0xFF;
		int rb = (colorB >> 16) & 0xFF;
		int gb = (colorB >> 8) & 0xFF;
		int bb = (colorB) & 0xFF;

		r = blendColorBand(r, rb, density);
		g = blendColorBand(g, gb, density);
		b = blendColorBand(b, bb, density);

		return r << 16 | g << 8 | b;
	}
}
package com.jamie.jamapp;

import com.jamie.raycasting.app.Console;

import java.util.ArrayList;
import java.util.List;

public class Bitmap
{
	public int width;
	public int height;
	public int[] pixels;
	public static final int INVISIBLE = 0;

	public static boolean isSmallFont = false;

	private static final String chars = "" +
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ.,!?\"'/\\<>()[]{}" +
			"abcdefghijklmnopqrstuvwxyz_               " +
			"0123456789+-=*:;                          " +
			"";

	public Bitmap(int width, int height) {
		setSize(width, height);
	}

	private static Bitmap getFont() {
        if (isSmallFont) {
            return PngLoader.loadBitmap("gui/fontS.png");
        }

        return PngLoader.loadBitmap("gui/font.png");
    }

	public static int fontWidth() {
        if (isSmallFont) {
            return 5;
        }

        return 6;
    }

    public static int fontHeight() {
	    if (isSmallFont) {
	        return 7;
        }

	    return 8;
    }

	public static int lineHeight() {
		if (isSmallFont) {
			return 9;
		}

		return 10;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public int halfWidth() {
		return width / 2;
	}

	public int halfHeight() {
		return height / 2;
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

	public static Bitmap square(int sizeX, int sizeY, int col) {
		Bitmap border = new Bitmap(sizeX, sizeY);
		border.fill(col);

		for (int i = border.width; i < (sizeX * sizeY) -border.width; i++) {
			if (i % border.width != 0 && i % border.width != border.width - 1) {
				border.pixels[i] = INVISIBLE;
			}
		}

		return border;
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
            draw(font, (x + i * fontWidth()) + 1, y + 1, xx * 6, yy * 8, fontWidth(), fontHeight(), 0x020202);
            draw(font, x + i * fontWidth(), y, xx * 6, yy * 8, fontWidth(), fontHeight(), col);
        }
    }

    private static List<String> getTextLines(String string, int maxWidth) {
		List<String> lines = new ArrayList<String>();
		String[] words = string.trim().split("\\s+");
		String line = "";

		for (int i = 0; i < words.length; i++) {
			if ((line + words[i]).length() * fontWidth() < maxWidth) {
				line += (words[i] + " ");
			} else {
				lines.add(line.trim());
				line = (words[i] + " ");
			}
		}
		lines.add(line.trim());

		return lines;
	}

    public static Bitmap textBox(String string, int sizeX, int sizeY, int col, int bgCol) {
		List<String> lines = getTextLines(string, sizeX);

		Bitmap b = new Bitmap(sizeX, sizeY);
		b.fill(bgCol);
		for (int i = 0; i < lines.size(); i++) {
			b.draw(lines.get(i), 0, lineHeight() * i, col);
		}
		return b;
	}

	public static Bitmap textBox(String string, int col, int bgCol) {
		Bitmap b = new Bitmap(string.length() * fontWidth(), fontHeight());
		b.fill(bgCol);
		b.draw(string, 0, 0, col);
		return b;
	}

	public static Bitmap textBox(String string, int maxWidth, int col, int bgCol) {
		List<String> lines = getTextLines(string, maxWidth);

		Bitmap b = new Bitmap(maxWidth, lines.size() * lineHeight());
		b.fill(bgCol);
		for (int i = 0; i < lines.size(); i++) {
			b.draw(lines.get(i), 0, lineHeight() * i, col);
		}
		return b;
	}

	public static Bitmap textBoxTrimmed(List<String> lines, int maxWidth, int col, int bgCol) {
		int maxLineWidth = 0;
		for (int i = 0; i < lines.size(); i++) {
			int lineWidth = lines.get(i).length() * fontWidth();
			if (lineWidth > maxWidth) {
				maxLineWidth = maxWidth;
				break;
			} else if (lineWidth > maxLineWidth) {
				maxLineWidth = lineWidth;
			}
		}

		Bitmap b = new Bitmap(maxLineWidth, lines.size() * lineHeight());
		b.fill(bgCol);
		for (int i = 0; i < lines.size(); i++) {
			b.draw(lines.get(i), 0, lineHeight() * i, col);
		}
		return b;
	}

	public static Bitmap textBoxTrimmed(String string, int maxWidth, int col, int bgCol) {
		List<String> lines = getTextLines(string, maxWidth);

		int maxLineWidth = 0;
		for (int i = 0; i < lines.size(); i++) {
			int lineWidth = lines.get(i).length() * fontWidth();
			if (lineWidth > maxWidth) {
				maxLineWidth = maxWidth;
				break;
			} else if (lineWidth > maxLineWidth) {
				maxLineWidth = lineWidth;
			}
		}

		Bitmap b = new Bitmap(maxLineWidth, lines.size() * lineHeight());
		b.fill(bgCol);
		for (int i = 0; i < lines.size(); i++) {
			b.draw(lines.get(i), 0, lineHeight() * i, col);
		}
		return b;
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

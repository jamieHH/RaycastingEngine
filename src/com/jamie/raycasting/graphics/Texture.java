package com.jamie.raycasting.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class Texture
{
    // special
    public static final Render font = loadBitmap("/gui/font2.png");
    public static final Render noTex = loadBitmap("/textures/noTex.png");
    public static final Render logo = loadBitmap("/textures/logo.png");
    public static final Render sky = new Render(400, 150);
    public static final Render none = new Render(16, 16);

    // surfaces
	public static final Render floor = loadBitmap("/textures/floor.png");
	public static final Render wetFloor = loadBitmap("/textures/wetFloor.png");
	public static final Render wall = loadBitmap("/textures/wall.png");
	public static final Render cobweb = loadBitmap("/textures/cobweb.png");
	public static final Render door = loadBitmap("/textures/door.png");
	public static final Render strongDoor = loadBitmap("/textures/strongDoor.png");
	public static final Render grass = loadBitmap("/textures/grass.png");
	public static final Render dirt = loadBitmap("/textures/dirt.png");
	public static final Render leaves = loadBitmap("/textures/leaves.png");
	public static final Render stonePath = loadBitmap("/textures/stonePath.png");
	public static final Render ladderHole = loadBitmap("/textures/ladderHole.png");
    public static final Render water0 = loadBitmap("/textures/water0.png");
    public static final Render water1 = loadBitmap("/textures/water1.png");
    public static final Render water2 = loadBitmap("/textures/water2.png");
    public static final Render button0 = loadBitmap("/textures/button0.png");
    public static final Render button1 = loadBitmap("/textures/button1.png");

	// sprites
    public static final Render bat0 = loadBitmap("/textures/bat0.png");
    public static final Render bat1 = loadBitmap("/textures/bat1.png");
    public static final Render bat2 = loadBitmap("/textures/bat2.png");
    public static final Render batAtt0 = loadBitmap("/textures/batAtt0.png");
    public static final Render batAtt1 = loadBitmap("/textures/batAtt1.png");
    public static final Render batAtt2 = loadBitmap("/textures/batAtt2.png");
    public static final Render batAtt3 = loadBitmap("/textures/batAtt3.png");
    public static final Render batHurt0 = loadBitmap("/textures/batHurt0.png");
    public static final Render batHurt1 = loadBitmap("/textures/batHurt1.png");
    public static final Render batHurt2 = loadBitmap("/textures/batHurt2.png");
    public static final Render spirit0 = loadBitmap("/textures/spirit0.png");
    public static final Render spirit1 = loadBitmap("/textures/spirit1.png");
    public static final Render spirit2 = loadBitmap("/textures/spirit2.png");
    public static final Render spiritAtt0 = loadBitmap("/textures/spiritAtt0.png");
    public static final Render spiritHurt0 = loadBitmap("/textures/spiritHurt0.png");
    public static final Render spinningDummy = loadBitmap("/textures/spinningDummy.png");
    public static final Render pillar = loadBitmap("/textures/pillar.png");
    public static final Render bars = loadBitmap("/textures/bars.png");
    public static final Render ladder = loadBitmap("/textures/ladder.png");
    public static final Render boards = loadBitmap("/textures/boards.png");
    public static final Render boardsSmashed = loadBitmap("/textures/boardsSmashed.png");
    public static final Render tree = loadBitmap("/textures/tree.png");
    public static final Render fountain = loadBitmap("/textures/fountain.png");
    public static final Render splat0 = loadBitmap("/textures/splat0.png");
    public static final Render splat1 = loadBitmap("/textures/splat1.png");
    public static final Render splat2 = loadBitmap("/textures/splat2.png");
    public static final Render drip0 = loadBitmap("/textures/drip0.png");
    public static final Render drip1 = loadBitmap("/textures/drip1.png");
    public static final Render drip2 = loadBitmap("/textures/drip2.png");
    public static final Render drip3 = loadBitmap("/textures/drip3.png");
    public static final Render drip4 = loadBitmap("/textures/drip4.png");
    public static final Render grave = loadBitmap("/textures/grave.png");

	// particles
    public static final Render blood0 = loadBitmap("/textures/blood0.png");
    public static final Render blood1 = loadBitmap("/textures/blood1.png");
    public static final Render blood2 = loadBitmap("/textures/blood2.png");
    public static final Render blood3 = loadBitmap("/textures/blood3.png");
    public static final Render poof0 = loadBitmap("/textures/poof0.png");
    public static final Render poof1 = loadBitmap("/textures/poof1.png");
    public static final Render splinter0 = loadBitmap("/textures/splinter0.png");
    public static final Render splinter1 = loadBitmap("/textures/splinter1.png");
    public static final Render drop = loadBitmap("/textures/drop.png");

    // screen
    public static final Render screenSpear0 = loadBitmap("/textures/screenSpear0.png");

    private final Random random = new Random();

	public Texture () {
	    // Generate sky texture
        double percentage = (0.05);
        for (int i = 0; i < sky.width * sky.height; i++) {
            if (random.nextInt(sky.width * sky.height) < ((percentage / 8) * (sky.width * sky.height))) {
                if (random.nextBoolean()) {
                    sky.pixels[i] = 0x909090;
                } else {
                    sky.pixels[i] = 0x404040;
                }
            } else {
                sky.pixels[i] = 0;
            }
        }

        // Generate none texture
        for (int i = 0; i < none.pixels.length; i++) {
            none.pixels[i] = 0;
        }
    }

	private static Render loadBitmap(String fileName) {
		try {
			BufferedImage img = ImageIO.read(new FileInputStream("res/" + fileName));
			
			int width = img.getWidth();
			int height = img.getHeight();
			
			Render result = new Render(width, height);
			img.getRGB(0, 0, width, height, result.pixels, 0, width);

//            for (int i = 0; i < result.pixels.length; i++) {
//                int in = result.pixels[i];
//                int col = (in & 0xf) >> 2;
//                if (in == 0xffff00ff) col = -1;
//                result.pixels[i] = col;
//            }

			return result;
		} catch (Exception e) {
			System.out.println("Could not read image from file name: " + fileName + "!");
			throw new RuntimeException(e);
		}
	}
}

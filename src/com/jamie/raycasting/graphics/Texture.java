package com.jamie.raycasting.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class Texture
{
    // special
    public static final Render font = loadBitmap("/gui/font2.png");
    public static final Render noTex = loadBitmap("/textures/noTex.png");
    public static final Render invisible = setInvisible();
    public static final Render sky = new Render(400, 150);
    public static final Render none = new Render(16, 16);

    public static final Render heartIcon = loadBitmap("/gui/heartIcon.png");
    public static final Render speedIcon = loadBitmap("/gui/speedIcon.png");
    public static final Render poisonIcon = loadBitmap("/gui/poisonIcon.png");

    public static final Render one = loadBitmap("textures/1.png");
    public static final Render two = loadBitmap("textures/2.png");
    public static final Render three = loadBitmap("textures/3.png");
    public static final Render four = loadBitmap("textures/4.png");

    // private overlays
    private static final Render stoneOverlay = loadBitmap("/textures/stoneOverlay.png");
    private static final Render puddleOverlay = loadBitmap("/textures/puddleOverlay.png");
    private static final Render buttonOverlay0 = loadBitmap("/textures/buttonOverlay0.png");
    private static final Render buttonOverlay1 = loadBitmap("/textures/buttonOverlay1.png");
    private static final Render switchOverlay0 = loadBitmap("/textures/switchOverlay1.png");
    private static final Render switchOverlay1 = loadBitmap("/textures/switchOverlay1.png");

    // surfaces
	public static final Render floor = loadBitmap("/textures/floor.png");
	public static final Render wall = loadBitmap("/textures/wall.png");
	public static final Render cobweb = loadBitmap("/textures/cobweb.png");
	public static final Render door = loadBitmap("/textures/door.png");
	public static final Render strongDoor = loadBitmap("/textures/strongDoor.png");
	public static final Render grass = loadBitmap("/textures/grass.png");
	public static final Render dirt = loadBitmap("/textures/dirt.png");
	public static final Render leaves = loadBitmap("/textures/leaves.png");
    public static final Render ladderHole = loadBitmap("/textures/ladderHole.png");
    public static final Render water0 = loadBitmap("/textures/water0.png");
    public static final Render water1 = loadBitmap("/textures/water1.png");
    public static final Render water2 = loadBitmap("/textures/water2.png");

    // merged textures
    public static final Render stonePath = mergeBitmap(grass, stoneOverlay);
    public static final Render wetFloor = mergeBitmap(floor, puddleOverlay);
    public static final Render wallButton0 = mergeBitmap(wall, buttonOverlay0);
    public static final Render wallButton1 = mergeBitmap(wall, buttonOverlay1);
    public static final Render wallSwitch0 = mergeBitmap(wall, switchOverlay0);
    public static final Render wallSwitch1 = mergeBitmap(wall, switchOverlay1);

	// sprites
    public static final Render bat0 = loadBitmap("/textures/bat0.png");
    public static final Render bat1 = loadBitmap("/textures/bat1.png");
    public static final Render bat2 = loadBitmap("/textures/bat2.png");
    public static final Render batAtt0 = loadBitmap("/textures/batAtt0.png");
    public static final Render batAtt1 = loadBitmap("/textures/batAtt1.png");
    public static final Render batAtt2 = loadBitmap("/textures/batAtt2.png");
    public static final Render batHurt0 = loadBitmap("/textures/batHurt0.png");
    public static final Render batHurt1 = loadBitmap("/textures/batHurt1.png");
    public static final Render batHurt2 = loadBitmap("/textures/batHurt2.png");
    public static final Render spirit0 = loadBitmap("/textures/spirit0.png");
    public static final Render spirit1 = loadBitmap("/textures/spirit1.png");
    public static final Render spirit2 = loadBitmap("/textures/spirit2.png");
    public static final Render spiritAtt0 = loadBitmap("/textures/spiritAtt0.png");
    public static final Render spiritHurt0 = loadBitmap("/textures/spiritHurt0.png");
    public static final Render spiritHeal0 = loadBitmap("/textures/spiritHeal0.png");
    public static final Render spinningDummy0 = loadBitmap("/textures/spinningDummy0.png");
    public static final Render spinningDummy1 = loadBitmap("/textures/spinningDummy1.png");
    public static final Render spinningDummy2 = loadBitmap("/textures/spinningDummy2.png");
    public static final Render spinningDummy3 = loadBitmap("/textures/spinningDummy3.png");
    public static final Render spinningDummy4 = loadBitmap("/textures/spinningDummy4.png");
    public static final Render pillar = loadBitmap("/textures/pillar.png");
    public static final Render bars = loadBitmap("/textures/bars.png");
    public static final Render ladder = loadBitmap("/textures/ladder.png");
    public static final Render portal0 = loadBitmap("/textures/portal0.png");
    public static final Render portal1 = loadBitmap("/textures/portal1.png");
    public static final Render portal2 = loadBitmap("/textures/portal2.png");
    public static final Render portal3 = loadBitmap("/textures/portal3.png");
    public static final Render portalDisabled0 = loadBitmap("/textures/portalDisabled0.png");
    public static final Render explode0 = loadBitmap("/textures/explode0.png");
    public static final Render explode1 = loadBitmap("/textures/explode1.png");
    public static final Render explode2 = loadBitmap("/textures/explode2.png");
    public static final Render explode3 = loadBitmap("/textures/explode3.png");
    public static final Render fire0 = loadBitmap("/textures/fire0.png");
    public static final Render fire1 = loadBitmap("/textures/fire1.png");
    public static final Render fire2 = loadBitmap("/textures/fire2.png");
    public static final Render fire3 = loadBitmap("/textures/fire3.png");
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
    public static final Render fireball0 = loadBitmap("/textures/fireball0.png");
    public static final Render fireball1 = loadBitmap("/textures/fireball1.png");
    public static final Render boltProjectile0 = loadBitmap("/textures/boltProjectile0.png");

	// particles
    public static final Render blood0 = loadBitmap("/textures/blood0.png");
    public static final Render blood1 = loadBitmap("/textures/blood1.png");
    public static final Render blood2 = loadBitmap("/textures/blood2.png");
    public static final Render blood3 = loadBitmap("/textures/blood3.png");
    public static final Render poof0 = loadBitmap("/textures/poof0.png");
    public static final Render poof1 = loadBitmap("/textures/poof1.png");
    public static final Render ember0 = loadBitmap("/textures/ember0.png");
    public static final Render ember1 = loadBitmap("/textures/ember1.png");
    public static final Render health0 = loadBitmap("/textures/health0.png");
    public static final Render health1 = loadBitmap("/textures/health1.png");
    public static final Render splinter0 = loadBitmap("/textures/splinter0.png");
    public static final Render splinter1 = loadBitmap("/textures/splinter1.png");
    public static final Render drop = loadBitmap("/textures/drop.png");

    // screen
    public static final Render screenSpear0 = loadBitmap("/textures/screenSpear0.png");
    public static final Render screenSpear1 = loadBitmap("/textures/screenSpear1.png");
    public static final Render screenSpear2 = loadBitmap("/textures/screenSpear2.png");
    public static final Render screenAxe0 = loadBitmap("/textures/screenAxe0.png");
    public static final Render screenAxe1 = loadBitmap("/textures/screenAxe1.png");
    public static final Render screenWand0 = loadBitmap("/textures/screenWand0.png");
    public static final Render screenWand1 = loadBitmap("/textures/screenWand1.png");
    public static final Render screenXbow0 = loadBitmap("/textures/screenXbow0.png");
    public static final Render screenXbow1 = loadBitmap("/textures/screenXbow1.png");

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

	private static Render setInvisible() {
        Render render = new Render(16, 16);
        for (int i = 0; i < render.pixels.length; i++) {
            render.pixels[i] = -65281;
        }

        return render;
    }

    private static Render mergeBitmap(Render tex0, Render tex1) {
        Render texture = new Render(tex0.width, tex0.height);
        for (int i = 0; i < tex1.pixels.length; i++) {
            if (-tex1.pixels[i] != 65281) {
                texture.pixels[i] = tex1.pixels[i];
            } else {
                texture.pixels[i] = tex0.pixels[i];
            }
        }

        return texture;
    }
}

package com.jamie.jamapp;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class Texture
{
    // special
    public static final Render font = loadBitmap("/gui/font2.png");
    public static final Render noTex = loadBitmap("tex/noTex.png");
    public static final Render invisible = setInvisible();
    public static final Render sky = new Render(400, 150);
    public static final Render none = new Render(16, 16);

    // icons
    public static final Render spearIcon = loadBitmap("/gui/spearIcon.png");
    public static final Render axeIcon = loadBitmap("/gui/axeIcon.png");
    public static final Render knifeIcon = loadBitmap("/gui/knifeIcon.png");
    public static final Render planksIcon = loadBitmap("/gui/planksIcon.png");
    public static final Render xbowIcon = loadBitmap("/gui/xbowIcon.png");
    public static final Render wandIcon = loadBitmap("/gui/wandIcon.png");
    public static final Render bluePotionIcon = loadBitmap("/gui/bluePotionIcon.png");
    public static final Render redPotionIcon = loadBitmap("/gui/redPotionIcon.png");
    public static final Render yellowPotionIcon = loadBitmap("/gui/yellowPotionIcon.png");
    public static final Render greenPotionIcon = loadBitmap("/gui/greenPotionIcon.png");
    public static final Render keyIcon = loadBitmap("/gui/keyIcon.png");

    public static final Render heartIcon = loadBitmap("/gui/heartIcon.png");
    public static final Render poisonIcon = loadBitmap("/gui/poisonIcon.png");
    public static final Render speedIcon = loadBitmap("/gui/speedIcon.png");
    public static final Render slowIcon = loadBitmap("/gui/slowIcon.png");
    public static final Render strengthIcon = loadBitmap("/gui/strengthIcon.png");
    public static final Render weaknessIcon = loadBitmap("/gui/weaknessIcon.png");
    public static final Render fireIcon = loadBitmap("/gui/fireIcon.png");

    public static final Render nameIcon = loadBitmap("/gui/nameIcon.png");
    public static final Render damageIcon = loadBitmap("/gui/damageIcon.png");
    public static final Render rangeIcon = loadBitmap("/gui/rangeIcon.png");
    public static final Render healthIcon = loadBitmap("/gui/healthIcon.png");
    public static final Render durationIcon = loadBitmap("/gui/durationIcon.png");
    public static final Render magnitudeIcon = loadBitmap("/gui/magnitudeIcon.png");

    public static final Render one = loadBitmap("tex/1.png");
    public static final Render two = loadBitmap("tex/2.png");
    public static final Render three = loadBitmap("tex/3.png");
    public static final Render four = loadBitmap("tex/4.png");

    // private overlays
    private static final Render stoneOverlay = loadBitmap("tex/stoneOverlay.png");
    private static final Render altStoneOverlay = loadBitmap("tex/altStoneOverlay.png");
    private static final Render puddleOverlay = loadBitmap("tex/puddleOverlay.png");
    private static final Render buttonOverlay0 = loadBitmap("tex/buttonOverlay0.png");
    private static final Render buttonOverlay1 = loadBitmap("tex/buttonOverlay1.png");
    private static final Render switchOverlay0 = loadBitmap("tex/switchOverlay1.png");
    private static final Render switchOverlay1 = loadBitmap("tex/switchOverlay1.png");
    private static final Render boltSwitchOverlay0 = loadBitmap("tex/boltSwitchOverlay0.png");
    private static final Render boltSwitchOverlay1 = loadBitmap("tex/boltSwitchOverlay1.png");

    // surfaces
	public static final Render floor = loadBitmap("tex/floor.png");
	public static final Render bridge = loadBitmap("tex/bridge.png");
	public static final Render bridgeBroken = loadBitmap("tex/bridgeBroken.png");
	public static final Render wall = loadBitmap("tex/wall.png");
	public static final Render cobweb = loadBitmap("tex/cobweb.png");
	public static final Render door = loadBitmap("tex/door.png");
	public static final Render strongDoor = loadBitmap("tex/strongDoor.png");
	public static final Render grass = loadBitmap("tex/grass.png");
	public static final Render dirt = loadBitmap("tex/dirt.png");
	public static final Render leaves = loadBitmap("tex/leaves.png");
    public static final Render ladderHole = loadBitmap("tex/ladderHole.png");
    public static final Render water0 = loadBitmap("tex/water0.png");
    public static final Render water1 = loadBitmap("tex/water1.png");
    public static final Render water2 = loadBitmap("tex/water2.png");

    // merged textures
    public static final Render stonePath = mergeBitmap(grass, stoneOverlay);
    public static final Render altStonePath = mergeBitmap(grass, altStoneOverlay);
    public static final Render wetFloor = mergeBitmap(floor, puddleOverlay);
    public static final Render wallButton0 = mergeBitmap(wall, buttonOverlay0);
    public static final Render wallButton1 = mergeBitmap(wall, buttonOverlay1);
    public static final Render wallBoltSwitch0 = mergeBitmap(wall, boltSwitchOverlay0);
    public static final Render wallBoltSwitch1 = mergeBitmap(wall, boltSwitchOverlay1);
    public static final Render bridgeWaterBroken = mergeBitmap(water0, bridgeBroken);
    public static final Render wallSwitch0 = mergeBitmap(wall, switchOverlay0);
    public static final Render wallSwitch1 = mergeBitmap(wall, switchOverlay1);

	// sprites
    public static final Render bat0 = loadBitmap("tex/bat0.png");
    public static final Render bat1 = loadBitmap("tex/bat1.png");
    public static final Render bat2 = loadBitmap("tex/bat2.png");
    public static final Render batAtt0 = loadBitmap("tex/batAtt0.png");
    public static final Render batAtt1 = loadBitmap("tex/batAtt1.png");
    public static final Render batAtt2 = loadBitmap("tex/batAtt2.png");
    public static final Render batHurt0 = loadBitmap("tex/batHurt0.png");
    public static final Render batHurt1 = loadBitmap("tex/batHurt1.png");
    public static final Render batHurt2 = loadBitmap("tex/batHurt2.png");
    public static final Render spirit0 = loadBitmap("tex/spirit0.png");
    public static final Render spirit1 = loadBitmap("tex/spirit1.png");
    public static final Render spirit2 = loadBitmap("tex/spirit2.png");
    public static final Render spiritAtt0 = loadBitmap("tex/spiritAtt0.png");
    public static final Render spiritHurt0 = loadBitmap("tex/spiritHurt0.png");
    public static final Render spiritHeal0 = loadBitmap("tex/spiritHeal0.png");
    public static final Render spinningDummy0 = loadBitmap("tex/spinningDummy0.png");
    public static final Render spinningDummy1 = loadBitmap("tex/spinningDummy1.png");
    public static final Render spinningDummy2 = loadBitmap("tex/spinningDummy2.png");
    public static final Render spinningDummy3 = loadBitmap("tex/spinningDummy3.png");
    public static final Render spinningDummy4 = loadBitmap("tex/spinningDummy4.png");
    public static final Render pillar = loadBitmap("tex/pillar.png");
    public static final Render lamp = loadBitmap("tex/lamp.png");
    public static final Render bars = loadBitmap("tex/bars.png");
    public static final Render ladder = loadBitmap("tex/ladder.png");
    public static final Render portal0 = loadBitmap("tex/portal0.png");
    public static final Render portal1 = loadBitmap("tex/portal1.png");
    public static final Render portal2 = loadBitmap("tex/portal2.png");
    public static final Render portal3 = loadBitmap("tex/portal3.png");
    public static final Render portalDisabled0 = loadBitmap("tex/portalDisabled0.png");
    public static final Render explode0 = loadBitmap("tex/explode0.png");
    public static final Render explode1 = loadBitmap("tex/explode1.png");
    public static final Render explode2 = loadBitmap("tex/explode2.png");
    public static final Render explode3 = loadBitmap("tex/explode3.png");
    public static final Render fire0 = loadBitmap("tex/fire0.png");
    public static final Render fire1 = loadBitmap("tex/fire1.png");
    public static final Render fire2 = loadBitmap("tex/fire2.png");
    public static final Render fire3 = loadBitmap("tex/fire3.png");
    public static final Render boards = loadBitmap("tex/boards.png");
    public static final Render boardsSmashed = loadBitmap("tex/boardsSmashed.png");
    public static final Render tree = loadBitmap("tex/tree.png");
    public static final Render fountain = loadBitmap("tex/fountain.png");
    public static final Render splat0 = loadBitmap("tex/splat0.png");
    public static final Render splat1 = loadBitmap("tex/splat1.png");
    public static final Render splat2 = loadBitmap("tex/splat2.png");
    public static final Render drip0 = loadBitmap("tex/drip0.png");
    public static final Render drip1 = loadBitmap("tex/drip1.png");
    public static final Render drip2 = loadBitmap("tex/drip2.png");
    public static final Render drip3 = loadBitmap("tex/drip3.png");
    public static final Render drip4 = loadBitmap("tex/drip4.png");
    public static final Render grave = loadBitmap("tex/grave.png");
    public static final Render fireball0 = loadBitmap("tex/fireball0.png");
    public static final Render fireball1 = loadBitmap("tex/fireball1.png");
    public static final Render gate0 = loadBitmap("tex/gate0.png");
    public static final Render gate1 = loadBitmap("tex/gate1.png");
    public static final Render gate2 = loadBitmap("tex/gate2.png");
    public static final Render gate3 = loadBitmap("tex/gate3.png");
    public static final Render guardian0 = loadBitmap("tex/guardian0.png");
    public static final Render imp0 = loadBitmap("tex/imp0.png");
    public static final Render imp1 = loadBitmap("tex/imp1.png");
    public static final Render imp2 = loadBitmap("tex/imp2.png");
    public static final Render impAtt0 = loadBitmap("tex/impAtt0.png");
    public static final Render impAtt1 = loadBitmap("tex/impAtt1.png");
    public static final Render impAtt2 = loadBitmap("tex/impAtt2.png");
    public static final Render impHurt0 = loadBitmap("tex/impHurt0.png");
    public static final Render impHurt1 = loadBitmap("tex/impHurt1.png");
    public static final Render impHurt2 = loadBitmap("tex/impHurt2.png");
    public static final Render impHeal0 = loadBitmap("tex/impHeal0.png");
    public static final Render impHeal1 = loadBitmap("tex/impHeal1.png");
    public static final Render impHeal2 = loadBitmap("tex/impHeal2.png");
    public static final Render torch0 = loadBitmap("tex/torch0.png");
    public static final Render torch1 = loadBitmap("tex/torch1.png");
    public static final Render torch2 = loadBitmap("tex/torch2.png");

	// particles
    public static final Render blood0 = loadBitmap("tex/blood0.png");
    public static final Render blood1 = loadBitmap("tex/blood1.png");
    public static final Render blood2 = loadBitmap("tex/blood2.png");
    public static final Render blood3 = loadBitmap("tex/blood3.png");
    public static final Render stone0 = loadBitmap("tex/stone0.png");
    public static final Render stone1 = loadBitmap("tex/stone1.png");
    public static final Render stone2 = loadBitmap("tex/stone2.png");
    public static final Render stone3 = loadBitmap("tex/stone3.png");
    public static final Render poof0 = loadBitmap("tex/poof0.png");
    public static final Render poof1 = loadBitmap("tex/poof1.png");
    public static final Render ember0 = loadBitmap("tex/ember0.png");
    public static final Render ember1 = loadBitmap("tex/ember1.png");
    public static final Render health0 = loadBitmap("tex/health0.png");
    public static final Render health1 = loadBitmap("tex/health1.png");
    public static final Render splinter0 = loadBitmap("tex/splinter0.png");
    public static final Render splinter1 = loadBitmap("tex/splinter1.png");
    public static final Render drop = loadBitmap("tex/drop.png");

    // screen
    public static final Render screenSpear0 = loadBitmap("tex/screenSpear0.png");
    public static final Render screenSpear1 = loadBitmap("tex/screenSpear1.png");
    public static final Render screenSpear2 = loadBitmap("tex/screenSpear2.png");
    public static final Render screenAxe0 = loadBitmap("tex/screenAxe0.png");
    public static final Render screenAxe1 = loadBitmap("tex/screenAxe1.png");
    public static final Render screenKnife0 = loadBitmap("tex/screenKnife0.png");
    public static final Render screenKnife1 = loadBitmap("tex/screenKnife1.png");
    public static final Render screenKnife2 = loadBitmap("tex/screenKnife2.png");
    public static final Render screenWand0 = loadBitmap("tex/screenWand0.png");
    public static final Render screenWand1 = loadBitmap("tex/screenWand1.png");
    public static final Render screenWand2 = loadBitmap("tex/screenWand2.png");
    public static final Render screenWand3 = loadBitmap("tex/screenWand3.png");
    public static final Render screenWand4 = loadBitmap("tex/screenWand4.png");
    public static final Render screenXbow0 = loadBitmap("tex/screenXbow0.png");
    public static final Render screenXbow1 = loadBitmap("tex/screenXbow1.png");
    public static final Render bag = loadBitmap("tex/bag.png");

	private static Render loadBitmap(String fileName) {
		try {
			BufferedImage img = ImageIO.read(new FileInputStream("res/" + fileName));
			
			int width = img.getWidth();
			int height = img.getHeight();
			
			Render result = new Render(width, height);
			img.getRGB(0, 0, width, height, result.pixels, 0, width);

            for (int i = 0; i < result.pixels.length; i++) {
                if (result.pixels[i] == 0xFFFF00FF) {
                    result.pixels[i] = Render.INVISIBLE;
                }
            }

			return result;
		} catch (Exception e) {
			System.out.println("Could not read image from file name: " + fileName + "!");
			throw new RuntimeException(e);
		}
	}

	private static Render setInvisible() {
        Render render = new Render(16, 16);
        for (int i = 0; i < render.pixels.length; i++) {
            render.pixels[i] = Render.INVISIBLE;
        }

        return render;
    }

    private static Render mergeBitmap(Render tex0, Render tex1) {
        Render texture = new Render(tex0.width, tex0.height);
        for (int i = 0; i < tex1.pixels.length; i++) {
            if (tex1.pixels[i] != Render.INVISIBLE) {
                texture.pixels[i] = tex1.pixels[i];
            } else {
                texture.pixels[i] = tex0.pixels[i];
            }
        }

        return texture;
    }
}

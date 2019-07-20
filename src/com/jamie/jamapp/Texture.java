package com.jamie.jamapp;

public class Texture
{
    // special
    public static final Render font = PngLoader.loadBitmap("/gui/font2.png");
    public static final Render noTex = PngLoader.loadBitmap("tex/noTex.png");
    public static final Render invisible = PngLoader.setInvisible();
    public static final Render sky = new Render(400, 150);
    public static final Render none = new Render(16, 16);

    // icons
    public static final Render spearIcon = PngLoader.loadBitmap("/gui/spearIcon.png");
    public static final Render axeIcon = PngLoader.loadBitmap("/gui/axeIcon.png");
    public static final Render knifeIcon = PngLoader.loadBitmap("/gui/knifeIcon.png");
    public static final Render planksIcon = PngLoader.loadBitmap("/gui/planksIcon.png");
    public static final Render xbowIcon = PngLoader.loadBitmap("/gui/xbowIcon.png");
    public static final Render wandIcon = PngLoader.loadBitmap("/gui/wandIcon.png");
    public static final Render bluePotionIcon = PngLoader.loadBitmap("/gui/bluePotionIcon.png");
    public static final Render redPotionIcon = PngLoader.loadBitmap("/gui/redPotionIcon.png");
    public static final Render yellowPotionIcon = PngLoader.loadBitmap("/gui/yellowPotionIcon.png");
    public static final Render greenPotionIcon = PngLoader.loadBitmap("/gui/greenPotionIcon.png");
    public static final Render keyIcon = PngLoader.loadBitmap("/gui/keyIcon.png");

    public static final Render heartIcon = PngLoader.loadBitmap("/gui/heartIcon.png");
    public static final Render poisonIcon = PngLoader.loadBitmap("/gui/poisonIcon.png");
    public static final Render speedIcon = PngLoader.loadBitmap("/gui/speedIcon.png");
    public static final Render slowIcon = PngLoader.loadBitmap("/gui/slowIcon.png");
    public static final Render strengthIcon = PngLoader.loadBitmap("/gui/strengthIcon.png");
    public static final Render weaknessIcon = PngLoader.loadBitmap("/gui/weaknessIcon.png");
    public static final Render fireIcon = PngLoader.loadBitmap("/gui/fireIcon.png");

    public static final Render nameIcon = PngLoader.loadBitmap("/gui/nameIcon.png");
    public static final Render damageIcon = PngLoader.loadBitmap("/gui/damageIcon.png");
    public static final Render rangeIcon = PngLoader.loadBitmap("/gui/rangeIcon.png");
    public static final Render healthIcon = PngLoader.loadBitmap("/gui/healthIcon.png");
    public static final Render durationIcon = PngLoader.loadBitmap("/gui/durationIcon.png");
    public static final Render magnitudeIcon = PngLoader.loadBitmap("/gui/magnitudeIcon.png");

    public static final Render one = PngLoader.loadBitmap("tex/1.png");
    public static final Render two = PngLoader.loadBitmap("tex/2.png");
    public static final Render three = PngLoader.loadBitmap("tex/3.png");
    public static final Render four = PngLoader.loadBitmap("tex/4.png");

    // private overlays
    private static final Render stoneOverlay = PngLoader.loadBitmap("tex/stoneOverlay.png");
    private static final Render altStoneOverlay = PngLoader.loadBitmap("tex/altStoneOverlay.png");
    private static final Render puddleOverlay = PngLoader.loadBitmap("tex/puddleOverlay.png");
    private static final Render buttonOverlay0 = PngLoader.loadBitmap("tex/buttonOverlay0.png");
    private static final Render buttonOverlay1 = PngLoader.loadBitmap("tex/buttonOverlay1.png");
    private static final Render switchOverlay0 = PngLoader.loadBitmap("tex/switchOverlay1.png");
    private static final Render switchOverlay1 = PngLoader.loadBitmap("tex/switchOverlay1.png");
    private static final Render boltSwitchOverlay0 = PngLoader.loadBitmap("tex/boltSwitchOverlay0.png");
    private static final Render boltSwitchOverlay1 = PngLoader.loadBitmap("tex/boltSwitchOverlay1.png");

    // surfaces
	public static final Render floor = PngLoader.loadBitmap("tex/floor.png");
	public static final Render bridge = PngLoader.loadBitmap("tex/bridge.png");
	public static final Render bridgeBroken = PngLoader.loadBitmap("tex/bridgeBroken.png");
	public static final Render wall = PngLoader.loadBitmap("tex/wall.png");
	public static final Render cobweb = PngLoader.loadBitmap("tex/cobweb.png");
	public static final Render door = PngLoader.loadBitmap("tex/door.png");
	public static final Render strongDoor = PngLoader.loadBitmap("tex/strongDoor.png");
	public static final Render grass = PngLoader.loadBitmap("tex/grass.png");
	public static final Render dirt = PngLoader.loadBitmap("tex/dirt.png");
	public static final Render leaves = PngLoader.loadBitmap("tex/leaves.png");
    public static final Render ladderHole = PngLoader.loadBitmap("tex/ladderHole.png");
    public static final Render water0 = PngLoader.loadBitmap("tex/water0.png");
    public static final Render water1 = PngLoader.loadBitmap("tex/water1.png");
    public static final Render water2 = PngLoader.loadBitmap("tex/water2.png");

    // merged textures
    public static final Render stonePath = PngLoader.mergeBitmap(grass, stoneOverlay);
    public static final Render altStonePath = PngLoader.mergeBitmap(grass, altStoneOverlay);
    public static final Render wetFloor = PngLoader.mergeBitmap(floor, puddleOverlay);
    public static final Render wallButton0 = PngLoader.mergeBitmap(wall, buttonOverlay0);
    public static final Render wallButton1 = PngLoader.mergeBitmap(wall, buttonOverlay1);
    public static final Render wallBoltSwitch0 = PngLoader.mergeBitmap(wall, boltSwitchOverlay0);
    public static final Render wallBoltSwitch1 = PngLoader.mergeBitmap(wall, boltSwitchOverlay1);
    public static final Render bridgeWaterBroken = PngLoader.mergeBitmap(water0, bridgeBroken);
    public static final Render wallSwitch0 = PngLoader.mergeBitmap(wall, switchOverlay0);
    public static final Render wallSwitch1 = PngLoader.mergeBitmap(wall, switchOverlay1);

	// sprites
    public static final Render bat0 = PngLoader.loadBitmap("tex/bat0.png");
    public static final Render bat1 = PngLoader.loadBitmap("tex/bat1.png");
    public static final Render bat2 = PngLoader.loadBitmap("tex/bat2.png");
    public static final Render batAtt0 = PngLoader.loadBitmap("tex/batAtt0.png");
    public static final Render batAtt1 = PngLoader.loadBitmap("tex/batAtt1.png");
    public static final Render batAtt2 = PngLoader.loadBitmap("tex/batAtt2.png");
    public static final Render batHurt0 = PngLoader.loadBitmap("tex/batHurt0.png");
    public static final Render batHurt1 = PngLoader.loadBitmap("tex/batHurt1.png");
    public static final Render batHurt2 = PngLoader.loadBitmap("tex/batHurt2.png");
    public static final Render spirit0 = PngLoader.loadBitmap("tex/spirit0.png");
    public static final Render spirit1 = PngLoader.loadBitmap("tex/spirit1.png");
    public static final Render spirit2 = PngLoader.loadBitmap("tex/spirit2.png");
    public static final Render spiritAtt0 = PngLoader.loadBitmap("tex/spiritAtt0.png");
    public static final Render spiritHurt0 = PngLoader.loadBitmap("tex/spiritHurt0.png");
    public static final Render spiritHeal0 = PngLoader.loadBitmap("tex/spiritHeal0.png");
    public static final Render spinningDummy0 = PngLoader.loadBitmap("tex/spinningDummy0.png");
    public static final Render spinningDummy1 = PngLoader.loadBitmap("tex/spinningDummy1.png");
    public static final Render spinningDummy2 = PngLoader.loadBitmap("tex/spinningDummy2.png");
    public static final Render spinningDummy3 = PngLoader.loadBitmap("tex/spinningDummy3.png");
    public static final Render spinningDummy4 = PngLoader.loadBitmap("tex/spinningDummy4.png");
    public static final Render pillar = PngLoader.loadBitmap("tex/pillar.png");
    public static final Render lamp = PngLoader.loadBitmap("tex/lamp.png");
    public static final Render bars = PngLoader.loadBitmap("tex/bars.png");
    public static final Render ladder = PngLoader.loadBitmap("tex/ladder.png");
    public static final Render portal0 = PngLoader.loadBitmap("tex/portal0.png");
    public static final Render portal1 = PngLoader.loadBitmap("tex/portal1.png");
    public static final Render portal2 = PngLoader.loadBitmap("tex/portal2.png");
    public static final Render portal3 = PngLoader.loadBitmap("tex/portal3.png");
    public static final Render portalDisabled0 = PngLoader.loadBitmap("tex/portalDisabled0.png");
    public static final Render explode0 = PngLoader.loadBitmap("tex/explode0.png");
    public static final Render explode1 = PngLoader.loadBitmap("tex/explode1.png");
    public static final Render explode2 = PngLoader.loadBitmap("tex/explode2.png");
    public static final Render explode3 = PngLoader.loadBitmap("tex/explode3.png");
    public static final Render fire0 = PngLoader.loadBitmap("tex/fire0.png");
    public static final Render fire1 = PngLoader.loadBitmap("tex/fire1.png");
    public static final Render fire2 = PngLoader.loadBitmap("tex/fire2.png");
    public static final Render fire3 = PngLoader.loadBitmap("tex/fire3.png");
    public static final Render boards = PngLoader.loadBitmap("tex/boards.png");
    public static final Render boardsSmashed = PngLoader.loadBitmap("tex/boardsSmashed.png");
    public static final Render tree = PngLoader.loadBitmap("tex/tree.png");
    public static final Render fountain = PngLoader.loadBitmap("tex/fountain.png");
    public static final Render splat0 = PngLoader.loadBitmap("tex/splat0.png");
    public static final Render splat1 = PngLoader.loadBitmap("tex/splat1.png");
    public static final Render splat2 = PngLoader.loadBitmap("tex/splat2.png");
    public static final Render drip0 = PngLoader.loadBitmap("tex/drip0.png");
    public static final Render drip1 = PngLoader.loadBitmap("tex/drip1.png");
    public static final Render drip2 = PngLoader.loadBitmap("tex/drip2.png");
    public static final Render drip3 = PngLoader.loadBitmap("tex/drip3.png");
    public static final Render drip4 = PngLoader.loadBitmap("tex/drip4.png");
    public static final Render grave = PngLoader.loadBitmap("tex/grave.png");
    public static final Render fireball0 = PngLoader.loadBitmap("tex/fireball0.png");
    public static final Render fireball1 = PngLoader.loadBitmap("tex/fireball1.png");
    public static final Render gate0 = PngLoader.loadBitmap("tex/gate0.png");
    public static final Render gate1 = PngLoader.loadBitmap("tex/gate1.png");
    public static final Render gate2 = PngLoader.loadBitmap("tex/gate2.png");
    public static final Render gate3 = PngLoader.loadBitmap("tex/gate3.png");
    public static final Render guardian0 = PngLoader.loadBitmap("tex/guardian0.png");
    public static final Render imp0 = PngLoader.loadBitmap("tex/imp0.png");
    public static final Render imp1 = PngLoader.loadBitmap("tex/imp1.png");
    public static final Render imp2 = PngLoader.loadBitmap("tex/imp2.png");
    public static final Render impAtt0 = PngLoader.loadBitmap("tex/impAtt0.png");
    public static final Render impAtt1 = PngLoader.loadBitmap("tex/impAtt1.png");
    public static final Render impAtt2 = PngLoader.loadBitmap("tex/impAtt2.png");
    public static final Render impHurt0 = PngLoader.loadBitmap("tex/impHurt0.png");
    public static final Render impHurt1 = PngLoader.loadBitmap("tex/impHurt1.png");
    public static final Render impHurt2 = PngLoader.loadBitmap("tex/impHurt2.png");
    public static final Render impHeal0 = PngLoader.loadBitmap("tex/impHeal0.png");
    public static final Render impHeal1 = PngLoader.loadBitmap("tex/impHeal1.png");
    public static final Render impHeal2 = PngLoader.loadBitmap("tex/impHeal2.png");
    public static final Render torch0 = PngLoader.loadBitmap("tex/torch0.png");
    public static final Render torch1 = PngLoader.loadBitmap("tex/torch1.png");
    public static final Render torch2 = PngLoader.loadBitmap("tex/torch2.png");

	// particles
    public static final Render blood0 = PngLoader.loadBitmap("tex/blood0.png");
    public static final Render blood1 = PngLoader.loadBitmap("tex/blood1.png");
    public static final Render blood2 = PngLoader.loadBitmap("tex/blood2.png");
    public static final Render blood3 = PngLoader.loadBitmap("tex/blood3.png");
    public static final Render stone0 = PngLoader.loadBitmap("tex/stone0.png");
    public static final Render stone1 = PngLoader.loadBitmap("tex/stone1.png");
    public static final Render stone2 = PngLoader.loadBitmap("tex/stone2.png");
    public static final Render stone3 = PngLoader.loadBitmap("tex/stone3.png");
    public static final Render poof0 = PngLoader.loadBitmap("tex/poof0.png");
    public static final Render poof1 = PngLoader.loadBitmap("tex/poof1.png");
    public static final Render ember0 = PngLoader.loadBitmap("tex/ember0.png");
    public static final Render ember1 = PngLoader.loadBitmap("tex/ember1.png");
    public static final Render health0 = PngLoader.loadBitmap("tex/health0.png");
    public static final Render health1 = PngLoader.loadBitmap("tex/health1.png");
    public static final Render splinter0 = PngLoader.loadBitmap("tex/splinter0.png");
    public static final Render splinter1 = PngLoader.loadBitmap("tex/splinter1.png");
    public static final Render drop = PngLoader.loadBitmap("tex/drop.png");

    // screen
    public static final Render screenSpear0 = PngLoader.loadBitmap("tex/screenSpear0.png");
    public static final Render screenSpear1 = PngLoader.loadBitmap("tex/screenSpear1.png");
    public static final Render screenSpear2 = PngLoader.loadBitmap("tex/screenSpear2.png");
    public static final Render screenAxe0 = PngLoader.loadBitmap("tex/screenAxe0.png");
    public static final Render screenAxe1 = PngLoader.loadBitmap("tex/screenAxe1.png");
    public static final Render screenKnife0 = PngLoader.loadBitmap("tex/screenKnife0.png");
    public static final Render screenKnife1 = PngLoader.loadBitmap("tex/screenKnife1.png");
    public static final Render screenKnife2 = PngLoader.loadBitmap("tex/screenKnife2.png");
    public static final Render screenWand0 = PngLoader.loadBitmap("tex/screenWand0.png");
    public static final Render screenWand1 = PngLoader.loadBitmap("tex/screenWand1.png");
    public static final Render screenWand2 = PngLoader.loadBitmap("tex/screenWand2.png");
    public static final Render screenWand3 = PngLoader.loadBitmap("tex/screenWand3.png");
    public static final Render screenWand4 = PngLoader.loadBitmap("tex/screenWand4.png");
    public static final Render screenXbow0 = PngLoader.loadBitmap("tex/screenXbow0.png");
    public static final Render screenXbow1 = PngLoader.loadBitmap("tex/screenXbow1.png");
    public static final Render bag = PngLoader.loadBitmap("tex/bag.png");
}

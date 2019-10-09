package com.jamie.raycasting.graphics;

import com.jamie.jamapp.PngLoader;
import com.jamie.jamapp.Bitmap;

public class Texture
{
    // special
    public static final Bitmap noTex = PngLoader.loadBitmap("tex/noTex.png");
    public static final Bitmap fogHours = PngLoader.loadBitmap("tex/fogHours.png");

    // icons
    public static final Bitmap spearIcon = PngLoader.loadBitmap("/gui/spearIcon.png");
    public static final Bitmap axeIcon = PngLoader.loadBitmap("/gui/axeIcon.png");
    public static final Bitmap knifeIcon = PngLoader.loadBitmap("/gui/knifeIcon.png");
    public static final Bitmap planksIcon = PngLoader.loadBitmap("/gui/planksIcon.png");
    public static final Bitmap xbowIcon = PngLoader.loadBitmap("/gui/xbowIcon.png");
    public static final Bitmap wandIcon = PngLoader.loadBitmap("/gui/wandIcon.png");
    public static final Bitmap bluePotionIcon = PngLoader.loadBitmap("/gui/bluePotionIcon.png");
    public static final Bitmap redPotionIcon = PngLoader.loadBitmap("/gui/redPotionIcon.png");
    public static final Bitmap yellowPotionIcon = PngLoader.loadBitmap("/gui/yellowPotionIcon.png");
    public static final Bitmap greenPotionIcon = PngLoader.loadBitmap("/gui/greenPotionIcon.png");
    public static final Bitmap keyIcon = PngLoader.loadBitmap("/gui/keyIcon.png");

    public static final Bitmap heartIcon = PngLoader.loadBitmap("/gui/heartIcon.png");
    public static final Bitmap poisonIcon = PngLoader.loadBitmap("/gui/poisonIcon.png");
    public static final Bitmap speedIcon = PngLoader.loadBitmap("/gui/speedIcon.png");
    public static final Bitmap slowIcon = PngLoader.loadBitmap("/gui/slowIcon.png");
    public static final Bitmap strengthIcon = PngLoader.loadBitmap("/gui/strengthIcon.png");
    public static final Bitmap weaknessIcon = PngLoader.loadBitmap("/gui/weaknessIcon.png");
    public static final Bitmap fireIcon = PngLoader.loadBitmap("/gui/fireIcon.png");

    public static final Bitmap damageIcon = PngLoader.loadBitmap("/gui/damageIcon.png");
    public static final Bitmap rangeIcon = PngLoader.loadBitmap("/gui/rangeIcon.png");
    public static final Bitmap healthIcon = PngLoader.loadBitmap("/gui/healthIcon.png");
    public static final Bitmap durationIcon = PngLoader.loadBitmap("/gui/durationIcon.png");
    public static final Bitmap magnitudeIcon = PngLoader.loadBitmap("/gui/magnitudeIcon.png");

    public static final Bitmap one = PngLoader.loadBitmap("tex/1.png");
    public static final Bitmap two = PngLoader.loadBitmap("tex/2.png");
    public static final Bitmap three = PngLoader.loadBitmap("tex/3.png");
    public static final Bitmap four = PngLoader.loadBitmap("tex/4.png");

    // surfaces
	public static final Bitmap floor = PngLoader.loadBitmap("tex/floor.png");
	public static final Bitmap bridge = PngLoader.loadBitmap("tex/bridge.png");
	public static final Bitmap wall = PngLoader.loadBitmap("tex/wall.png");
	public static final Bitmap cobweb = PngLoader.loadBitmap("tex/cobweb.png");
	public static final Bitmap door = PngLoader.loadBitmap("tex/door.png");
	public static final Bitmap strongDoor = PngLoader.loadBitmap("tex/strongDoor.png");
	public static final Bitmap grass = PngLoader.loadBitmap("tex/grass.png");
	public static final Bitmap dirt = PngLoader.loadBitmap("tex/dirt.png");
	public static final Bitmap leaves = PngLoader.loadBitmap("tex/leaves.png");
    public static final Bitmap ladderHole = PngLoader.loadBitmap("tex/ladderHole.png");
    public static final Bitmap water0 = PngLoader.loadBitmap("tex/water0.png");
    public static final Bitmap water1 = PngLoader.loadBitmap("tex/water1.png");
    public static final Bitmap water2 = PngLoader.loadBitmap("tex/water2.png");

    // merged textures
    public static final Bitmap stonePath = PngLoader.loadBitmap("tex/stonePath.png");
    public static final Bitmap altStonePath = PngLoader.loadBitmap("tex/altStonePath.png");
    public static final Bitmap wetFloor = PngLoader.loadBitmap("tex/wetFloor.png");
    public static final Bitmap wallButton0 = PngLoader.loadBitmap("tex/wallButton0.png");
    public static final Bitmap wallButton1 = PngLoader.loadBitmap("tex/wallButton1.png");
    public static final Bitmap wallBoltSwitch0 = PngLoader.loadBitmap("tex/wallBoltSwitch0.png");
    public static final Bitmap wallBoltSwitch1 = PngLoader.loadBitmap("tex/wallBoltSwitch1.png");
    public static final Bitmap bridgeWaterBroken = PngLoader.loadBitmap("tex/bridgeWaterBroken.png");
    public static final Bitmap wallSwitch0 = PngLoader.loadBitmap("tex/wallSwitch0.png");
    public static final Bitmap wallSwitch1 = PngLoader.loadBitmap("tex/wallSwitch1.png");

	// sprites
    public static final Bitmap marker = PngLoader.loadBitmap("tex/marker.png");
    public static final Bitmap signN = PngLoader.loadBitmap("tex/signN.png");
    public static final Bitmap signS = PngLoader.loadBitmap("tex/signS.png");
    public static final Bitmap signE = PngLoader.loadBitmap("tex/signE.png");
    public static final Bitmap signW = PngLoader.loadBitmap("tex/signW.png");
    public static final Bitmap bat0 = PngLoader.loadBitmap("tex/bat0.png");
    public static final Bitmap bat1 = PngLoader.loadBitmap("tex/bat1.png");
    public static final Bitmap bat2 = PngLoader.loadBitmap("tex/bat2.png");
    public static final Bitmap batAtt0 = PngLoader.loadBitmap("tex/batAtt0.png");
    public static final Bitmap batAtt1 = PngLoader.loadBitmap("tex/batAtt1.png");
    public static final Bitmap batAtt2 = PngLoader.loadBitmap("tex/batAtt2.png");
    public static final Bitmap batHurt0 = PngLoader.loadBitmap("tex/batHurt0.png");
    public static final Bitmap batHurt1 = PngLoader.loadBitmap("tex/batHurt1.png");
    public static final Bitmap batHurt2 = PngLoader.loadBitmap("tex/batHurt2.png");
    public static final Bitmap spirit0 = PngLoader.loadBitmap("tex/spirit0.png");
    public static final Bitmap spirit1 = PngLoader.loadBitmap("tex/spirit1.png");
    public static final Bitmap spirit2 = PngLoader.loadBitmap("tex/spirit2.png");
    public static final Bitmap spiritAtt0 = PngLoader.loadBitmap("tex/spiritAtt0.png");
    public static final Bitmap spiritHurt0 = PngLoader.loadBitmap("tex/spiritHurt0.png");
    public static final Bitmap spiritHeal0 = PngLoader.loadBitmap("tex/spiritHeal0.png");
    public static final Bitmap spinningDummy0 = PngLoader.loadBitmap("tex/spinningDummy0.png");
    public static final Bitmap spinningDummy1 = PngLoader.loadBitmap("tex/spinningDummy1.png");
    public static final Bitmap spinningDummy2 = PngLoader.loadBitmap("tex/spinningDummy2.png");
    public static final Bitmap spinningDummy3 = PngLoader.loadBitmap("tex/spinningDummy3.png");
    public static final Bitmap spinningDummy4 = PngLoader.loadBitmap("tex/spinningDummy4.png");
    public static final Bitmap pillar = PngLoader.loadBitmap("tex/pillar.png");
    public static final Bitmap lamp0 = PngLoader.loadBitmap("tex/lamp0.png");
    public static final Bitmap lamp1 = PngLoader.loadBitmap("tex/lamp1.png");
    public static final Bitmap lamp2 = PngLoader.loadBitmap("tex/lamp2.png");
    public static final Bitmap lamp3 = PngLoader.loadBitmap("tex/lamp3.png");
    public static final Bitmap bars = PngLoader.loadBitmap("tex/bars.png");
    public static final Bitmap barrel0 = PngLoader.loadBitmap("tex/barrel0.png");
    public static final Bitmap ladder = PngLoader.loadBitmap("tex/ladder.png");
    public static final Bitmap portal0 = PngLoader.loadBitmap("tex/portal0.png");
    public static final Bitmap portal1 = PngLoader.loadBitmap("tex/portal1.png");
    public static final Bitmap portal2 = PngLoader.loadBitmap("tex/portal2.png");
    public static final Bitmap portal3 = PngLoader.loadBitmap("tex/portal3.png");
    public static final Bitmap portalDisabled0 = PngLoader.loadBitmap("tex/portalDisabled0.png");
    public static final Bitmap explode0 = PngLoader.loadBitmap("tex/explode0.png");
    public static final Bitmap explode1 = PngLoader.loadBitmap("tex/explode1.png");
    public static final Bitmap explode2 = PngLoader.loadBitmap("tex/explode2.png");
    public static final Bitmap explode3 = PngLoader.loadBitmap("tex/explode3.png");
    public static final Bitmap fire0 = PngLoader.loadBitmap("tex/fire0.png");
    public static final Bitmap fire1 = PngLoader.loadBitmap("tex/fire1.png");
    public static final Bitmap fire2 = PngLoader.loadBitmap("tex/fire2.png");
    public static final Bitmap fire3 = PngLoader.loadBitmap("tex/fire3.png");
    public static final Bitmap boards = PngLoader.loadBitmap("tex/boards.png");
    public static final Bitmap boardsSmashed = PngLoader.loadBitmap("tex/boardsSmashed.png");
    public static final Bitmap tree = PngLoader.loadBitmap("tex/tree.png");
    public static final Bitmap fountain = PngLoader.loadBitmap("tex/fountain.png");
    public static final Bitmap splat0 = PngLoader.loadBitmap("tex/splat0.png");
    public static final Bitmap splat1 = PngLoader.loadBitmap("tex/splat1.png");
    public static final Bitmap splat2 = PngLoader.loadBitmap("tex/splat2.png");
    public static final Bitmap drip0 = PngLoader.loadBitmap("tex/drip0.png");
    public static final Bitmap drip1 = PngLoader.loadBitmap("tex/drip1.png");
    public static final Bitmap drip2 = PngLoader.loadBitmap("tex/drip2.png");
    public static final Bitmap drip3 = PngLoader.loadBitmap("tex/drip3.png");
    public static final Bitmap drip4 = PngLoader.loadBitmap("tex/drip4.png");
    public static final Bitmap grave = PngLoader.loadBitmap("tex/grave.png");
    public static final Bitmap fireball0 = PngLoader.loadBitmap("tex/fireball0.png");
    public static final Bitmap fireball1 = PngLoader.loadBitmap("tex/fireball1.png");
    public static final Bitmap gate0 = PngLoader.loadBitmap("tex/gate0.png");
    public static final Bitmap gate1 = PngLoader.loadBitmap("tex/gate1.png");
    public static final Bitmap gate2 = PngLoader.loadBitmap("tex/gate2.png");
    public static final Bitmap gate3 = PngLoader.loadBitmap("tex/gate3.png");
    public static final Bitmap guardian0 = PngLoader.loadBitmap("tex/guardian0.png");
    public static final Bitmap imp0 = PngLoader.loadBitmap("tex/imp0.png");
    public static final Bitmap imp1 = PngLoader.loadBitmap("tex/imp1.png");
    public static final Bitmap imp2 = PngLoader.loadBitmap("tex/imp2.png");
    public static final Bitmap impAtt0 = PngLoader.loadBitmap("tex/impAtt0.png");
    public static final Bitmap impAtt1 = PngLoader.loadBitmap("tex/impAtt1.png");
    public static final Bitmap impAtt2 = PngLoader.loadBitmap("tex/impAtt2.png");
    public static final Bitmap impHurt0 = PngLoader.loadBitmap("tex/impHurt0.png");
    public static final Bitmap impHurt1 = PngLoader.loadBitmap("tex/impHurt1.png");
    public static final Bitmap impHurt2 = PngLoader.loadBitmap("tex/impHurt2.png");
    public static final Bitmap impHeal0 = PngLoader.loadBitmap("tex/impHeal0.png");
    public static final Bitmap impHeal1 = PngLoader.loadBitmap("tex/impHeal1.png");
    public static final Bitmap impHeal2 = PngLoader.loadBitmap("tex/impHeal2.png");
    public static final Bitmap torch0 = PngLoader.loadBitmap("tex/torch0.png");
    public static final Bitmap torch1 = PngLoader.loadBitmap("tex/torch1.png");
    public static final Bitmap torch2 = PngLoader.loadBitmap("tex/torch2.png");

	// particles
    public static final Bitmap blood0 = PngLoader.loadBitmap("tex/blood0.png");
    public static final Bitmap blood1 = PngLoader.loadBitmap("tex/blood1.png");
    public static final Bitmap blood2 = PngLoader.loadBitmap("tex/blood2.png");
    public static final Bitmap blood3 = PngLoader.loadBitmap("tex/blood3.png");
    public static final Bitmap stone0 = PngLoader.loadBitmap("tex/stone0.png");
    public static final Bitmap stone1 = PngLoader.loadBitmap("tex/stone1.png");
    public static final Bitmap stone2 = PngLoader.loadBitmap("tex/stone2.png");
    public static final Bitmap stone3 = PngLoader.loadBitmap("tex/stone3.png");
    public static final Bitmap poof0 = PngLoader.loadBitmap("tex/poof0.png");
    public static final Bitmap poof1 = PngLoader.loadBitmap("tex/poof1.png");
    public static final Bitmap ember0 = PngLoader.loadBitmap("tex/ember0.png");
    public static final Bitmap ember1 = PngLoader.loadBitmap("tex/ember1.png");
    public static final Bitmap health0 = PngLoader.loadBitmap("tex/health0.png");
    public static final Bitmap health1 = PngLoader.loadBitmap("tex/health1.png");
    public static final Bitmap splinter0 = PngLoader.loadBitmap("tex/splinter0.png");
    public static final Bitmap splinter1 = PngLoader.loadBitmap("tex/splinter1.png");
    public static final Bitmap drop = PngLoader.loadBitmap("tex/drop.png");

    // screen
    public static final Bitmap screenSpear0 = PngLoader.loadBitmap("tex/screenSpear0.png");
    public static final Bitmap screenSpear1 = PngLoader.loadBitmap("tex/screenSpear1.png");
    public static final Bitmap screenSpear2 = PngLoader.loadBitmap("tex/screenSpear2.png");
    public static final Bitmap screenAxe0 = PngLoader.loadBitmap("tex/screenAxe0.png");
    public static final Bitmap screenAxe1 = PngLoader.loadBitmap("tex/screenAxe1.png");
    public static final Bitmap screenKnife0 = PngLoader.loadBitmap("tex/screenKnife0.png");
    public static final Bitmap screenKnife1 = PngLoader.loadBitmap("tex/screenKnife1.png");
    public static final Bitmap screenKnife2 = PngLoader.loadBitmap("tex/screenKnife2.png");
    public static final Bitmap screenWand0 = PngLoader.loadBitmap("tex/screenWand0.png");
    public static final Bitmap screenWand1 = PngLoader.loadBitmap("tex/screenWand1.png");
    public static final Bitmap screenWand2 = PngLoader.loadBitmap("tex/screenWand2.png");
    public static final Bitmap screenWand3 = PngLoader.loadBitmap("tex/screenWand3.png");
    public static final Bitmap screenWand4 = PngLoader.loadBitmap("tex/screenWand4.png");
    public static final Bitmap screenXbow0 = PngLoader.loadBitmap("tex/screenXbow0.png");
    public static final Bitmap screenXbow1 = PngLoader.loadBitmap("tex/screenXbow1.png");
    public static final Bitmap bag = PngLoader.loadBitmap("tex/bag.png");
}

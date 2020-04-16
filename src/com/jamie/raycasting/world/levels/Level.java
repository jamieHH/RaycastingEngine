package com.jamie.raycasting.world.levels;

import java.util.*;

import com.jamie.jamapp.Bitmap;
import com.jamie.jamapp.Sfx;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.entities.*;
import com.jamie.raycasting.entities.mobs.*;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.raycasting.world.Logic;
import com.jamie.raycasting.world.World;
import com.jamie.raycasting.world.blocks.*;

public abstract class Level
{
    public String name = "";

    private Block[] blocks;
    private List<Entity> entities = new ArrayList<>();
    private List<Logic> logicInstances = new ArrayList<>();

    protected int sizeX;
    protected int sizeZ;
    public int height = 1;
    public int fogColor = 0x000000;
    public boolean isOutside = false;

    public double spawnX;
    public double spawnZ;

    public World world;

    protected Bitmap getFloorTexture() {
        return Texture.floor;
    }

    protected Bitmap getCeilingTexture() {
        return Texture.floor;
    }

    protected Bitmap getWallTexture() {
        return Texture.wall;
    }

    // static blocks. TODO: check if is performance optimal.
    // Add these to array and loop through array for ticks
    protected final Block NullBlock = new NullBlock();
    protected final WaterBlock WaterBlock = new WaterBlock(getCeilingTexture());
    protected final TorchBlock TorchBlock = new TorchBlock(getFloorTexture(), getCeilingTexture());
    protected final SolidBlock ShrubsBlock = new SolidBlock(Texture.leaves);
    protected final SolidBlock WallBlock = new SolidBlock(getWallTexture());
    protected final SolidBlock ShelfBlock = new SolidBlock(Texture.shelf);
    public final AirBlock AirBlock = new AirBlock(getFloorTexture(), getCeilingTexture());
    protected final AirBlock GrassBlock = new AirBlock(Texture.grass, getCeilingTexture());
    protected final AirBlock StonePathBlock = new AirBlock(Texture.stonePath, getCeilingTexture());
    protected final AirBlock AltStonePathBlock = new AirBlock(Texture.altStonePath, getCeilingTexture());
    protected final SolidSpriteBlock PillarBlock = new SolidSpriteBlock(getFloorTexture(), getCeilingTexture(), Texture.pillar);
    protected final SolidSpriteBlock BarsBlock = new SolidSpriteBlock(getFloorTexture(), getCeilingTexture(), Texture.bars);
    protected final SolidSpriteBlock TreeBlock = new SolidSpriteBlock(Texture.grass, getCeilingTexture(), Texture.tree);
    protected final AirSpriteBlock GraveBlock = new AirSpriteBlock(Texture.dirt, getCeilingTexture(), Texture.grave);
    protected final AirSpriteBlock LampBlock = new AirSpriteBlock(Texture.stonePath, getCeilingTexture(), new Sprite(new Bitmap[] {
            Texture.lamp0,
            Texture.lamp1,
            Texture.lamp2,
            Texture.lamp3,
    }));
    protected final AirSpriteBlock CeilDripBlock = new AirSpriteBlock(Texture.wetFloor, Texture.floor, new Sprite(new Bitmap[] {
            Texture.drip0,
            Texture.drip0,
            Texture.drip1,
            Texture.drip1,
            Texture.drip2,
            Texture.drip3,
            Texture.drip4,
            Texture.drip0,
    }));
    protected final SolidSpriteBlock SpinningDummyBlock = new SolidSpriteBlock(getFloorTexture(), getCeilingTexture(), new Sprite(new Bitmap[] {
            Texture.spinningDummy0,
            Texture.spinningDummy1,
            Texture.spinningDummy2,
            Texture.spinningDummy3,
            Texture.spinningDummy4,
    }));
    protected final SolidSpriteBlock SignBlock = new SolidSpriteBlock(Texture.floor, Texture.floor, new Sprite(new Bitmap[] {
            Texture.signN,
            Texture.signW,
            Texture.signS,
            Texture.signE,
    }));


    public void setBlock(int x, int z, Block block) {
        if (block instanceof FunctionBlock) {
            ((FunctionBlock) block).level = this;
            ((FunctionBlock) block).gridX = x;
            ((FunctionBlock) block).gridZ = z;
        }

        blocks[x + z * sizeX] = block;
    }

    public Block getBlock(int x, int z) {
        if (x < 0 || z < 0 || x >= sizeX || z >= sizeZ) {
            return NullBlock;
        }

        return blocks[x + z * sizeX];
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void create(int sizeX, int sizeZ, int[] pixels) {
        this.world = Client.getWorld();
        this.sizeX = sizeX;
        this.sizeZ = sizeZ;
        this.blocks = new Block[sizeX * sizeZ];

        int switchCount = 1;
        int levelPortalCount = 1;
        int triggerBlockCount = 1;

        for (int z = 0; z < sizeZ; z++) {
            for (int x = 0; x < sizeX; x++) {
                int col = pixels[z + x * sizeX] & 0xFFFFFF;
                if (col == 0xFFFF00) {
                    setDefaultSpawn(x + 0.5, z + 0.5);
                }

                Block block = getBlockByColour(col);
                if (block instanceof SwitchBlock) {
                    ((SwitchBlock) block).id = switchCount;
                    switchCount++;
                } else if (block instanceof LevelPortalBlock) {
                    ((LevelPortalBlock) block).id = levelPortalCount;
                    levelPortalCount++;
                } else if (block instanceof TriggerableBlock) {
                    ((TriggerableBlock) block).id = triggerBlockCount;
                    triggerBlockCount++;
                }
                setBlock(x, z, block);
            }
        }

        for (int zb = 0; zb < sizeZ; zb++) {
            for (int xb = 0; xb < sizeX; xb++) {
                int col = pixels[zb + xb * sizeX] & 0xFFFFFF;

                Entity ent = getEntByColour(col);
                if (ent != null) {
                    addEntity(ent, xb + 0.5, zb + 0.5);
                }
            }
        }

        postCreate();
    }

    protected abstract void postCreate();

    public abstract void switchLevel(Entity entity, int id);

    public void triggerBlock(int id) {
        for (Block block : blocks) {
            if (block instanceof TriggerableBlock) {
                if (((TriggerableBlock) block).id == id) {
                    ((TriggerableBlock) block).trigger();
                }
            }
        }
    }

    public void triggerBlock(String reference) {
        for (Block block : blocks) {
            if (block instanceof TriggerableBlock) {
                TriggerableBlock tBlock = (TriggerableBlock) block;
                if (tBlock.reference != null && tBlock.reference.equals(reference)) {
                    tBlock.trigger();
                }
            }
        }
    }

    public void setBlockState(String reference, boolean state) {
        for (Block block : blocks) {
            if (block instanceof TriggerableBlock) {
                TriggerableBlock tBlock = (TriggerableBlock) block;
                if (tBlock.reference != null && tBlock.reference.equals(reference)) {
                    tBlock.setState(state);
                }
            }
        }
    }

    public void triggerLogic(String reference) {
        for (Logic instance : logicInstances) {
            if (instance.reference != null && instance.reference.equals(reference)) {
                instance.trigger();
            }
        }
    }

    public void tick() {
        NullBlock.tick();
        AirBlock.tick();
        TorchBlock.tick();
        WallBlock.tick();
        PillarBlock.tick();
        LampBlock.tick();
        BarsBlock.tick();
        TreeBlock.tick();
        GrassBlock.tick();
        ShrubsBlock.tick();
        StonePathBlock.tick();
        AltStonePathBlock.tick();
        GraveBlock.tick();
        WaterBlock.tick();
        CeilDripBlock.tick();
        SpinningDummyBlock.tick();
        SignBlock.tick();
        for (Block block : blocks) {
            if (block instanceof FunctionBlock) {
                block.tick();
            }
        }

        for (Logic instance : logicInstances) {
            instance.tick();
        }

        for (int i = 0; i < countEntities(); i++) {
            getEntity(i).tick();
            if (getEntity(i).removed) {
                removeEntity(getEntity(i));
            }
        }
    }

    public void playSound(Sfx sound, double posX, double posZ) {
        if (Client.getPlayer() != null) {
            double viewDist = Client.getPlayer().viewDist;
            double dist = Math.hypot(Math.abs(posX - Client.getPlayer().posX), Math.abs(posZ - Client.getPlayer().posZ));
            double per = dist / viewDist;
            double volume = (per * -1) + 1;

            if (volume > 1) volume = 1;
            else if (volume < 0) volume = 0;
            sound.play(volume);
        }
    }

    public void addLogic(Logic logic) {
        logic.level = this;
        logicInstances.add(logic);
    }

    public void addEntity(Entity e, double x, double z) {
        e.setPosition(x, z);
        entities.add(e);
        e.level = this;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Entity getEntity(int i) {
        return getEntities().get(i);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
        e.level = null;
    }

    public int countEntities() {
        return entities.size();
    }


    public List<Mob> getMobEntities() {
        List<Mob> mobs = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e instanceof Mob) {
                mobs.add((Mob) (e));
            }
        }
        return mobs;
    }

    public Block getBlockByReference(String reference) {
        for (Block block : blocks) {
            if (block instanceof FunctionBlock) {
                if (((FunctionBlock) block).reference != null && ((FunctionBlock) block).reference.equals(reference)) {
                    return block;
                }
            }
        }

        return null;
    }

    public LevelPortalBlock getLevelPortalBlockById(int id) {
        for (Block block : blocks) {
            if (block instanceof LevelPortalBlock) {
                if (((LevelPortalBlock) block).id == id) {
                    return (LevelPortalBlock) block;
                }
            }
        }

        return null;
    }

    private Block getBlockByColour(int col) {
        if (col == 0x000000) return NullBlock;
        if (col == 0xFFFFFF) return WallBlock;
        if (col == 0xFFE1C4) return ShelfBlock;
        if (col == 0x7C5F36) return TorchBlock;
        if (col == 0x808080) return PillarBlock;
        if (col == 0x648480) return LampBlock;
        if (col == 0xC0C0C0) return BarsBlock;
        if (col == 0xB27400) return TreeBlock;
        if (col == 0xB8ECBE) return GrassBlock;
        if (col == 0x7F8800) return ShrubsBlock;
        if (col == 0x8BB28F) return StonePathBlock;
        if (col == 0xA2AFA4) return AltStonePathBlock;
        if (col == 0x9A9A9A) return GraveBlock;
        if (col == 0x0094FF) return WaterBlock;
        if (col == 0x217F74) return CeilDripBlock;
//        if (col == 0xA3723A) return SpinningDummyBlock;
        if (col == 0x7F3300) return new BridgeBlock(getCeilingTexture(), false);
        if (col == 0x7F334E) return new BridgeBlock(getCeilingTexture(), true);
        if (col == 0xA48080) return new DoorBlock(getFloorTexture(), getCeilingTexture(), false);
        if (col == 0x632A2A) return new StrongDoorBlock(getFloorTexture(), getCeilingTexture(), false);
        if (col == 0xE1AE4A) return new BoardsBlock(getFloorTexture(), getCeilingTexture(), false);
        if (col == 0x4C4C65) return new GateBlock(getFloorTexture(), getCeilingTexture(), "Grey Key");
        if (col == 0x7EC0C0) return new FountainBlock(getCeilingTexture(), true);
        if (col == 0xC80000) return new SwitchBlock();
        if (col == 0x873800) return new LevelPortalBlock();
        if (col == 0xFF6A00) return new LadderBlock(false);
        if (col == 0xB24700) return new LadderBlock(true);
        if (col == 0xEAEAEA) return new CrackedWallBlock(getFloorTexture(), getCeilingTexture());
        if (col == 0xC82A00) return new ProjectileSwitchBlock();
        if (col == 0xA78431) return new PressurePlateBlock(getCeilingTexture());
        return AirBlock;
    }

    private Entity getEntByColour(int col) {
        if (col == 0x804000) return new Bat(new ArtificialInputHandler());
        if (col == 0xFFFF71) return new Spirit(new ArtificialInputHandler());
        if (col == 0x8080C4) return new Imp(new ArtificialInputHandler());
        if (col == 0xC0EBC0) return new Guardian(new ArtificialInputHandler());
        if (col == 0xB06E23) return new BarrelEntity(BarrelEntity.getLootItem());
        if (col == 0xA3723A) return new ExplosiveBarrelEntity();
//        if (col == 0xB06E23) return new ChestEntity(ChestEntity.getLootItem());
        return null;
    }

    private void setDefaultSpawn(double x, double z) {
        spawnX = x;
        spawnZ = z;
    }

    public boolean blockContainsEntity(int x, int z) {
        for (Entity e : getEntities()) {
            if (e.isSolid) {
                if (e.isInside(x, z, x + 1, z + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<Entity> getEntitiesWithin(double x0, double z0, double x1, double z1) {
        List<Entity> entities = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e.isInside(x0, z0, x1, z1)) {
                entities.add(e);
            }
        }

        return entities;
    }

    public static Level makeRandomLevel(int sizeX, int sizeZ) {
        Level level = new RandomLevel();
        level.sizeX = sizeX;
        level.sizeZ = sizeZ;

        level.setDefaultSpawn(0.5, 0.5);

        level.blocks = new Block[sizeX * sizeZ];

        Random random = new Random();
        for (int z = 0; z < sizeZ; z++) {
            for (int x = 0; x < sizeX; x++) {
                Block block;
                if (random.nextInt(4) == 0) {
                    block = new SolidBlock(Texture.wall);
                } else {
                    if (random.nextInt(8) == 0) {
                        block = new SolidSpriteBlock(Texture.floor, Texture.floor, Texture.pillar);
                    } else {
                        block = new AirBlock(Texture.floor, Texture.floor);
                    }
                }

                level.setBlock(x, z, block);
            }
        }

        return level;
    }
}

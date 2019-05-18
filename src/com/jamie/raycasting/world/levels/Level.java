package com.jamie.raycasting.world.levels;

import java.util.*;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.Drop;
import com.jamie.raycasting.entities.mobs.*;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.raycasting.world.World;
import com.jamie.raycasting.world.blocks.*;

public abstract class Level
{
    public String name = "";

    private Block[] blocks;
    private List<Entity> entities = new ArrayList<Entity>();

    protected int sizeX;
    protected int sizeZ;
    public int height = 1;

    public double spawnX;
    public double spawnZ;

    public World world;

    // static blocks. TODO: check if is performance optimal.
    // Add these to array and loop through array for ticks
    protected static final Block Block = new Block();
    protected static final AirBlock AirBlock = new AirBlock();
    protected static final WallBlock WallBlock = new WallBlock();
    protected static final PillarBlock PillarBlock = new PillarBlock();
    protected static final LampBlock LampBlock = new LampBlock();
    protected static final BarsBlock BarsBlock = new BarsBlock();
    protected static final CobwebBlock CobwebBlock = new CobwebBlock();
    protected static final TreeBlock TreeBlock = new TreeBlock();
    protected static final GrassBlock GrassBlock = new GrassBlock();
    protected static final ShrubsBlock ShrubsBlock = new ShrubsBlock();
    protected static final StonePathBlock StonePathBlock = new StonePathBlock();
    protected static final AltStonePathBlock AltStonePathBlock = new AltStonePathBlock();
    protected static final GraveBlock GraveBlock = new GraveBlock();
    protected static final WaterBlock WaterBlock = new WaterBlock();
    protected static final CeilDripBlock CeilDripBlock = new CeilDripBlock();
    protected static final SpinningDummyBlock SpinningDummyBlock = new SpinningDummyBlock();
    protected Block defaultFloorBlock = AirBlock;


    public void create(Game game, int sizeX, int sizeZ, int[] pixels) { // Possibly not to do with the world generation?
        this.world = game.world;
        this.sizeX = sizeX;
        this.sizeZ = sizeZ;
        this.blocks = new Block[sizeX * sizeZ];

        int ladderCount = 1;
        int doorCount = 1;
        int buttonCount = 1;

        for (int z = 0; z < sizeZ; z++) {
            for (int x = 0; x < sizeX; x++) {
            int col = pixels[x + z * sizeX] & 0xFFFFFF;
                if (col == 0xFFFF00) {
                    setDefaultSpawn(x + 0.5, z + 0.5);
                }

                Block block = getBlockByColour(col);

                if (block instanceof LevelPortalBlock) { // level portals
                    ((LevelPortalBlock) block).id = ladderCount;
                    ladderCount++;
                }

                if (block instanceof DoorBlock) { // doors
                    ((DoorBlock) block).id = doorCount;
                    doorCount++;
                }

                if (block instanceof ButtonBlock) { // buttons
                    ((ButtonBlock) block).id = buttonCount;
                    buttonCount++;
                }

                setBlock(x, z, block);
            }
        }

        for (int zb = 0; zb < sizeZ; zb++) {
            for (int xb = 0; xb < sizeX; xb++) {
                int col = pixels[xb + zb * sizeX] & 0xFFFFFF;

                Mob mob = getMobByColour(col);
                if (mob != null) {
                    addEntity(mob, xb + 0.5, zb + 0.5);
                }
            }
        }

        postCreate();
    }

    protected abstract void postCreate();

    public abstract void switchLevel(Mob mob, int id);

    public void triggerBlock(int id) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] instanceof TriggerableBlock) {
                if (((TriggerableBlock) blocks[i]).id == id) {
                    ((TriggerableBlock) blocks[i]).trigger();
                }
            }
        }
    }

    public void tick() {
        Block.tick();
        AirBlock.tick();
        WallBlock.tick();
        PillarBlock.tick();
        LampBlock.tick();
        BarsBlock.tick();
        CobwebBlock.tick();
        TreeBlock.tick();
        GrassBlock.tick();
        ShrubsBlock.tick();
        StonePathBlock.tick();
        AltStonePathBlock.tick();
        GraveBlock.tick();
        WaterBlock.tick();
        CeilDripBlock.tick();
        SpinningDummyBlock.tick();
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] instanceof FunctionBlock) {
                blocks[i].tick();
            }
        }

        for (int i = 0; i < countEntities(); i++) {
            getEntity(i).tick();

            if (getEntity(i).removed) {
                removeEntity(getEntity(i));
            }
        }
    }

    public void setBlock(int x, int z, Block block) {
        if (block instanceof FunctionBlock) {
            ((FunctionBlock) block).level = this;
            ((FunctionBlock) block).gridX = x;
            ((FunctionBlock) block).gridZ = z;
        }

        blocks[x + z * sizeX] = block;
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
        List<Mob> mobs = new ArrayList<Mob>();
        for (int i = 0; i < countEntities(); i++) {
            if (getEntity(i) instanceof Mob) {
                mobs.add((Mob) (getEntity(i)));
            }
        }
        return mobs;
    }

    public Mob getMobEntity(int i) {
        return getMobEntities().get(i);
    }

    public int countMobs() {
        return getMobEntities().size();
    }


    public List<Drop> getDropEntities() {
        List<Drop> drops = new ArrayList<Drop>();
        for (int i = 0; i < countEntities(); i++) {
            if (getEntity(i) instanceof Drop) {
                drops.add((Drop) (getEntity(i)));
            }
        }
        return drops;
    }

    public Drop getDropEntity(int i) {
        return getDropEntities().get(i);
    }

    public int countDrops() {
        return getDropEntities().size();
    }

    public Block getBlock(int x, int z) {
        if (x < 0 || z < 0 || x >= sizeX || z >= sizeZ) {
            return Block;
        }

        return blocks[x + z * sizeX];
    }

    public Block getBlockByReference(String reference) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] instanceof FunctionBlock) {
                if (((FunctionBlock) blocks[i]).reference == reference) {
                    return blocks[i];
                }
            }
        }

        return null;
    }

    public LevelPortalBlock getLevelPortalBlockById(int id) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] instanceof LevelPortalBlock) {
                if (((LevelPortalBlock) blocks[i]).id == id) {
                    return (LevelPortalBlock) blocks[i];
                }
            }
        }

        return null;
    }

    private Block getBlockByColour(int col) {
        if (col == 0x000000) return Block;
        if (col == 0xFFFFFF) return WallBlock;
        if (col == 0x808080) return PillarBlock;
        if (col == 0x648480) return LampBlock;
        if (col == 0xC0C0C0) return BarsBlock;
        if (col == 0xE0E0E0) return CobwebBlock;
        if (col == 0xB27400) return TreeBlock;
        if (col == 0xB8ECBE) return GrassBlock;
        if (col == 0x7F8800) return ShrubsBlock;
        if (col == 0x8BB28F) return StonePathBlock;
        if (col == 0xA2AFA4) return AltStonePathBlock;
        if (col == 0x9A9A9A) return GraveBlock;
        if (col == 0x0094FF) return WaterBlock;
        if (col == 0x217F74) return CeilDripBlock;
        if (col == 0xA3723A) return SpinningDummyBlock;
        if (col == 0x7F3300) return new BridgeBlock(false);
        if (col == 0x7F334E) return new BridgeBlock(true);
        if (col == 0xA48080) return new DoorBlock();
        if (col == 0x632A2A) return new StrongDoorBlock();
        if (col == 0xE1AE4A) return new BoardsBlock();
        if (col == 0x4C4C65) return new GateBlock("Grey Key");
        if (col == 0x7EC0C0) return new FountainBlock();
        if (col == 0xC80000) return new ButtonBlock();
        if (col == 0x873800) return new LevelPortalBlock();
        if (col == 0xFF6A00) return new LadderBlock(false);
        if (col == 0xB24700) return new LadderBlock(true);
        return defaultFloorBlock;
    }

    private Mob getMobByColour(int col) {
        if (col == 0x804000) return new Bat(new ArtificialInputHandler());
        if (col == 0xFFFF71) return new Spirit(new ArtificialInputHandler());
        if (col == 0x8080C4) return new Imp(new ArtificialInputHandler());
        if (col == 0xC0EBC0) return new Guardian(new ArtificialInputHandler());
        return null;
    }

    private void setDefaultSpawn(double x, double z) {
        spawnX = x;
        spawnZ = z;
    }

    public boolean blockContainsEntity(int x, int z) {
        for (int i = 0; i < countEntities(); i++) {
            Entity e = getEntity(i);
            if (e.isSolid) {
                if (e.isInside(x, z, x + 1, z + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<Entity> getEntitiesWithin(double x0, double z0, double x1, double z1) {
        List<Entity> entities = new ArrayList<Entity>();

        for (int i = 0; i < getEntities().size(); i++) {
            if (getEntities().get(i).isInside(x0, z0, x1, z1)) {
                entities.add(getEntities().get(i));
            }
        }

        return entities;
    }

    public List<Mob> getMobsWithin(double x0, double z0, double x1, double z1) {
        List<Mob> mobs = new ArrayList<Mob>();

        for (int i = 0; i < getMobEntities().size(); i++) {
            if (getMobEntities().get(i).isInside(x0, z0, x1, z1)) {
                mobs.add(getMobEntities().get(i));
            }
        }

        return mobs;
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
                    block = new WallBlock();
                } else {
                    if (random.nextInt(8) == 0) {
                        block = new PillarBlock();
                    } else {
                        block = new AirBlock();
                    }
                }

                level.setBlock(x, z, block);
            }
        }

        return level;
    }
}

package com.jamie.raycasting.levels;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Bat;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Spirit;
import com.jamie.raycasting.input.ArtificialInputHandler;
import com.jamie.raycasting.levels.blocks.*;

public class Level {
	public String name = "";

	public Block[] blocks;
	private List<Entity> entities = new ArrayList<Entity>();

	private int sizeX;
	private int sizeZ;
    public int levelHeight = 8;
	
	public double spawnX;
	public double spawnZ;
	
	protected Game game;
	public Mob player;

	private void setupLevelClass(Game game, String name, int sizeX, int sizeZ, int[] pixels) {
		this.name = name;
		this.game = game;
		this.sizeX = sizeX;
		this.sizeZ = sizeZ;
        player = game.player;

        blocks = new Block[sizeX * sizeZ];

		int ladderCount = 1;
		for (int z = 0; z < sizeZ; z++) {
			for (int x = 0; x < sizeX; x++) {
				int col = pixels[z + x * sizeX] & 0xFFFFFF;

				Block block = getBlockByColour(col);

                if (block instanceof LadderBlock) {
                    block.id = ladderCount;
                    ladderCount++;
                }

				block.level = this;
				block.gridX = x;
				block.gridZ = z;
                blocks[x + z * sizeX] = block;
				
				decorateBlock(x, z, block, col);
			}
		}

        for (int zb = 0; zb < sizeZ; zb++) {
            for (int xb = 0; xb < sizeX; xb++) {
                int col = pixels[zb + xb * sizeX] & 0xffffff;

                Mob mob = getMobByColour(col);
                if (mob != null) {
                    addEntity(mob);
                    mob.level = this;

                    mob.posX = (xb * 16) + 8;
                    mob.posZ = (zb * 16) + 8;
                }
            }
        }
	}
	
	public void tick() {
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].tick();
        }

		for (int i = 0; i < countEntities(); i++) {
            getEntity(i).tick();

			if (getEntity(i).removed) {
                removeEntity(getEntity(i));
            }
		}
	}
	
	public void addEntity(Entity e) {
	    entities.add(e);
	}

    public List<Entity> getEntities() {
        return entities;
    }

    public Entity getEntity(int i) {
        return entities.get(i);
    }
	
	public void removeEntity(Entity e) {
        entities.remove(e);
	}

    public int countEntities() {
        return entities.size();
    }
	
	public Block getBlock(int x, int z) {
		if (x < 0 || z < 0 || x >= sizeX || z >= sizeZ) {
			return Block.boundaryBlock;
		}
		
		return blocks[x + z * sizeX];
	}
	
	private Block getBlockByColour(int col) {
		if (col == 0xFFFFFF) return new SolidBlock();
		if (col == 0x808080) return new PillarBlock();
		if (col == 0xC0C0C0) return new BarsBlock();
		if (col == 0xA3723A) return new SpinningDummyBlock();
		if (col == 0xA48080) return new DoorBlock();
		if (col == 0xE0E0E0) return new CobwebBlock();
		if (col == 0x0094FF) return new WaterBlock();
		if (col == 0xB27400) return new TreeBlock();
		if (col == 0xB8ECBE) return new GrassBlock();
		if (col == 0x7F8800) return new ShrubsBlock();
		if (col == 0x8BB28F) return new StonePathBlock();
		if (col == 0xE1AE4A) return new BoardsBlock();
		if (col == 0x217F74) return new CeilDripBlock();
		if (col == 0x7EC0C0) return new FountainBlock();
		if (col == 0x9A9A9A) return new GraveBlock();

        if (col == 0xFF6A00) return new LadderBlock(false);
        if (col == 0xB24700) return new LadderBlock(true);
		return new Block();
	}

    private Mob getMobByColour(int col) {
        if (col == 0x804000) return new Bat(new ArtificialInputHandler());
        if (col == 0xFFFF71) return new Spirit(new ArtificialInputHandler());
        return null;
    }

    private void decorateBlock(int x, int z, Block block, int col) {
        if (col == 0xFFFF00) {
            spawnX = (x * 16) + 8;
            spawnZ = (z * 16) + 8;
        }
    }

    public void switchLevel(int id) {}

    public void setSpawn(int id) {
        for (int z = 0; z < sizeZ; z++) {
            for (int x = 0; x < sizeX; x++) {
                Block b = blocks[x + z * sizeX];
                if (b.id == id && b instanceof LadderBlock) {
                    System.out.println("Spawn found at grid: " + x + ", " + z);
                    spawnX = (x * 16) + 8;
                    spawnZ = (z * 16) + 8;
                    return;
                }
            }
        }
    }

    public boolean blockContainsEntity(int x, int z) {
        Block block = getBlock(x, z);
        int bX0 = block.gridX * 16;
        int bX1 = (block.gridX * 16) + 16;
        int bZ0 = block.gridZ * 16;
        int bZ1 = (block.gridZ * 16) + 16;
        for (int i = 0; i < countEntities(); i++) {
            Entity e = getEntity(i);
            if (e.solid) {
                if ((e.posX + e.radius > bX0 && e.posX - e.radius < bX1) && (e.posZ + e.radius > bZ0 && e.posZ - e.radius < bZ1)) {
                    return true;
                }
            }
        }
        return false;
    }
	
	public static Level getLoadLevel(Game game, String name) {
		if (game.loaded.containsKey(name)) return game.loaded.get(name); 
		
		try {
			BufferedImage img = ImageIO.read(new FileInputStream("res/levels/" + name + ".png"));

			int w = img.getWidth();
			int h = img.getHeight();
			int[] pixels = new int[w * h];
			img.getRGB(0, 0, w, h, pixels, 0, w);

			Level level = Level.getByName(name);
			level.setupLevelClass(game, name, w, h, pixels);
			game.loaded.put(name, level);

			return level;
		} catch (Exception e) {
			System.out.println("Failed to get load level:" + name + "!!");
			throw new RuntimeException(e);
		}
	}
	
	private static Level getByName(String name) {
		try {
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			return (Level) Class.forName("com.jamie.raycasting.levels." + name + "Level").newInstance();
		} catch (Exception e) {
			System.out.println("Failed to load levels by name!");
			throw new RuntimeException(e);
		}
	}

    public void generateRandomLevel(int sizeX, int sizeZ) {
        this.sizeX = sizeX;
        this.sizeZ = sizeZ;

        spawnX = 8;
        spawnZ = 8;

        blocks = new Block[sizeX * sizeZ];
        Random random = new Random();
        for (int z = 0; z < sizeZ; z++) {
            for (int x = 0; x < sizeX; x++) {
                Block block;
                if (random.nextInt(4) == 0) {
                    block = new SolidBlock();
                } else {
                    if (random.nextInt(8) == 0) {
                        block = new PillarBlock();
                    } else {
                        block = new Block();
                    }
                }
                blocks[x + z * sizeX] = block;
            }
        }
    }
}

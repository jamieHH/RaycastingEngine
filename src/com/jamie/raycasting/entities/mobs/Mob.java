package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.particles.BloodParticle;
import com.jamie.raycasting.entities.particles.HealthParticle;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.items.Item;
import com.jamie.raycasting.items.consumables.Consumable;
import com.jamie.raycasting.world.blocks.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity
{
    protected boolean wallCollide = true;
    protected boolean entCollide = true;

    // details
    protected Particle hurtParticle;

    // distances
    public double baseReach = 1.5;
    public double viewDist = 4;

    // actions
    public InputHandler input;
    private int useTicks = 0;
    protected int useWait = 15;

    private List<String> factions = new ArrayList<String>();
    protected Mob target;

    // movement
	private double rotationMove;
    private double moveX, moveZ;

    protected double rotationSpeed = 0.03;
    protected double walkSpeed = 0.0125;
    protected double runSpeed = 0.0125;
    protected double crouchSpeed = 0.0125;

    protected double camHeightMod = 0.652;
    protected double crouchHeightMod = 0.25;

    public double camY = 0;
    public double yBob = 0;
    private int bobTime = 0;

	// stats
    protected int baseDamage = 1;

	public int hurtTime = 0;
	public int maxHealth = 10;
	public int health = 10;

	private int dieTime = 30;
	private boolean isDieing = false;
    public boolean isDead = false;

    // items
    private List<Item> items = new  ArrayList<Item>();
    private int rightHandItemIndex = 0;
    public boolean rightHandEmpty = true;


    public ArrayList<String> hudHeadings = new ArrayList<String>();
    public int hudHeadingsTicks = 120;

    public Mob(InputHandler input) {
        input.setMob(this);
        this.input = input;

        health = maxHealth;
        camY = camHeightMod;

        addIdleSprite(new Sprite(Texture.none));
        addActionSprite(new Sprite(Texture.none));
        addHurtSprite(new Sprite(Texture.none));
        addDeathSprite(new Sprite(Texture.none));
        addHealSprite(new Sprite(Texture.none));
    }

    public void tick() {
        super.tick();

        for (int i = 0; i < items.size(); i++) {
            getItem(i).tick();
        }

        if (!(input instanceof UserInputHandler)) {
            input.tick();
        }

        if (hurtTime > 0) {
            hurtTime--;
        }

        if (useTicks > 0) {
            useTicks--;
        }

        if (hudHeadingsTicks > 0) {
            hudHeadingsTicks--;
        } else {
            hudHeadingsTicks = 120;
            if (hudHeadings.size() != 0) {
                hudHeadings.remove(0);
            }
        }

        if (isDieing) {
            unequipRightHand();
            camY = 0.125;

            if (!isDead) {
                dieTime--;
                if (dieTime == 0) {
                    for (int i = 0; i < 6 ; i++) {
                        PoofParticle particle = new PoofParticle(posX, posZ);
                        level.addEntity(particle);
                    }

                    isDead = true;
                }
            } else {
                if (!(this instanceof Player)) {
                    remove();
                }
            }
        } else {
            if (input.action) {
                if (useTicks == 0) {
                    useTicks = getUseWait();

                    activate();
                }
            }

            for (int i = 0; i < level.countDrops(); i++) {
                if (contains(level.getDropEntity(i).posX, level.getDropEntity(i).posZ)) {
                    addItem(level.getDropEntity(i).item);
                    level.getDropEntity(i).remove();
                }
            }

            doMovements();
        }
    }

    private void die() {
        runSpriteSet("death");
        isDieing = true;
    }

    public void addHudHeading(String s) {
        hudHeadings.add(s);

        if (hudHeadings.size() > 5) {
            hudHeadings.remove(0);
        }

        hudHeadingsTicks = 120;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setUser(this);
        addHudHeading("Picked up " + item.name);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setUser(null);
        addHudHeading(item.name + " removed");
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(int i) {
        return items.get(i);
    }

    public int countItems() {
        return items.size();
    }

    public Item getRightHandItem() {
        if (countItems() > 0 && !rightHandEmpty) {
            return getItems().get(rightHandItemIndex);
        }
        return null;
    }

    public void unequipRightHand() {
        setRightHandItemIndex(-1);
    }

    public int getRightHandItemIndex() {
        return rightHandItemIndex;
    }

    public void setRightHandItemIndex(int i) {
        if (i < 0) {
            rightHandEmpty = true;
            rightHandItemIndex = 0;
        } else {
            rightHandEmpty = false;
            rightHandItemIndex = i;
        }
    }

    public double getRightHandReach() {
        if (countItems() > 0 && !rightHandEmpty) {
            return baseReach + getRightHandItem().reach;
        }
        return baseReach;
    }

    public int getDamage() {
        if (getRightHandItem() != null) {
            return baseDamage + getRightHandItem().damage;
        }
        return baseDamage;
    }

    public void addHurtSprite(Sprite s) {
        addSpriteSet("hurt", s);
    }

    public void addActionSprite(Sprite s) {
        addSpriteSet("action", s);
    }

    public void addDeathSprite(Sprite s) {
        addSpriteSet("death", s);
    }

    public void addHealSprite(Sprite s) {
        addSpriteSet("heal", s);
    }

    private void doMovements() {
        camY = camHeightMod;

        double moveSpeed;
        if (input.crouch) {
            camY -= crouchHeightMod;
            moveSpeed = crouchSpeed;
        } else if (input.run) {
            moveSpeed = runSpeed;
        } else {
            moveSpeed = walkSpeed;
        }

        if (input.forward) moveZ += moveSpeed;
        if (input.back) moveZ -= moveSpeed;
        if (input.left) moveX -= moveSpeed;
        if (input.right) moveX += moveSpeed;
        if (input.rotLeft) rotationMove -= rotationSpeed;
        if (input.rotRight) rotationMove += rotationSpeed;

        // View bob:
        if ((input.forward ^ input.back) || (input.left ^ input.right)) {
            bobTime++;
            yBob += Math.sin(bobTime / (3 - (moveSpeed * 10))) * 0.01;
        } else {
            bobTime = 0;
        }
        camY += yBob;
        yBob *= 0.75;

        // Do movements:
        rotation += rotationMove;
        rotationMove *= 0.5;

        move(moveX * Math.cos(rotation) + moveZ * Math.sin(rotation), moveZ * Math.cos(rotation) - moveX * Math.sin(rotation));
    }

    private boolean isWallBlocked(double x, double z) {
        int x0 = (int) (Math.floor(x + radius));
        int x1 = (int) (Math.floor(x - radius));
        int z0 = (int) (Math.floor(z + radius));
        int z1 = (int) (Math.floor(z - radius));

        if (level.getBlock(x0, z0).isSolid) return true;
        if (level.getBlock(x1, z0).isSolid) return true;
        if (level.getBlock(x0, z1).isSolid) return true;
        if (level.getBlock(x1, z1).isSolid) return true;
        return false;
    }

    private boolean isEntityBlocked(double x, double z) {
        for (int i = 0; i < level.countEntities(); i++) {
            Entity e = level.getEntity(i);
            if (e.isSolid) {
                double entX = e.posX;
                double entZ = e.posZ;
                double entRadius = e.radius;
                if (level.getEntity(i) != this) {
                    if (((Math.abs(x - entX)) - entRadius < radius) && ((Math.abs(z - entZ)) - entRadius < radius)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void moveWallEntColl(double nextX, double nextZ) {
        if (isWallBlocked(posX + nextX, posZ) || isEntityBlocked(posX + nextX, posZ)) {
            nextX = 0;
        }
        posX += nextX;

        if (wallCollide) {
            if (isWallBlocked(posX, posZ + nextZ) || isEntityBlocked(posX, posZ + nextZ)) {
                nextZ = 0;
            }
        }
        posZ += nextZ;
    }

    private void moveWallColl(double nextX, double nextZ) {
        if (isWallBlocked(posX + nextX, posZ)) {
            nextX = 0;
        }
        posX += nextX;


        if (isWallBlocked(posX, posZ + nextZ)) {
            nextZ = 0;
        }

        posZ += nextZ;
    }

    private void moveEntColl(double nextX, double nextZ) {
        if (isEntityBlocked(posX + nextX, posZ)) {
            nextX = 0;
        }
        posX += nextX;

        if (isEntityBlocked(posX, posZ + nextZ)) {
            nextZ = 0;
        }
        posZ += nextZ;
    }

    private void moveNoColl(double nextX, double nextZ) {
        posX += nextX;
        posZ += nextZ;
    }

	private void move(double nextX, double nextZ) {
        if (wallCollide) {
            if (entCollide) {
                moveWallEntColl(nextX, nextZ);
            } else {
                moveWallColl(nextX, nextZ);
            }
        } else {
            if (entCollide) {
                moveEntColl(nextX, nextZ);
            } else {
                moveNoColl(nextX, nextZ);
            }
        }

        moveX *= 0.5;
        moveZ *= 0.5;
	}

    public void lookTowards(double x, double z) {
        double xDiff = x - posX;
        double zDiff = z - posZ;
        rotation = Math.atan2(xDiff, zDiff);
    }

    public void push(double x, double z) {
        double nextZ = z * Math.cos(-rotation) - x * Math.sin(-rotation);
        double nextX = x * Math.cos(-rotation) + z * Math.sin(-rotation);

        moveX += nextX;
        moveZ += nextZ;
    }

    public void hurt(Mob source, int damage) {
        if (hurtTime > 0 || damage <= 0 || isDieing) return;

        runSpriteSet("hurt");

        yBob -= 0.8;
        health -= damage;
        hurtTime = 30;

        double mx = (posX - source.posX) / 4;
        double mz = (posZ - source.posZ) / 4;
        push(mx, mz);
        rotationMove += (Math.random() - 0.5);


        for (int i = 0; i < 2; i++) {
            BloodParticle p = new BloodParticle(posX, posZ);
            level.addEntity(p);
        }

        if (health <= 0) {
            die();
        }
    }

    private void activate() {
            if (getRightHandItem() != null) {
                getRightHandItem().use();
            }

            if (getRightHandItem() instanceof Consumable) {
                return;
            } else {
                runSpriteSet("action");
            }


        List<Entity> closeEnts = new ArrayList<Entity>();
        for (int e = 0; e < level.countEntities(); e++) {
            Entity ent = level.getEntity(e);
            if (distanceFrom(ent.posX, ent.posZ) < getRightHandReach()) {
                closeEnts.add(ent);
            }
        }


        double blockUseDist = getRightHandReach();
        int divs = (int) (getRightHandReach() * 100);
        double xa = blockUseDist * Math.sin(rotation);
        double za = blockUseDist * Math.cos(rotation);

        for (int i = 0; i < divs; i++) {
            double xx = posX + xa * i / divs;
            double zz = posZ + za * i / divs;
            for (int b = 0; b < closeEnts.size(); b++) {
                Entity ent = closeEnts.get(b);
                if (ent instanceof Mob && ent != this) {
                    if (closeEnts.get(b).contains(xx, zz)) {
                        ((Mob) ent).hurt(this, getDamage());
                        return;
                    }
                }
            }

            int xb = (int) xx;
            int zb = (int) zz;
            if (xb != (int) (posX) || zb != (int) (posZ)) {
                Block block = level.getBlock(xb, zb);
                if (block.use(this)) return;

                if (block.isSolid) return;
            }
        }
    }

    public int getUseWait() {
        if (getRightHandItem() != null) {
            return getRightHandItem().useWait;
        }
        return useWait;
    }

    public List<String> getFactions() {
        return factions;
    }

    public void addFaction(String faction) {
        this.factions.add(faction);
    }

    public void addHealth(int modifier) {
        runSpriteSet("heal");
        for (int i = 0; i < 4; i++) {
            HealthParticle p = new HealthParticle(posX, posZ);
            level.addEntity(p);
        }

        if (health + modifier > maxHealth) {
            health = maxHealth;
        } else {
            health += modifier;
        }
    }
}

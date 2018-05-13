package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.mobEffects.MobEffect;
import com.jamie.raycasting.entities.particles.BloodParticle;
import com.jamie.raycasting.entities.particles.HealthParticle;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.items.Inventory;
import com.jamie.raycasting.items.Item;
import com.jamie.raycasting.world.blocks.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity
{
    protected boolean isFloating = false;

    // details
    protected Particle hurtParticle;

    // distances
    public double baseReach = 1.5;
    public double viewDist = 4;

    // actions
    public InputHandler input;
    public boolean isUsingMenu = false;
    private int useTicks = 0;
    protected int useWait = 15;

    private List<String> factions = new ArrayList<String>();
    protected Mob target;

    // movement
	private double rotationMove;
    private double moveX, moveZ;
    private double friction = 0.25;

    protected double rotationSpeed = 0.03;
    protected double walkSpeed = 0.03;
    protected double runSpeed = 0.03;
    protected double crouchSpeed = 0.03;

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
    public Inventory inventory = new Inventory();
    private int rightHandItemIndex = 0;
    public boolean rightHandEmpty = true;

    public List<MobEffect> mobEffects = new ArrayList<MobEffect>();

    public ArrayList<String> hudHeadings = new ArrayList<String>();
    public int hudHeadingsTicks = 120;

    public Mob(InputHandler input) {
        input.setMob(this);
        this.input = input;

        health = maxHealth;
        camY = camHeightMod;
    }

    public void tick() {
        super.tick();

        if (!(input instanceof UserInputHandler)) {
            input.tick();
        }

        // inventory tick
        for (int i = 0; i < inventory.countItems(); i++) {
            inventory.getItem(i).tick(); // TODO: does each item need to tick (NO IT SHOULDENTS)

            if (inventory.getItem(i).removed) {
                if (i <= rightHandItemIndex) {
                    // prevent out of bounds held item exception
                    rightHandItemIndex--;
                }

                removeItem(inventory.getItem(i));
            }
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
            for (int i = 0; i < mobEffects.size(); i++) {
                mobEffects.get(i).tick();

                if (mobEffects.get(i).removed) {
                    removeMobEffect(mobEffects.get(i));
                }
            }

            for (int i = 0; i < level.countDrops(); i++) {
                if (contains(level.getDropEntity(i).posX, level.getDropEntity(i).posZ)) {
                    addItem(level.getDropEntity(i).item);
                    level.getDropEntity(i).remove();
                }
            }

            if (!isUsingMenu) {
                reciveInput();
            }

            doMovements();
        }
    }

    private void reciveInput() {
        if (input.action) {
            if (useTicks == 0) {
                useTicks = getUseWait();

                activate();
            }
        }

        double moveSpeed;
        if (input.crouch) {
            camY -= crouchHeightMod;
            moveSpeed = crouchSpeed; // change to moveSpeed multipliers
        } else if (input.run) {
            moveSpeed = runSpeed;
        } else {
            moveSpeed = walkSpeed;
        }
        moveSpeed *= friction;

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
    }

    private void doMovements() {
        camY = camHeightMod;
        camY += yBob;
        yBob *= 0.75;

        rotation += rotationMove;
        rotationMove *= 0.6;

        move(moveX * Math.cos(rotation) + moveZ * Math.sin(rotation), moveZ * Math.cos(rotation) - moveX * Math.sin(rotation));
        moveX *= 1 - friction;
        moveZ *= 1 - friction;
    }

    private boolean isWallBlocked(double x, double z) {
        if (isSolid) {
            int x0 = (int) (Math.floor(x + radius));
            int z0 = (int) (Math.floor(z + radius));
            int x1 = (int) (Math.floor(x - radius));
            int z1 = (int) (Math.floor(z - radius));

            Block block00 = level.getBlock(x0, z0);
            Block block10 = level.getBlock(x1, z0);
            Block block01 = level.getBlock(x0, z1);
            Block block11 = level.getBlock(x1, z1);

            if (block00.isSolid) return true;
            if (block10.isSolid) return true;
            if (block01.isSolid) return true;
            if (block11.isSolid) return true;

            if (!this.isFloating) {
                if (!block00.isWalkable) return true;
                if (!block10.isWalkable) return true;
                if (!block01.isWalkable) return true;
                if (!block11.isWalkable) return true;
            }
            return false;
        }

        return false;
    }

    private boolean isEntityBlocked(double x, double z) {
        if (isSolid) {
            for (int i = 0; i < level.countEntities(); i++) {
                Entity e = level.getEntity(i);
                if (e != this && e.isSolid) {
                    double entX = e.posX;
                    double entZ = e.posZ;
                    double entRadius = e.radius;
                    if (((Math.abs(x - entX)) - entRadius < radius) && ((Math.abs(z - entZ)) - entRadius < radius)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void move(double nX, double nZ) {
        int divs = 100;
        for (int i = 0; i < divs; i++) {
            double nextX = nX / divs;
            if (isWallBlocked(posX + nextX, posZ) || isEntityBlocked(posX + nextX, posZ)) {
                nextX = 0;
            }
            posX += nextX;

            double nextZ = nZ / divs;
            if (isWallBlocked(posX, posZ + nextZ) || isEntityBlocked(posX, posZ + nextZ)) {
                nextZ = 0;
            }
            posZ += nextZ;
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

    public void addMobEffect(MobEffect mobEffect) {
        mobEffects.add(mobEffect);
        mobEffect.setMob(this);
        addHudHeading(mobEffect.name +" effect added");
    }

    public void removeMobEffect(MobEffect mobEffect) {
        mobEffects.remove(mobEffect);
        mobEffect.setMob(null);
    }

    public void addItem(Item item) {
        inventory.addItem(item);
        item.setUser(this);
        addHudHeading("Picked up " + item.getInfo().get("name"));
    }

    public void removeItem(Item item) {
        inventory.removeItem(item);
        item.setUser(null);
        addHudHeading(item.getInfo().get("name") + " removed");
    }

    public Item getRightHandItem() {
        if (inventory.countItems() > 0 && !rightHandEmpty) {
            return inventory.getItems().get(rightHandItemIndex);
        }
        return null;
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

    public void unequipRightHand() {
        setRightHandItemIndex(-1);
    }

    public double getRightHandReach() {
        if (inventory.countItems() > 0 && !rightHandEmpty) {
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

    public void setHurtSprite(Sprite s) {
        setSpriteSet("hurt", s);
    }

    public void setActionSprite(Sprite s) {
        setSpriteSet("action", s);
    }

    public void setDeathSprite(Sprite s) {
        setSpriteSet("death", s);
    }

    public void setHealSprite(Sprite s) {
        setSpriteSet("heal", s);
    }

    public void lookTowards(double x, double z) {
        double xDiff = x - posX;
        double zDiff = z - posZ;
        rotation = Math.atan2(xDiff, zDiff);
    }

    public void push(double x, double z) {
        // TODO: fix this damn pushing algorithm!! NOW!!
        double nextZ = z * Math.cos(-rotation) - x * Math.sin(-rotation);
        double nextX = x * Math.cos(-rotation) + z * Math.sin(-rotation);

        moveX += nextX;
        moveZ += nextZ;
    }

    public void hurt(Entity source, int damage) {
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
        // TODO: do not use() a ranged weapon if a block is being triggered

        if (getRightHandItem() == null || getRightHandItem().canStrike) {
            runSpriteSet("action");

            List<Entity> closeEntities = getEntitiesInRadius(getRightHandReach());

            int divs = (int) (getRightHandReach() * 100);
            double xa = getRightHandReach() * Math.sin(rotation);
            double za = getRightHandReach() * Math.cos(rotation);

            for (int i = 0; i < divs; i++) {
                double xx = posX + xa * i / divs;
                double zz = posZ + za * i / divs;
                for (int b = 0; b < closeEntities.size(); b++) {
                    Entity ent = closeEntities.get(b);
                    if (ent instanceof Mob && ent != this) {
                        if (closeEntities.get(b).contains(xx, zz)) {
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

    public void modHealth(int modifier) {
        if (modifier > 0) {
            runSpriteSet("heal");
            for (int i = 0; i < 4; i++) {
                HealthParticle p = new HealthParticle(posX, posZ);
                level.addEntity(p);
            }
        }

        if (health + modifier > maxHealth) {
            health = maxHealth;
        } else if (health + modifier < 0) {
            health = 0;
        } else {
            health += modifier;
        }
    }

    public void modSpeed(double modifier) {
        walkSpeed += modifier;
    }
}

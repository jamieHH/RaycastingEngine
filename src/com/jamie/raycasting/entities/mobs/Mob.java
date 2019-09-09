package com.jamie.raycasting.entities.mobs;

import com.jamie.jamapp.*;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.mobEffects.MobEffect;
import com.jamie.raycasting.entities.particles.BloodParticle;
import com.jamie.raycasting.entities.particles.HealthParticle;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.input.Controls;
import com.jamie.raycasting.input.UserInputHandler;
import com.jamie.raycasting.items.Inventory;
import com.jamie.raycasting.items.Item;
import com.jamie.raycasting.items.consumables.Consumable;
import com.jamie.raycasting.items.specials.Spell;
import com.jamie.raycasting.items.weapons.Weapon;
import com.jamie.raycasting.world.blocks.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Mob extends Entity
{
    protected boolean isInvulnerable = false;
    protected boolean canPickup = false;
    protected boolean canActivateBlocks = false;
    protected boolean isFloating = false;

    // distances
    public int viewDist = 4;
    public double baseReach = 1;

    // actions
    public InputHandler input;
    public boolean isUsingMenu = false;
    protected static final int useWait = 15;
    public int useTicks = 0;

    protected String faction;
    protected String enemyFaction;
    protected Mob target;

    protected Sfx deathSound = Sound.die;
    protected Sfx hurtSound = Sound.hit;

    // movement
	private double rotationMove;
    private double moveX, moveZ;
    private double friction = 0.25;

    protected double rotationSpeed = 0.03;
    protected double walkSpeed = 0.03;

    private double viewHeight = 0.652;
    private int bobTime = 0;
    public double yBob = 0;
    public double camY;
    public double camPitch = 0.6;

	// stats
    public int baseDamage = 1;
    private static final int hurtWait = 30;
    public int hurtTicks = 0;
	public String hurtType = "";
	public int maxHealth = 10;
	public int health;

	private int dieTime = 30;
	private boolean isDieing = false;
    public boolean isDead = false;

    // items
    public Inventory inventory = new Inventory();
    private Integer[] hotKeys = new Integer[3];
    private int rightHandItemIndex = 0;
    public boolean rightHandEmpty = true;

    public List<MobEffect> mobEffects = new ArrayList<MobEffect>();

    public ArrayList<String> hudHeadings = new ArrayList<String>();
    private static final int hudHeadingsWait = 120;
    private int hudHeadingsTicks = 120;

    // sprites
    protected abstract Sprite getActionSprite();
    protected abstract Sprite getHealSprite();
    protected abstract Sprite getHurtSprite();
    protected abstract Sprite getDeathSprite();
    protected Particle getHurtParticle() {
        return new BloodParticle(2);
    }

    //Ai
    private final Random random = new Random();
    private int influenceWait = 20;
    protected abstract InfluenceKeyframe getIdleInfluence();
    protected abstract InfluenceKeyframe getPursuitInfluence();
    protected abstract InfluenceKeyframe getAttackInfluence();


    protected class InfluenceKeyframe
    {
        int switchWait;
        int forward;
        int back;
        int sLeft;
        int sRight;
        int rLeft;
        int rRight;
        int action;
        String itemName;

        public InfluenceKeyframe(int switchWait, int forward, int back, int sLeft, int sRight, int rLeft, int rRight, int action, String itemName) {
            this.switchWait = switchWait;
            this.forward = forward;
            this.back = back;
            this.sLeft = sLeft;
            this.sRight = sRight;
            this.rLeft = rLeft;
            this.rRight = rRight;
            this.action = action;
            this.itemName = itemName;
        }
    }


    public Mob(InputHandler input) {
        this.input = input;

        health = maxHealth;
        camY = viewHeight;

        setSpriteSet("action", getActionSprite());
        setSpriteSet("heal", getHealSprite());
        setSpriteSet("hurt", getHurtSprite());
        setSpriteSet("death", getDeathSprite());
    }

    private void setInputInfluence(InfluenceKeyframe influenceKeyframe) {
        if (influenceKeyframe != null) {
            input.setInput(Controls.FORWARD, (random.nextInt(100) < influenceKeyframe.forward));
            input.setInput(Controls.BACK, (random.nextInt(100) < influenceKeyframe.back));
            input.setInput(Controls.LEFT, (random.nextInt(100) < influenceKeyframe.sLeft));
            input.setInput(Controls.RIGHT, (random.nextInt(100) < influenceKeyframe.sRight));
            input.setInput(Controls.ROTLEFT, (random.nextInt(100) < influenceKeyframe.rLeft));
            input.setInput(Controls.ROTRIGHT, (random.nextInt(100) < influenceKeyframe.rRight));
            input.setInput(Controls.ACTION, (random.nextInt(100) < influenceKeyframe.action));
            if (influenceKeyframe.itemName != null) {
                setRightHandItem(influenceKeyframe.itemName);
            } else {
                unequipRightHand();
            }
            influenceWait = influenceKeyframe.switchWait;
        }
    }

    public void tick() {
        super.tick();

        if (hurtTicks > 0) {
            hurtTicks--;
        }

        if (useTicks > 0) {
            useTicks--;
        }

        if (hudHeadingsTicks > 0) {
            hudHeadingsTicks--;
        } else {
            if (hudHeadings.size() != 0) {
                hudHeadings.remove(0);
            }

            hudHeadingsTicks = hudHeadingsWait;
        }

        if (!isDieing) {
            if (health > 0) {
                if (!(input instanceof UserInputHandler)) {
                    target = null;
                    for (int i = 0; i < level.getMobEntities().size(); i++) {
                        Mob mob = level.getMobEntities().get(i);
                        if (mob != this) {
                            if (squareDistanceFrom(mob.posX, mob.posZ) < viewDist && mob.getFaction().equals(enemyFaction)) {
                                target = level.getMobEntities().get(i);
                                break;
                            }
                        }
                    }
                    if (influenceWait > 0) {
                        influenceWait--;
                    } else {
                        if (target == null) {
                            setInputInfluence(getIdleInfluence());
                        } else {
                            lookTowards(target.posX, target.posZ);
                            if (squareDistanceFrom(target.posX, target.posZ) < viewDist && squareDistanceFrom(target.posX, target.posZ) > getRightHandReach()) {
                                setInputInfluence(getPursuitInfluence());
                            } else if (squareDistanceFrom(target.posX, target.posZ) < getRightHandReach()) {
                                setInputInfluence(getAttackInfluence());
                            }
                        }
                    }
                }

                if (getRightHandItem() != null) {
                    getRightHandItem().tick();
                }

                for (int i = 0; i < inventory.countItems(); i++) {
                    if (inventory.getItem(i).removed) {
                        if (i <= rightHandItemIndex) {
                            rightHandItemIndex--;
                        }

                        for (int j = 1; j < 4; j++) {
                            if (getHotkey(j) != null) {
                                if (i < getHotkey(j)) {
                                    setHotkey(j, getHotkey(j) - 1);
                                } else if (i == getHotkey(j)) {
                                    setHotkey(j, null);
                                }
                            }
                        }

                        removeItem(inventory.getItem(i));
                    }
                }

                for (int i = 0; i < mobEffects.size(); i++) {
                    mobEffects.get(i).tick();

                    if (mobEffects.get(i).removed) {
                        removeMobEffect(mobEffects.get(i));
                    }
                }

                if (canPickup) {
                    for (int i = 0; i < level.countDrops(); i++) {
                        if (contains(level.getDropEntity(i).posX, level.getDropEntity(i).posZ)) {
                            Sound.pickUp.play();
                            addItem(level.getDropEntity(i).item);
                            level.getDropEntity(i).remove();
                        }
                    }
                }

                if (!isUsingMenu) {
                    receiveMovementInput();

                    if (input.check(Controls.ACTION)) {
                        activate();
                    }

                    if (input.check(Controls.HOT1) && getHotkey(1) != null) {
                        input.stopInput(Controls.HOT1);
                        useItemIndex(getHotkey(1));
                    }
                    if (input.check(Controls.HOT2) && getHotkey(2) != null) {
                        input.stopInput(Controls.HOT2);
                        useItemIndex(getHotkey(2));
                    }
                    if (input.check(Controls.HOT3) && getHotkey(3) != null) {
                        input.stopInput(Controls.HOT3);
                        useItemIndex(getHotkey(3));
                    }
                }

                doMovements();
            } else {
                deathSound.play();
                runSpriteSet("death");
                isDieing = true;
            }
        } else {
            unequipRightHand();
            camY = 0.125;

            if (!isDead) {
                dieTime--;
                if (dieTime == 0) {
                    level.addEntity(new PoofParticle(8), posX, posZ);

                    isDead = true;
                }
            } else {
                remove();
            }
        }
    }

    private void receiveMovementInput() {
        double moveSpeed = walkSpeed;
        moveSpeed *= friction;

        if (input.check(Controls.CROUCH)) {
            moveSpeed *= 0.5;
            yBob =- 0.25;
        }
        if (input.check(Controls.FORWARD)) moveZ += moveSpeed;
        if (input.check(Controls.BACK)) moveZ -= moveSpeed;
        if (input.check(Controls.LEFT)) moveX -= moveSpeed;
        if (input.check(Controls.RIGHT)) moveX += moveSpeed;
        if (input.check(Controls.ROTLEFT)) rotationMove -= rotationSpeed;
        if (input.check(Controls.ROTRIGHT)) rotationMove += rotationSpeed;

        // View bob:
        if ((input.check(Controls.FORWARD) ^ input.check(Controls.BACK)) || (input.check(Controls.LEFT) ^ input.check(Controls.RIGHT))) {
            bobTime++;
            double bobSpeed = (moveSpeed * 10) * 1.5;
            yBob += Math.sin((bobTime * bobSpeed)) * 0.015;
        } else {
            bobTime = 0;
        }
    }

    public void camPitch(double pitch) {
        camPitch += pitch;
        if (camPitch > 1.0) {
            camPitch = 1.0;
        } else if (camPitch < 0) {
            camPitch = 0.0;
        }
    }

    private void doMovements() {
        camY = viewHeight;
        camY += yBob;
        yBob *= 0.75;

        rotate(rotationMove);
        if (!isUsingMenu) {
            rotate(input.getDiffMouseX());
            camPitch(input.getDiffMouseY());
        }
        rotationMove *= 0.6;

        move(moveX * Math.cos(getRotation()) + moveZ * Math.sin(getRotation()), moveZ * Math.cos(getRotation()) - moveX * Math.sin(getRotation()));
        moveX *= 1 - friction;
        moveZ *= 1 - friction;
    }

    private boolean isWallBlocked(double x, double z) {
        if (isSolid) {
            int x0 = (int) (x + radius);
            int z0 = (int) (z + radius);
            int x1 = (int) (x - radius);
            int z1 = (int) (z - radius);

            if (level.getBlock(x0, z0).isSolid) return true;
            if (level.getBlock(x1, z0).isSolid) return true;
            if (level.getBlock(x0, z1).isSolid) return true;
            if (level.getBlock(x1, z1).isSolid) return true;

            if (!this.isFloating) {
                if (!level.getBlock(x0, z0).isWalkable) return true;
                if (!level.getBlock(x1, z0).isWalkable) return true;
                if (!level.getBlock(x0, z1).isWalkable) return true;
                if (!level.getBlock(x1, z1).isWalkable) return true;
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
                    if (((Math.abs(x - e.posX)) - e.radius < radius) && ((Math.abs(z - e.posZ)) - e.radius < radius)) {
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
        addHudHeading(item.getInfo().get("name") + " removed");
        inventory.removeItem(item);
        item.setUser(null);
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
        useTicks = 0;
    }

    public void setRightHandItem(String itemName) {
        for (int i = 0; i < inventory.getItems().size(); i++) {
            if (inventory.getItem(i).name.equals(itemName)) {
                if (getRightHandItem() != inventory.getItem(i)) {
                    setRightHandItemIndex(i);
                    return;
                }
            }
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

    public Integer[] getHotkeys() {
        return hotKeys;
    }

    public Integer getHotkey(int slot) {
        return hotKeys[slot - 1];
    }

    public void setHotkey(int slot, Integer itemIndex) {
        hotKeys[slot - 1] = itemIndex;
    }

    public Item getItemByName(String name) {
        for (int i = 0; i < inventory.countItems(); i++) {
            if (inventory.getItem(i).name.equals(name)) {
                return inventory.getItem(i);
            }
        }
        return null;
    }

    public void useItemIndex(int index) {
        if (inventory.getItem(index) instanceof Consumable) {
            inventory.getItem(index).use();
        } else if (inventory.getItem(index) instanceof Weapon || inventory.getItem(index) instanceof Spell) {
            if (getRightHandItemIndex() != index) {
                setRightHandItemIndex(index);
            } else {
                if (rightHandEmpty) {
                    setRightHandItemIndex(index);
                } else {
                    unequipRightHand();
                }
            }
        }
    }

    public int getDamage() {
        if (getRightHandItem() != null) {
            return baseDamage + getRightHandItem().damage;
        }
        return baseDamage;
    }

    public void lookTowards(double x, double z) {
        double r = Math.atan2(x - posX, z - posZ);
        setRotation(r);
    }

    public void pushDir(double direction, double force) {
        double mx = Math.sin(direction) * force;
        double mz = Math.cos(direction)* force;

        moveX += mx * Math.cos(-getRotation()) + mz * Math.sin(-getRotation());
        moveZ += mz * Math.cos(-getRotation()) - mx * Math.sin(-getRotation());
    }

    public void heal(int magnitude) {
        if (magnitude > 0 && !isDieing) {
            runSpriteSet("heal");
            HealthParticle p = new HealthParticle(8);
            level.addEntity(p, posX, posZ);

            if (health + magnitude > maxHealth) {
                health = maxHealth;
            } else if (health + magnitude < 0) {
                health = 0;
            } else {
                health += magnitude;
            }
        }
    }

    public void hurt(Entity source, int magnitude) {
        hurt(source, magnitude, "hurt");
    }

    public void hurt(Entity source, int magnitude, String damageType) {
        if (!isInvulnerable) {
            if (magnitude > 0 && !isDieing && hurtTicks < 1) {
                runSpriteSet("hurt");
                level.addEntity(getHurtParticle(), posX, posZ);

                if (health - magnitude > 0) {
                    hurtSound.play();
                    hurtType = damageType; // change to blunt if armor protects some damage
                    hurtTicks = hurtWait;
                    health -= magnitude;

                    yBob -= 0.5;
                    rotationMove += (Math.random() - 0.5) / 2;
                    pushDir(source.getRotation(), 0.4);
                } else {
                    health = 0;
                }
            }
        }
    }

    private void activate() {
        if (useTicks < 1) {
            useTicks = getUseWait();
            runSpriteSet("action");

            List<Entity> closeEntities = getEntitiesInRadius(getRightHandReach());
            double xa = getRightHandReach() * Math.sin(getRotation());
            double za = getRightHandReach() * Math.cos(getRotation());
            int divs = 100;

            boolean hit = false;
            for (int i = 0; i < divs && !hit; i++) {
                double xx = posX + xa * i / divs;
                double zz = posZ + za * i / divs;

                for (int b = 0; b < closeEntities.size(); b++) {
                    Entity ent = closeEntities.get(b);
                    if (ent instanceof Mob && ent != this) {
                        if (ent.contains(xx, zz)) {
                            if (getRightHandItem() == null || (getRightHandItem() != null && getRightHandItem().canStrike)) {
                                ((Mob) ent).hurt(this, getDamage());
                            } // prevents ranged weapons from causing melee damage

                            hit = true;
                            break;
                        }
                    }
                }

                if ((int) xx != (int) posX || (int) zz != (int) posZ) {
                    Block block = level.getBlock((int) xx, (int) zz);
                    if (block.use(this) || block.isSolid || block.isUsable) {
                        if (getRightHandItem() != null && !getRightHandItem().canStrike) {
                            return;
                        } // prevent ranged weapons firing when activating blocks

                        break;
                    }
                }
            }

            if (getRightHandItem() != null) {
                getRightHandItem().use();
            }
        }
    }

    private int getUseWait() {
        if (getRightHandItem() != null) {
            return getRightHandItem().useWait;
        }
        return useWait;
    }

    public String getFaction() {
        return faction;
    }

    public void modHealth(int modifier) {
        if (modifier > 0) {
            heal(modifier);
        } else {
            hurt(this, -modifier, "poison");
        }
    }

    public void modSpeed(double modifier) {
        walkSpeed += modifier;
    }

    public void modBaseDamage(double modifier) {
        baseDamage += modifier;
    }
}

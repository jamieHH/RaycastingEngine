package com.jamie.raycasting.entities.mobs;

import com.jamie.raycasting.entities.BloodParticle;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.PoofParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.InputHandler;
import com.jamie.raycasting.levels.blocks.Block;

import java.util.ArrayList;
import java.util.List;

public class Mob extends Entity
{
    protected boolean wallCollide = true;
    protected boolean entCollide = true;

    // distances
    public int useDist = 24;
    public int viewDist = 64;

    // actions
    public InputHandler input;
    protected int useTime = 0;

    // movement
	protected double rotationMove;
    protected double moveX, moveZ;

    protected double rotationSpeed = 0.03;
    protected double walkSpeed = 0.3;
    protected double runSpeed = 0.4;
    protected double crouchSpeed = 0.15;

    protected double camHeightMod = 2.0;
    protected double crouchHeightMod = 4.0;

    public double camY = 0;
    protected double yBob = 0;
    private int bobTime = 0;


	// stats
	public int damageTime = 0;
	public int maxHealth = 10;
	public int health = 10;

	protected int dieTime = 30;
	protected boolean isDieing = false;
    public boolean isDead = false;

    public Mob(InputHandler input) {
        input.setMob(this);
        this.input = input;
    }

    public void tick() {
        super.tick();
        input.tick();

        if (input.action) {
            if (useTime <= 0) {
                useTime = 20;

                activate();
            }
        }

        if (damageTime > 0) damageTime--;
        if (useTime > 0) useTime--;

        if (health <= 0) isDieing = true;
        if (isDieing && !isDead) dieTick();
    }

    protected boolean isFree(double x, double z) {
        int x0 = (int) (Math.floor((x + radius) / 16));
        int x1 = (int) (Math.floor((x - radius) / 16));
        int z0 = (int) (Math.floor((z + radius) / 16));
        int z1 = (int) (Math.floor((z - radius) / 16));

        if (level.getBlock(x0, z0).blocksMotion) return false;
        if (level.getBlock(x1, z0).blocksMotion) return false;
        if (level.getBlock(x0, z1).blocksMotion) return false;
        if (level.getBlock(x1, z1).blocksMotion) return false;
        return true;
    }

    protected boolean isEntityFree(double x, double z) {
        for (int i = 0; i < level.countEntities(); i++) {
            Entity e = level.getEntity(i);
            if (e.solid) {
                double entX = e.posX;
                double entZ = e.posZ;
                double entRadius = e.radius;
                if (level.getEntity(i) != this) {
                    if (((Math.abs(x - entX)) - entRadius < radius) && ((Math.abs(z - entZ)) - entRadius < radius)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected void doMovements() {
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
            yBob += Math.sin(bobTime / (7 - (moveSpeed * 10))) * 0.25;
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

	protected void move(double nextX, double nextZ) {
        if (wallCollide) {
            if (!isFree(posX + nextX, posZ)) {
                nextX = 0;
            }
        }
        if (entCollide) {
            if (!isEntityFree(posX + nextX, posZ)) {
                nextX = 0;
            }
        }
        posX += nextX;

        if (wallCollide) {
            if (!isFree(posX, posZ + nextZ)) {
                nextZ = 0;
            }
        }
        if (entCollide) {
            if (!isEntityFree(posX, posZ + nextZ)) {
                nextZ = 0;
            }
        }
        posZ += nextZ;

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
        if (damageTime > 0 || damage <= 0 || isDieing) return;

        yBob -= 6;

        health -= damage;
        damageTime = 30;

        double mx = (posX - source.posX) / 2;
        double mz = (posZ - source.posZ) / 2;
        push(mx, mz);

        for (int i = 0; i < 2 ; i++) {
            BloodParticle bloodParticle = new BloodParticle(posX, posZ);
            bloodParticle.level = level;
            level.addEntity(bloodParticle);
        }
    }

    protected void dieTick() {
        dieTime--;

        clearSprites();
        spriteIndex = 0;

        Sprite sprite = new Sprite(0, 0, 0, Texture.splat);
        addSprite(sprite);

        if (dieTime <= 0) {
            isDead = true;

            for (int i = 0; i < 6 ; i++) {
                PoofParticle poofParticle = new PoofParticle(posX, posZ);
                poofParticle.level = level;
                level.addEntity(poofParticle);
            }
        }
    }

    public void activate() {
        List<Entity> closeEnts = new ArrayList<Entity>();
        for (int e = 0; e < level.countEntities(); e++) {
            Entity ent = level.getEntity(e);
            if (distanceFrom(ent.posX, ent.posZ) < useDist) {
                closeEnts.add(ent);
            }
        }


        double blockUseDist = ((double) useDist) / 16;
        int divs = useDist;
        double xa = (blockUseDist * Math.sin(rotation));
        double za = (blockUseDist * Math.cos(rotation));

        for (int i = 0; i < divs; i++) {
            double xx = posX + xa * (i * 16) / divs;
            double zz = posZ + za * (i * 16) / divs;
            for (int b = 0; b < closeEnts.size(); b++) {
                Entity ent = closeEnts.get(b);
                if (ent instanceof Mob && ent != this) {
                    if (closeEnts.get(b).contains(xx, zz)) {
                        ((Mob) ent).hurt(this, 1); // change based on equipped tool
                        return;
                    }
                }
            }

            int xb = (int) (xx / 16);
            int zb = (int) (zz / 16);
            if (xb != (int) (posX / 16) || zb != (int) (posZ / 16)) {
                Block block = level.getBlock(xb, zb);
                if (block.use()) return;

                if (block.blocksMotion) return;
            }
        }
    }
}

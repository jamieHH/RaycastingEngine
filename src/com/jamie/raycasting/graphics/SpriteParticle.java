package com.jamie.raycasting.graphics;

public class SpriteParticle extends Sprite
{
    private double moveX = 0;
    private double moveY = 0;
    private double moveZ = 0;

    private double force;
    private double gravity;
    public double friction = 0.05;


    public SpriteParticle(Render t, double force, double gravity) {
        super(t);

        this.force = force;
        this.gravity = gravity;
        setMomentum();
    }

    public SpriteParticle(Render[] ts, double force, double gravity) {
        super(ts);

        this.force = force;
        this.gravity = gravity;
        setMomentum();
    }

    public void tick() {
        super.tick();
        y -= gravity / 16;

        moveX *= 1 - friction;
        moveY *= 1 - friction;
        moveZ *= 1 - friction;

        x += moveX;
        y += moveY;
        z += moveZ;

        if (y <= 0) {
            y = 0;
            moveX *= 0.5;
            moveZ *= 0.5;
        }
    }

    private void setMomentum() {
        moveX = ((Math.random() - 0.5) / 2) * force;
        moveY = ((Math.random() - 0.5) / 2) * force;
        moveZ = ((Math.random() - 0.5) / 2) * force;
    }
}

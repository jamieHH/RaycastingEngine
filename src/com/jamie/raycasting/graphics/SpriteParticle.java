package com.jamie.raycasting.graphics;

public class SpriteParticle extends Sprite
{
    private int time = 0;

    private double moveX, moveY, moveZ;
    private double yg = 0;

    public double speed = 1;
    public double gravity = 1;
    public double friction = 0.05;

    public SpriteParticle(Render texture, double xOffs, double yOffs, double zOffs) {
        super(texture, xOffs, yOffs, zOffs);

        setMomentum();
    }

    public SpriteParticle(Render texture, double xOffs, double yOffs, double zOffs, double speed) {
        super(texture, xOffs, yOffs, zOffs);

        this.speed = speed;
        setMomentum();
    }

    public SpriteParticle(Render t) {
        super(t);

        setMomentum();
    }

    public SpriteParticle(Render t, double speed) {
        super(t);

        this.speed = speed;
        setMomentum();
    }

    public SpriteParticle(Render[] ts) {
        super(ts);

        setMomentum();
    }

    public SpriteParticle(Render[] ts, double speed) {
        super(ts);

        this.speed = speed;
        setMomentum();
    }

    public void tick() {
        super.tick();
        time++;

        yg -= ((time * 0.125) * gravity) * 0.0125;
        y += yg;

        x += moveX * 0.25;
        y += moveY * 0.25;
        z += moveZ * 0.25;

        moveX *= 1 - friction;
        moveY *= 1 - friction;
        moveZ *= 1 - friction;

        if (y <= 0) {
            y = 0;
            moveX *= 0.75;
            moveZ *= 0.75;
        }
    }

    private void setMomentum() {
        moveX = ((Math.random() - 0.5) / 2) * speed;
        moveY = ((Math.random() - 0.5) / 2) * speed;
        moveZ = ((Math.random() - 0.5) / 2) * speed;
    }
}

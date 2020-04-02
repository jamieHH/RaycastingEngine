package com.jamie.raycasting.graphics;

import com.jamie.jamapp.Bitmap;

public class DropSprite extends Sprite
{
    private double moveX = 0;
    private double moveY = 0;
    private double moveZ = 0;

    private final double force;
    private final double gravity;
    public final double friction = 0.05;


    public DropSprite(Bitmap t, double force, double gravity) {
        super(t);
        y = 0.5;

        this.force = force;
        this.gravity = gravity;
        setMomentum();
    }

    public DropSprite(Bitmap t) {
        super(t);
        y = 0.5;

        this.force = 0.1;
        this.gravity = 0.5;
        setMomentum();
    }

    public void tick() {
        super.tick();
        moveY -= gravity / 100;

        moveX *= 1 - friction;
        moveZ *= 1 - friction;

        x += moveX;
        y += moveY;
        z += moveZ;

        if (y <= 0) {
            y = 0;

            moveX *= 0.5;
            moveY *= -0.75;
            moveZ *= 0.5;
        }
    }

    private void setMomentum() {
        moveY = 0.05;

        moveX = ((Math.random() - 0.5) / 2) * force;
        moveY += ((Math.random() - 0.5) / 2) * force;
        moveZ = ((Math.random() - 0.5) / 2) * force;
    }
}

package com.jamie.raycasting.graphics;

import java.util.Random;

public class SpriteParticle extends Sprite
{
    private int life = 120;
    private int time = 0;

    public double gravity;
    private double xa, ya, za;
    private Random random = new Random();

    public SpriteParticle(Render texture, double x, double y, double z) {
        super(texture, x, y, z);

        gravity = 1;
        xa = Math.random() - 0.5;
        ya = Math.random() - 0.5;
        za = Math.random() - 0.5;
    }

    public void tick() {
        super.tick();
        time++;

        ya -= ((time / 10) * 0.125) * gravity;

        x += xa * 0.5;
        y += ya * 0.125;
        z += za * 0.5;

        if (y < 0) {
            y = 0;
            xa *= 0.75;
            za *= 0.75;
        }
    }
}

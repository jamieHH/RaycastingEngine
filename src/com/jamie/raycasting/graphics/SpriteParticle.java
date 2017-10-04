package com.jamie.raycasting.graphics;

import java.util.Random;

public class SpriteParticle extends Sprite
{
    public int life = 120;
    private int time = 0;

    public double gravity = 1;
    private double xa, ya, za;
    private Random random = new Random();

    public SpriteParticle(Render texture, double xOffs, double yOffs, double zOffs) {
        super(texture, xOffs, yOffs, zOffs);

        xa = Math.random() - 0.5;
        ya = Math.random() - 0.5;
        za = Math.random() - 0.5;
    }

    public SpriteParticle(Render t) {
        super(t);

        xa = Math.random() - 0.5;
        ya = Math.random() - 0.5;
        za = Math.random() - 0.5;
    }

    public SpriteParticle(Render[] ts) {
        super(ts);

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

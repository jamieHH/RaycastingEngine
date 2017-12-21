package com.jamie.raycasting.graphics;

public class SpriteParticle extends Sprite
{
    private int time = 0;

    public double gravity = 1;
    private double xa, ya, za;

    public SpriteParticle(Render texture, double xOffs, double yOffs, double zOffs) {
        super(texture, xOffs, yOffs, zOffs);

        xa = Math.random() - 0.5;
        ya = (Math.random() - 0.5) / 2;
        za = Math.random() - 0.5;
    }

    public SpriteParticle(Render t) {
        super(t);

        xa = Math.random() - 0.5;
        ya = (Math.random() - 0.5) / 2;
        za = Math.random() - 0.5;
    }

    public SpriteParticle(Render[] ts) {
        super(ts);

        xa = Math.random() - 0.5;
        ya = (Math.random() - 0.5) / 2;
        za = Math.random() - 0.5;
    }

    public void tick() {
        super.tick();
        time++;

        x += xa * 0.5;
        y += ya * 0.5;
        z += za * 0.5;

        y -= ((time * 0.125) * gravity) * 0.0625;

        if (y <= 0) {
            y = 0;
            ya *= -1;
            xa *= 0.75;
            za *= 0.75;
        }
    }
}

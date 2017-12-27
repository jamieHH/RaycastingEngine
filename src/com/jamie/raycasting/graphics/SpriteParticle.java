package com.jamie.raycasting.graphics;

public class SpriteParticle extends Sprite
{
    private int time = 0;

    public double gravity = 1;
    private double xa, ya, za;
    private double yg = 0;

    public SpriteParticle(Render texture, double xOffs, double yOffs, double zOffs) {
        super(texture, xOffs, yOffs, zOffs);

        setMomentum();
    }

    public SpriteParticle(Render t) {
        super(t);

        setMomentum();
    }

    public SpriteParticle(Render[] ts) {
        super(ts);

        setMomentum();
    }

    private void setMomentum() {
        xa = (Math.random() - 0.5) / 2;
        ya = (Math.random() - 0.5) / 2;
        za = (Math.random() - 0.5) / 2;
    }

    public void tick() {
        super.tick();
        time++;

        yg -= ((time * 0.125) * gravity) * 0.0125;
        y += yg;

        xa *= 0.99;
        ya *= 0.99;
        za *= 0.99;

        x += xa * 0.25;
        y += ya * 0.25;
        z += za * 0.25;



        if (y <= 0) {
            y = 0;
            xa *= 0.75;
            za *= 0.75;
        }
    }
}

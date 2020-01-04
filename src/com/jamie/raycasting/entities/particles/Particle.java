package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.entities.Entity;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.ParticleSprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Particle extends Entity
{
    protected static final Random random = new Random();
    protected int life = 120;

    protected abstract Bitmap[] getTexOptions();

    private Sprite[] spriteParticles;


    public Particle(int count, double force, double gravity, double yOffs) {
        isSolid = false;

        List<Sprite> set = new ArrayList<Sprite>();
        for (int i = 0; i < count; i++) {
            ParticleSprite particle = new ParticleSprite(getTexOptions()[(random.nextInt(getTexOptions().length))], force, gravity);
            particle.setOffset(0, yOffs, 0);

            set.add(particle);
        }

        spriteParticles = set.toArray(new Sprite[0]);
    }

    public void tick() {
        super.tick();
        for (int i = 0; i < spriteParticles.length; i++) {
            spriteParticles[i].tick();
        }

        if (life > 0) {
            life --;
        } else {
            remove();
        }
    }

    public Sprite[] getSpriteParticles() {
        return spriteParticles;
    }

    protected Sprite getSprite() {
        return null;
    }
}
package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.entities.Entity;
import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.SpriteParticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Particle extends Entity
{
    protected static final Random random = new Random();
    protected int life = 120;

    protected abstract Render[] getTexOptions();

    private List<Sprite> spriteParticles = new ArrayList<Sprite>();


    public Particle(int count, double force, double gravity, double yOffs) {
        isSolid = false;

        List<Sprite> set = new ArrayList<Sprite>();
        for (int i = 0; i < count; i++) {
            SpriteParticle particle = new SpriteParticle(getTexOptions()[(random.nextInt(getTexOptions().length))], force, gravity);
            particle.setOffset(0, yOffs, 0);

            set.add(particle);
        }
        setSpriteParticles(set);
    }

    public void tick() {
        super.tick();
        for (int i = 0; i < spriteParticles.size(); i++) {
            spriteParticles.get(i).tick();
        }

        if (life > 0) {
            life --;
        } else {
            remove();
        }
    }

    public List<Sprite> getSpriteParticles() {
        return spriteParticles;
    }

    public void setSpriteParticles(List<Sprite> spriteParticles) {
        this.spriteParticles = spriteParticles;
    }

    protected Sprite getSprite() {
        return null;
    }
}
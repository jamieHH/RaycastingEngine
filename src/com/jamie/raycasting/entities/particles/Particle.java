package com.jamie.raycasting.entities.particles;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Particle extends Entity
{
    protected static final Random random = new Random();
    protected int life = 120;

    protected List<Sprite> spriteParticles = new ArrayList<Sprite>();

    public Particle() {
        isSolid = false;
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
}
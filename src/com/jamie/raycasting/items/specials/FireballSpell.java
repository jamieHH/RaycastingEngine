package com.jamie.raycasting.items.specials;

import com.jamie.raycasting.entities.projectiles.FireballProjectile;

public class FireballSpell extends Spell
{
    public FireballSpell() {
        name = "Fireball Spell";
        type = "spell";

        useWait = 120;
        canStrike = false;
    }

    public void use() {
        super.use();

        FireballProjectile projectile = new FireballProjectile(1, 1);
        projectile.setRotation(user.getRotation());
        user.level.addEntity(projectile, user.posX + Math.sin(user.getRotation()), user.posZ + Math.cos(user.getRotation()));
    }
}

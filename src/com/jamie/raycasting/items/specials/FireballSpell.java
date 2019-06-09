package com.jamie.raycasting.items.specials;

import com.jamie.raycasting.entities.projectiles.FireballProjectile;

public class FireballSpell extends Spell
{
    public FireballSpell() {
        name = "Fireball Spell";
        type = "spell";

        useWait = 1;
        canStrike = false;
    }

    public void use() {
        super.use();

        FireballProjectile projectile = new FireballProjectile(1, 1);
        projectile.setRotation(user.rotation);
        user.level.addEntity(projectile, user.posX + Math.sin(user.rotation), user.posZ + Math.cos(user.rotation));
    }
}

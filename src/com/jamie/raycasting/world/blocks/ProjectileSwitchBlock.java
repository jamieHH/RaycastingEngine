package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.projectiles.Projectile;
import com.jamie.raycasting.graphics.Texture;

import java.util.List;

public class ProjectileSwitchBlock extends SwitchBlock
{
    public ProjectileSwitchBlock() {
        isOpaque = true;
        isSolid = true;

        wallTex = Texture.wallBoltSwitch0;
    }

    public void tick() {
        if (!getState()) {
            List<Entity> entities = level.getEntitiesWithin(gridX - 0.1, gridZ - 0.1, gridX + 1.1, gridZ + 1.1);
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof Projectile) {
                    trigger();
                }
            }
        }
    }

    public boolean use(Mob source) {
        return false;
    }

    protected void setState(boolean state) {
        if (state) {
            this.isDown = true;
            emitSound(Sound.clickDown);
            wallTex = Texture.wallBoltSwitch1;
        } else {
            this.isDown = false;
            emitSound(Sound.clickUp);
            wallTex = Texture.wallBoltSwitch0;
        }
    }
}

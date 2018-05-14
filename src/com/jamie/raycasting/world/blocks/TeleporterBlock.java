package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

import java.util.List;

public class TeleporterBlock extends Block
{
    public boolean disabled = false;
    public Block target;

	public TeleporterBlock() {
        isOpaque = false;
        isSolid = false;

        Render ts0[] = {
                Texture.portal0,
                Texture.portal1,
                Texture.portal2,
                Texture.portal3,
        };

        setIdleSprite(new Sprite(ts0));
    }

	public void tick() {
	    super.tick();

        List<Entity> entities = level.getEntitiesWithin(gridX, gridZ, gridX + 1, gridZ + 1);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Particle) {
                entities.remove(i);
            }
        }

        if (disabled) {
            if (entities.size() == 0) {
                disabled = false;
            }
        } else {
            if (entities.size() > 0) {
                for (int i = 0; i < entities.size(); i++) {
                    entities.get(i).setPosition(target.gridX + 0.5, target.gridZ + 0.5);

                    // TODO: find out why these particles still disable the teleporters
//                    PoofParticle p0 = new PoofParticle(entities.get(i).posX, entities.get(i).posZ);
//                    level.addEntity(p0);
//
//                    PoofParticle p1 = new PoofParticle(gridX + 0.5, gridZ + 0.5);
//                    level.addEntity(p1);
                }

                if (target instanceof TeleporterBlock) {
                    ((TeleporterBlock) target).disabled = true;
                }
                disabled = true;
            }
        }
    }

    public void setTarget(Block target) {
        this.target = target;
    }
}

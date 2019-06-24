package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.environmentalEffects.EnvironmentalEffect;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.jamapp.Texture;

import java.util.List;

public class TeleporterBlock extends FunctionBlock
{
    public boolean disabled = false;
    public String targetRef;

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

        setDisabledSprite(new Sprite(Texture.portalDisabled0));
    }

	public void tick() {
	    super.tick();

        List<Entity> entities = level.getEntitiesWithin(gridX, gridZ, gridX + 1, gridZ + 1);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Particle || entities.get(i) instanceof EnvironmentalEffect) {
//                System.out.println(entities.get(i)); // particles removed
                entities.remove(i);
            }
        }

        if (disabled) {
//            System.out.println(entities.size());
            if (entities.size() == 0) {
//                System.out.println("enable");
                enable();
            }
        } else {
            if (entities.size() > 0) {
                TeleporterBlock target = (TeleporterBlock) level.getBlockByReference(targetRef);

                for (int i = 0; i < entities.size(); i++) {
//                    System.out.println(entities.get(i)); // entities being teleported
                    entities.get(i).setPosition(target.gridX + 0.5, target.gridZ + 0.5);

                    // TODO: find out why these particles still disable the teleporters
//                    PoofParticle p0 = new PoofParticle(entities.get(i).posX, entities.get(i).posZ);
//                    level.addEntity(p0);
//
//                    PoofParticle p1 = new PoofParticle(gridX + 0.5, gridZ + 0.5);
//                    level.addEntity(p1);
                }

                target.disable();
                disable();
            }
        }
    }

    public void disable() {
	    disabled = true;
	    if (!getActiveSetKey().equals("disabled")) {
	        runSpriteSet("disabled");
        }
    }

    public void enable() {
        disabled = false;
        if (!getActiveSetKey().equals("idle")) {
            runSpriteSet("idle");
        }
    }

    public void setDisabledSprite(Sprite sprite) {
        setSpriteSet("disabled", sprite);
    }



    public void setTargetRef(String targetRef) {
        this.targetRef = targetRef;
    }
}

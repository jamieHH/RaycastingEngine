package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.PoofParticle;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

import java.util.List;

public class TeleporterBlock extends Block
{
    public boolean disabled = false;
    public double destX;
    public double destZ;

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

        List<Mob> mobs = level.getMobsWithin(gridX, gridZ, gridX + 1, gridZ + 1);

        if (disabled) {
            if (mobs.size() == 0) {
                disabled = false;
            }
        } else {
            for (int i = 0; i < mobs.size(); i++) {
                mobs.get(i).setPosition(gridX - 1, gridZ - 1); // tmp positioning

                PoofParticle p0 = new PoofParticle(mobs.get(i).posX, mobs.get(i).posZ);
                level.addEntity(p0);

                PoofParticle p1 = new PoofParticle(gridX + 0.5, gridZ + 0.5);
                level.addEntity(p1);

                disabled = true;
            }
        }
    }
}

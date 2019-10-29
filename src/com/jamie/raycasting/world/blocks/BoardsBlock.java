package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.WoodParticle;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.weapons.AxeWeapon;

public class BoardsBlock extends TriggerableBlock
{
	private boolean smashed;

	public BoardsBlock(boolean smashed) {
        isOpaque = false;

        floorTex = Texture.floor;
        ceilTex = Texture.floor;

        Bitmap[] ts0 = {
                Texture.boards,
        };
        setIdleSprite(new Sprite(ts0));

        Bitmap[] ts1 = {
                Texture.boardsSmashed,
        };
        setSpriteSet("smashed", new Sprite(ts1));

        if (smashed) {
            this.smashed = true;
            isSolid = false;
            switchSpriteSet("smashed");
        } else {
            this.smashed = false;
            isSolid = true;
            switchSpriteSet("idle");
        }
	}

	public boolean use(Mob source) {
        if (!smashed) {
            if (source.getRightHandItem() instanceof AxeWeapon) {
                trigger();
                return true;
            }

            source.addHudHeading("You need an axe");
        } else {
            if (source.getItemByName("Planks") != null) {
                setState(false);
                return true;
            }
        }

        return false;
    }

    public void trigger() {
        setState(true);

        WoodParticle p = new WoodParticle(8);
        level.addEntity(p, gridX + 0.5, gridZ + 0.5);
    }

    protected void setState(boolean state) {
        if (state) {
            smashed = true;
            isSolid = false;
            emitSound(Sound.smash);
            switchSpriteSet("smashed");
        } else {
            smashed = false;
            isSolid = true;
            emitSound(Sound.clickAction);
            switchSpriteSet("idle");
        }
    }
}

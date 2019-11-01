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
	private boolean isBroken;

	public BoardsBlock(Bitmap floorTex, Bitmap ceilTex, boolean smashed) {
        isOpaque = false;

        this.floorTex = floorTex;
        this.ceilTex = ceilTex;

        Bitmap[] ts0 = {
                Texture.boards,
        };
        setIdleSprite(new Sprite(ts0));

        Bitmap[] ts1 = {
                Texture.boardsSmashed,
        };
        setSpriteSet("broken", new Sprite(ts1));

        if (smashed) {
            this.isBroken = true;
            isSolid = false;
            switchSpriteSet("broken");
        } else {
            this.isBroken = false;
            isSolid = true;
            switchSpriteSet("idle");
        }
	}

	public boolean use(Mob source) {
        if (!isBroken) {
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

    public boolean getState() {
        return isBroken;
    }

    public void setState(boolean state) {
        if (state) {
            isBroken = true;
            isSolid = false;
            emitSound(Sound.smash);
            switchSpriteSet("broken");
        } else {
            isBroken = false;
            isSolid = true;
            emitSound(Sound.clickAction);
            switchSpriteSet("idle");
        }
    }
}

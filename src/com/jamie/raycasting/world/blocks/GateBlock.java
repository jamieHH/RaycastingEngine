package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GateBlock extends TriggerableBlock
{
	protected boolean isOpen = false;

    public String keyName;


	public GateBlock(Bitmap floorTex, Bitmap ceilTex, String keyName) {
		isOpaque = false;
		isSolid = true;

		this.floorTex = floorTex;
		this.ceilTex = ceilTex;

		this.keyName = keyName;

        Bitmap[] ts0 = {
                Texture.gate0,
        };
        setIdleSprite(new Sprite(ts0));

        Bitmap[] ts1 = {
                Texture.gate1,
                Texture.gate2,
                Texture.gate3,
        };
        setOpenSprite(new Sprite(ts1));

        Bitmap[] ts2 = {
                Texture.gate2,
                Texture.gate1,
                Texture.gate0,
        };
        setCloseSprite(new Sprite(ts2));
	}

	public boolean use(Mob source) {
        if (source.getItemByName(keyName) != null) {
            trigger();
            return true;
        }

        source.addHudHeading("The " + keyName + " is required");
        return false;
    }

    public void trigger() {
        setState(!getState());
    }

    public void tick() {
	    super.tick();
    }

    public boolean getState() {
        return isOpen;
    }

    public void setState(boolean state) {
        if (state) {
            isOpen = true;
            isSolid = false;
            setIdleSprite(new Sprite(Texture.gate3));
            runSpriteSet("open");
            emitSound(Sound.slideDown);
        } else {
            isOpen = false;
            isSolid = true;
            setIdleSprite(new Sprite(Texture.gate0));
            runSpriteSet("close");
            emitSound(Sound.slideUp);
        }
    }

    public void setOpenSprite(Sprite sprite) {
        setSpriteSet("open", sprite);
    }

    public void setCloseSprite(Sprite sprite) {
        setSpriteSet("close", sprite);
    }
}

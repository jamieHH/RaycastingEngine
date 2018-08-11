package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class GateBlock extends TriggerableBlock
{
	protected boolean open = false;

    private int useTicks = 0;
    private int useWait = 10;

    public String keyName;


	public GateBlock(String keyName) {
		isOpaque = false;
		isSolid = true;

		floorTex = Texture.floor;
		ceilTex = Texture.floor;

		this.keyName = keyName;

        Render[] ts0 = {
                Texture.gate0,
        };
        setIdleSprite(new Sprite(ts0));

        Render[] ts1 = {
                Texture.gate1,
                Texture.gate2,
                Texture.gate3,
        };
        setOpenSprite(new Sprite(ts1));

        Render[] ts2 = {
                Texture.gate2,
                Texture.gate1,
                Texture.gate0,
        };
        setCloseSprite(new Sprite(ts2));
	}

	public boolean use(Mob source) {
        if (useTicks > 0) {
            return false;
        }

        if (source.getItemByName(keyName) != null) {
            useTicks = useWait;
            trigger();
            return true;
        }

        source.addHudHeading("The " + keyName + " is required");
        return false;
    }

    public void trigger() {
        open = !open;
        isSolid = !open;

        if (open) {
            setIdleSprite(new Sprite(Texture.gate3));
            runSpriteSet("open");
            Sound.slideDown.play();
        } else {
            setIdleSprite(new Sprite(Texture.gate0));
            runSpriteSet("close");
            Sound.slideUp.play();
        }
    }

    public void tick() {
	    super.tick();
	    if (useTicks > 0) useTicks--;

    }

    public void setOpenSprite(Sprite sprite) {
        setSpriteSet("open", sprite);
    }

    public void setCloseSprite(Sprite sprite) {
        setSpriteSet("close", sprite);
    }
}

package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class ButtonBlock extends FunctionBlock
{
    private int useTicks = 0;
    private static final int USE_WAIT = 10;

	public ButtonBlock() {
		isOpaque = true;
		isSolid = true;

		wallTex = Texture.wallButton0;
	}

	public void tick() {
        if (useTicks > 0) {
            useTicks--;
        } else {
            wallTex = Texture.wallButton0;
        }
    }

    public boolean use(Mob source) {
	    trigger();

	    return true;
    }

	public boolean trigger() {
        useTicks = USE_WAIT;
        emitSound(Sound.clickUp);
        wallTex = Texture.wallButton1;
        level.triggerBlock(reference);

		return true;
	}
}

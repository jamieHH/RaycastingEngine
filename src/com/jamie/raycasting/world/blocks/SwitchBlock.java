package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class SwitchBlock extends FunctionBlock
{
    private int useTicks = 0;
    private int useWait = 10;
    protected boolean isDown = false;

	public SwitchBlock() {
		isOpaque = true;
		isSolid = true;

		wallTex = Texture.wallSwitch0;
	}

	public void tick() {
        if (useTicks > 0) {
            useTicks--;
        }
    }

	public boolean use(Mob source) {
        if (useTicks > 0) {
            return false;
        }
        useTicks = useWait;

        trigger();

		return true;
	}

    public void trigger() {
        setState(!getState());
        level.triggerBlock(id);
    }

	public boolean getState() {
	    return isDown;
    }

    protected void setState(boolean state) {
        if (state) {
            this.isDown = true;
            emitSound(Sound.clickDown);
            wallTex = Texture.wallSwitch1;
        } else {
            this.isDown = false;
            emitSound(Sound.clickUp);
            wallTex = Texture.wallSwitch0;
        }
    }
}

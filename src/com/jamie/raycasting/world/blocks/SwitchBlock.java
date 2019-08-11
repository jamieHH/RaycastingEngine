package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Texture;

public class SwitchBlock extends FunctionBlock
{
    private int useTicks = 0;
    private int useWait = 10;
    private boolean isDown = false;

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

        isDown = !isDown;
        if (isDown) {
            Sound.clickDown.play();
            wallTex = Texture.wallSwitch1;
        } else {
            Sound.clickUp.play();
            wallTex = Texture.wallSwitch0;
        }
        level.triggerBlock(id);

		return true;
	}
}

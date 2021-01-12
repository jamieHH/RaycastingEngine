package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.Texture;

import java.util.List;

public class PressurePlateBlock extends FunctionBlock
{
    private static int checkTicks = 0;
    private static final int CHECK_WAIT = 5;
    private boolean isPressed = false;

	public PressurePlateBlock(Bitmap ceilTex) {
		this.isOpaque = false;
		this.isSolid = false;
		this.floorTex = Texture.floorPadUp;
		this.ceilTex = ceilTex;
	}

	public void tick() {
	    if (checkTicks > 0) {
	        checkTicks--;
        } else {
	        checkTicks = CHECK_WAIT;
            List<Entity> ents = level.getEntitiesWithin(gridX, gridZ, gridX + 1, gridZ + 1);
            for (int i = 0; i < ents.size(); i++) {
                if (ents.get(i) instanceof Player) {
                    trigger();
                    return;
                }
            }

            setState(false);
        }
    }

    public void trigger() {
        setState(true);
    }

    public boolean getState() {
        return isPressed;
    }

    public void setState(boolean state) {
        if (state) {
            level.triggerBlock(reference);
            if (!isPressed) {
                emitSound(Sound.clickUp);
                isPressed = true;
                floorTex = Texture.floorPadDown;
            }
        } else {
            if (isPressed) {
                emitSound(Sound.clickDown);
                isPressed = false;
                floorTex = Texture.floorPadUp;
            }
        }
    }
}

package com.jamie.raycasting.world.blocks;

import com.jamie.jamapp.Sfx;
import com.jamie.raycasting.world.levels.Level;

public abstract class FunctionBlock extends Block
{
    public Level level;
	public int gridX;
	public int gridZ;
	public String reference;
	public int id;


	public void setReference(String reference) {
		this.reference = reference;
	}

	public void emitSound(Sfx sound) {
		level.playSound(sound, gridX + 0.5, gridZ + 0.5);
	}
}

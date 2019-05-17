package com.jamie.raycasting.world.blocks;

import com.jamie.raycasting.world.levels.Level;

public class FunctionBlock extends Block
{
    public Level level;
	public int gridX;
	public int gridZ;
	public String reference;
	public int id;

	public void setReference(String reference) {
		this.reference = reference;
	}
}

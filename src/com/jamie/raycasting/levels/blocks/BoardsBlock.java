package com.jamie.raycasting.levels.blocks;

import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.particles.WoodParticle;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.weapons.AxeWeapon;

public class BoardsBlock extends Block
{
	private boolean smashed = false;

	public BoardsBlock() {
        isOpaque = false;
        isSolid = true;

        Sprite sprite = new Sprite(Texture.boards);
        addSprite(sprite);
	}

	public boolean use(Mob source) {
        if (!smashed) {
            if (source.getRightHandItem() instanceof AxeWeapon) {
                trigger();
                return true;
            }

            source.addHudHeading("You need an axe");
        }
        return false;
    }

    public void trigger() {
        smashed = true;
        isSolid = false;

        clearSprites();

        Sprite sprite = new Sprite(Texture.boardsSmashed);
        addSprite(sprite);

        for (int i = 0; i < 6 ; i++) {
            level.addEntity(new WoodParticle(gridX + 0.5, gridZ + 0.5));
        }
    }
}

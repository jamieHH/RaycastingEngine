package com.jamie.raycasting.entities;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Sprite;
import com.jamie.raycasting.graphics.Texture;

public class BouncingEntity extends Entity {
    public int moveX, moveZ;
    public String direction;



    public BouncingEntity() {
        Bitmap[] ts0 = {
                Texture.spinningDummy0,
                Texture.spinningDummy1,
                Texture.spinningDummy2,
                Texture.spinningDummy3,
                Texture.spinningDummy4,
        };

        setIdleSprite(new Sprite(ts0));
    }

    private boolean isWallBlocked(double x, double z) {
        if (isSolid) {
            int x0 = (int) (x + radius);
            int z0 = (int) (z + radius);
            int x1 = (int) (x - radius);
            int z1 = (int) (z - radius);

            if (level.getBlock(x0, z0).isSolid) return true;
            if (level.getBlock(x1, z0).isSolid) return true;
            if (level.getBlock(x0, z1).isSolid) return true;
            if (level.getBlock(x1, z1).isSolid) return true;

            if (!this.isFloating) {
                if (!level.getBlock(x0, z0).isWalkable) return true;
                if (!level.getBlock(x1, z0).isWalkable) return true;
                if (!level.getBlock(x0, z1).isWalkable) return true;
                if (!level.getBlock(x1, z1).isWalkable) return true;
            }
            return false;
        }

        return false;
    }
}

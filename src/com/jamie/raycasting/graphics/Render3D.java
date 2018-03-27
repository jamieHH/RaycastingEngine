package com.jamie.raycasting.graphics;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.world.blocks.*;

public class Render3D extends Render
{
	private double[] zBuffer;
	private double[] zBufferWall;

    private double xCentre = width / 2.0;
    private double yCentre = height / 2.0;

	private double cosine, sine;

	private int xBlockStart, xBlockEnd, zBlockStart, zBlockEnd;

	private double fov;

	private Mob p;

	protected Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width * height];
		zBufferWall = new double[width];
	}

	protected void render(Mob p) {
	    this.p = p;

	    xBlockStart = (int) (p.posX) - 16;
	    xBlockEnd = (int) (p.posX) + 16;
	    zBlockStart = (int) (p.posZ) - 16;
	    zBlockEnd = (int) (p.posZ) + 16;

        cosine = Math.cos(p.rotation);
        sine = Math.sin(p.rotation);

        fov = height;

        renderFloor();
        renderWalls();
        renderSprites();
        renderDistanceLimiter();
	}

    private void renderFloor() {
        for (int y = 0; y < height; y++) {
            double yDist = (y - height / 2.0) / height;

            boolean isFloor = true;
            double zDist = p.camY / yDist;
            if (yDist < 0) {
                isFloor = false;
                zDist = (p.level.height - p.camY) / -yDist;
            }

            for (int x = 0; x < width; x++) {
                double xDist = (x - width / 2.0) / height;
                xDist *= zDist;

                double xx = xDist * cosine + zDist * sine;
                double zz = zDist * cosine - xDist * sine;

                int xTexture = (int) ((xx + p.posX) * 16);
                int zTexture = (int) ((zz + p.posZ) * 16);
                int xTile = xTexture >> 4;
                int zTile = zTexture >> 4;

                zBuffer[x + y * width] = zDist;

                Block block = p.level.getBlock(xTile, zTile);
                Render tex = block.floorTex;

                if (!isFloor) {
                    tex = block.ceilTex;
                }

                pixels[x + y * width] = tex.pixels[(xTexture & 15) + (zTexture & 15) * 16];

                zBufferWall[x] = 0;
            }
        }
    }

	private void renderSprite(double x, double y, double z, Render tex) {
		double xc = (x - p.posX) * 2.0;
		double yc = (-y + (p.camY - 0.5)) * 2.0;
		double zc = (z - p.posZ) * 2.0;

		double rotX = xc * cosine - zc * sine;
		double rotY = yc;
		double rotZ = zc * cosine + xc * sine;

		if (rotZ < 0.1) return;

		double xPixel = rotX / rotZ * height + xCentre;
		double yPixel = rotY / rotZ * height + yCentre;

		double xPixelL = xPixel - height / rotZ;
		double xPixelR = xPixel + height / rotZ;
		double yPixelL = yPixel - height / rotZ;
		double yPixelR = yPixel + height / rotZ;

		int xPixelLInt = (int) Math.ceil(xPixelL);
		int xPixelRInt = (int) Math.ceil(xPixelR);
		int yPixelLInt = (int) Math.ceil(yPixelL);
		int yPixelRInt = (int) Math.ceil(yPixelR);

		if (xPixelLInt < 0) xPixelLInt = 0;
		if (xPixelRInt > width) xPixelRInt = width;
		if (yPixelLInt < 0) yPixelLInt = 0;
		if (yPixelRInt > height) yPixelRInt = height;

		double distBuffer = rotZ * 0.5;

		int scale = tex.width; // 16 // use to implement texture scales

		for (int yp = yPixelLInt; yp < yPixelRInt; yp++) {
			double pixelRotationY = (yp - yPixelL) / (yPixelR - yPixelL);
			int yTexture = (int) (pixelRotationY * scale); // 16
			for (int xp = xPixelLInt; xp < xPixelRInt; xp++) {
				double pixelRotationX = (xp - xPixelL) / (xPixelR - xPixelL);
				int xTexture = (int) (pixelRotationX * scale); // 16

				if (zBuffer[xp + yp * width] > distBuffer) {
					int colour = tex.pixels[(xTexture &15) + (yTexture &15) * 16]; // 16 // TODO: support different texture scales
					if (colour != 0xffff00ff) {
						pixels[xp + yp * width] = colour;
						zBuffer[xp + yp * width] = distBuffer;
					}
				}
			}
		}
	}


	private void renderWall(double xLeft, double zLeft, double xRight, double zRight, Render texture) {
	    double yB = 0; // Bottom y position.

		double xcLeft = (xLeft - p.posX) * 2;
		double zcLeft = (zLeft - p.posZ) * 2;

		double rotLeftSideX = xcLeft * cosine - zcLeft * sine;
        double yCornerTL = (-yB + (p.camY - 1)) * 2.0;
        double yCornerBL = (-yB + p.camY) * 2.0;
		double rotLeftSideZ = zcLeft * cosine + xcLeft * sine;

		double xcRight = (xRight - p.posX) * 2;
		double zcRight = (zRight - p.posZ) * 2;

		double rotRightSideX = xcRight * cosine - zcRight * sine;
        double yCornerTR = (-yB + (p.camY - 1)) * 2.0;
        double yCornerBR = (-yB + p.camY) * 2.0;
		double rotRightSideZ = zcRight * cosine + xcRight * sine;

		double xt0 = 0;
        double xt1;

        // below flips textures on opposite surfaces
        if (xRight - xLeft == 0) {
            xt1 = (zLeft - zRight) * 16;
        } else {
            xt1 = (xRight - xLeft) * 16;
        }

		double clip = 0.2;

		// Clipping algorithm:
		if (rotLeftSideZ < clip && rotRightSideZ < clip) return;

		if (rotLeftSideZ < clip) {
			double clip0 = (clip - rotLeftSideZ) / (rotRightSideZ - rotLeftSideZ);
			rotLeftSideZ = rotLeftSideZ + (rotRightSideZ - rotLeftSideZ) * clip0;
			rotLeftSideX = rotLeftSideX + (rotRightSideX - rotLeftSideX) * clip0;
			xt0 = xt0 + (xt1 - xt0) * clip0;
		}

		if (rotRightSideZ < clip) {
			double clip0 = (clip - rotLeftSideZ) / (rotRightSideZ - rotLeftSideZ);
			rotRightSideZ = rotLeftSideZ + (rotRightSideZ - rotLeftSideZ) * clip0;
			rotRightSideX = rotLeftSideX + (rotRightSideX - rotLeftSideX) * clip0;
			xt1 = xt0 + (xt1 - xt0) * clip0;
		}
		// End clipping algorithm

		double xPixelLeft = (rotLeftSideX / rotLeftSideZ * height + width / 2);
		double xPixelRight = (rotRightSideX / rotRightSideZ * height + width / 2);

		if (xPixelLeft >= xPixelRight) return;
		int xPixelLeftInt = (int) Math.ceil(xPixelLeft);
		int xPixelRightInt = (int) Math.ceil(xPixelRight);
		if (xPixelLeftInt < 0) xPixelLeftInt = 0;
		if (xPixelRightInt > width) xPixelRightInt = width;

		double yPixelLeftTop = (yCornerTL / rotLeftSideZ * fov + yCentre);
		double yPixelLeftBottom = (yCornerBL / rotLeftSideZ * fov + yCentre);
		double yPixelRightTop = (yCornerTR / rotRightSideZ * fov + yCentre);
		double yPixelRightBottom = (yCornerBR / rotRightSideZ * fov + yCentre);

		double iz0 = 1 / rotLeftSideZ;
		double iz1 = 1 / rotRightSideZ;
		double itx0 = xt0 / rotLeftSideZ;
		double itx1 = xt1 / rotRightSideZ - itx0;

		double iw = 1 / (xPixelRight - xPixelLeft);
		for (int x = xPixelLeftInt; x < xPixelRightInt; x++) {
			double pixelRotation = (x - xPixelLeft) * iw;
			double zWall = (iz0 + (iz1 - iz0) * pixelRotation);

			if (zBufferWall[x] > zWall) continue;
			zBufferWall[x] = zWall;
            int xTexture = (int) Math.floor((itx0 + itx1 * pixelRotation) / zWall);

			double yPixelTop = yPixelLeftTop + (yPixelRightTop - yPixelLeftTop) * pixelRotation;
			double yPixelBottom = yPixelLeftBottom + (yPixelRightBottom - yPixelLeftBottom) * pixelRotation;

			int yPixelTopInt = (int) Math.ceil(yPixelTop);
			int yPixelBottomInt = (int) Math.ceil(yPixelBottom);

			if (yPixelTopInt < 0) yPixelTopInt = 0;
			if (yPixelBottomInt > height) yPixelBottomInt = height;

            double ih = 1 / (yPixelBottom - yPixelTop);
			for (int y = yPixelTopInt; y < yPixelBottomInt; y++) {
				double pixelRotationY = (y - yPixelTop) * ih;
				int yTexture = (int) Math.floor(16 * pixelRotationY);

                int colour = texture.pixels[(xTexture & 15) + (yTexture & 15) * 16];
                if (colour != 0xffff00ff) {
                    pixels[x + y * width] = pixels[x + y * width] = colour;
                    zBuffer[x + y * width] = 1 / zWall * 0.5;
                }
			}
		}
	}

	private void renderSprites() {
        for (int xBlock = xBlockStart; xBlock <= xBlockEnd; xBlock++) {
            for (int zBlock = zBlockStart; zBlock <= zBlockEnd; zBlock++) {
                Block block = p.level.getBlock(xBlock, zBlock);
                for (int i = 0; i < block.countSprites(); i++) {
					Sprite sprite = block.getSprite(i);
                    renderSprite((xBlock + 0.5) + sprite.x, sprite.y, (zBlock + 0.5) + sprite.z, sprite.render());
                }
			}
		}

		for (int i = 0; i < p.level.countEntities(); i++) {
			Entity entity = p.level.getEntity(i);
			if (entity.isInside(xBlockStart, zBlockStart, xBlockEnd, zBlockEnd)) {
				for (int b = 0; b < entity.countSprites(); b++) {
					Sprite sprite = entity.getSprite(b);
					renderSprite(entity.posX + sprite.x, entity.posY + sprite.y, entity.posZ + sprite.z, sprite.render());
				}
			}
		}
	}

	private void renderWalls() {
        for (int xBlock = xBlockStart; xBlock <= xBlockEnd; xBlock++) {
            for (int zBlock = zBlockStart; zBlock <= zBlockEnd; zBlock++) {
                Block block = p.level.getBlock(xBlock, zBlock);
                Block east = p.level.getBlock(xBlock + 1, zBlock);
                Block south = p.level.getBlock(xBlock, zBlock + 1);

                if (block instanceof DoorBlock) {
                    double rr = 1 / 4.0;
                    double openness = 1 - ((DoorBlock) block).openness * 7 / 8;

                    if (east.isOpaque) {
                        renderWall(xBlock + openness, zBlock + 1 - rr, xBlock - 1 + openness, zBlock + 1 - rr, block.wallTex);
                        renderWall(xBlock - 1 + openness, zBlock + rr, xBlock + openness, zBlock + rr, block.wallTex);
                        renderWall(xBlock + openness, zBlock + rr, xBlock + openness, zBlock + 1 - rr, block.wallTex);
                    } else {
                        openness = 2 - openness;
                        renderWall(xBlock + 1 - rr, zBlock - 1 + openness, xBlock + 1 - rr, zBlock + openness, block.wallTex);
                        renderWall(xBlock + rr, zBlock + openness, xBlock + rr, zBlock - 1 + openness, block.wallTex);
                        renderWall(xBlock + rr, zBlock - 1 + openness, xBlock + 1 - rr, zBlock - 1 + openness, block.wallTex);
                    }
                }

                if (block.isOpaque) {
                    if (!east.isOpaque) {
                        renderWall(xBlock + 1, zBlock, xBlock + 1, zBlock + 1, block.wallTex);
                    }
                    if (!south.isOpaque) {
                        renderWall(xBlock + 1, zBlock + 1, xBlock, zBlock + 1, block.wallTex);
                    }
                } else {
                    if (east.isOpaque) {
                        renderWall(xBlock + 1, zBlock + 1, xBlock + 1, zBlock, east.wallTex);
                    }
                    if (south.isOpaque) {
                        renderWall(xBlock, zBlock + 1, xBlock + 1, zBlock + 1, south.wallTex);
                    }
                }
			}
		}
	}

	private void renderDistanceLimiter() {
		for (int i = 0; i < width * height; i++) {
			int colour = pixels[i];
			double iBuff = zBuffer[i];

//            if (iBuff > p.viewDist) {
//                pixels[i] = 0x000020;
//            } else {
            // TODO: Incorporate p.viewDistance into this equation

            int factor = 4;
            int i0 = factor * 1;
            int i1 = factor * 8;

            double xx = ((i % width - width / 2.0) / width) * i0;
            int brightness = (int) (256 - ((iBuff) * (((xx * xx) * 2) + i1)));

            if (brightness < 0) {
                brightness = 0;
            } else if (brightness > 255) {
                brightness = 255;
            }

            int r = (colour >> 16) & 0xff;
            int g = (colour >> 8) & 0xff;
            int b = (colour) & 0xff;

            r = r * brightness / 255;
            g = g * brightness / 255;
            b = b * brightness / 255;

            pixels[i] = r << 16 | g << 8 | b;
//            }
		}
	}
}

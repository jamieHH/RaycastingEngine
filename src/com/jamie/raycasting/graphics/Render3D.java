package com.jamie.raycasting.graphics;

import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.levels.blocks.*;

public class Render3D extends Render {
	
	private double[] zBuffer;
	private double[] zBufferWall;

    private double xCentre = width / 2.0;
    private double yCentre = height / 2.0;

    private double floorDepth = 8.0;
    private double ceilingHeight = 8.0;

	private double cosine, sine;

	private int xBlockStart, xBlockEnd, zBlockStart, zBlockEnd;
	private double displacedCamX, displacedCamY, displacedCamZ;

	private double fov;

	private Mob p;
	
	protected Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width * height];
		zBufferWall = new double[width];
	}
	
	protected void render(Mob p) {
	    this.p = p;

        ceilingHeight = p.level.levelHeight;

        displacedCamX = p.posX / 16;
        displacedCamY = -p.camY / 16;
        displacedCamZ = p.posZ / 16;

	    xBlockStart = (int) (p.posX / 16) - 24;
	    xBlockEnd = (int) (p.posX / 16) + 24;
	    zBlockStart = (int) (p.posZ / 16) - 24;
	    zBlockEnd = (int) (p.posZ / 16) + 24;

        cosine = Math.cos(p.rotation);
        sine = Math.sin(p.rotation);

        fov = height;

        floor();
        renderWalls();
        renderSprites();
        renderDistanceLimiter();
	}

    private void floor() {
        for (int y = 0; y < height; y++) {
            double yDist = (y - height / 2.0) / height;

            boolean isFloor = true;
            double zDist = (floorDepth + p.camY) / yDist;
            if (yDist < 0) {
                isFloor = false;
                zDist = (ceilingHeight - p.camY) / -yDist;
            }

            for (int x = 0; x < width; x++) {
//                if (zBuffer[x + y * width] < yDist) continue; // ????

                double xDist = (x - width / 2.0) / height;
                xDist *= zDist;

                double xx = xDist * cosine + zDist * sine;
                double zz = zDist * cosine - xDist * sine;

                int xTexture = (int) (xx + p.posX);
                int zTexture = (int) (zz + p.posZ);
                int xTile = (xTexture >> 4);
                int zTile = (zTexture >> 4);

                zBuffer[x + y * width] = zDist;


                Block block = p.level.getBlock(xTile, zTile);
                Render tex = block.floorTex;

                if (!isFloor) {
                    tex = block.ceilTex;
                }

                pixels[x + y * width] = tex.pixels[(xTexture & 15) + (zTexture & 15) * 16];
//                pixels[x + y * width] = 0xFFFFFF;

                zBufferWall[x] = 0;
            }
        }
    }

	private void renderSprite(double x, double y, double z, Render tex) {
		double xc = (x - displacedCamX) * 2.0;
		double yc = (-y - displacedCamY) * 2.0;
		double zc = (z - displacedCamZ) * 2.0;

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

		double distBuffer = rotZ * 8;

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


	private void renderWall(double xLeft, double xRight, double zLeft, double zRight, double yHeight, Render texture) {
		double xcLeft = (xLeft - displacedCamX) * 2;
		double zcLeft = (zLeft - displacedCamZ) * 2;

		double rotLeftSideX = xcLeft * cosine - zcLeft * sine;
		double yCornerTL = (-yHeight - displacedCamY) * 2;
		double yCornerBL = (yHeight - displacedCamY) * 2;
		double rotLeftSideZ = zcLeft * cosine + xcLeft * sine;

		double xcRight = (xRight - displacedCamX) * 2;
		double zcRight = (zRight - displacedCamZ) * 2;

		double rotRightSideX = xcRight * cosine - zcRight * sine;
		double yCornerTR = (-yHeight - displacedCamY) * 2;
		double yCornerBR = (yHeight - displacedCamY) * 2;
		double rotRightSideZ = zcRight * cosine + xcRight * sine;

		double xt0 = 0;
        double xt1 = 16;

        // below flips textures on opposite surfaces
        if (xRight - xLeft == 0) xt1 = (zLeft - zRight) * 16;
        else if (zRight - zLeft == 0) xt1 = (xRight - xLeft) * 16;

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
		double itxa = xt1 / rotRightSideZ - itx0;

		double iw = 1 / (xPixelRight - xPixelLeft);
		for (int x = xPixelLeftInt; x < xPixelRightInt; x++) {
			double pixelRotation = (x - xPixelLeft) * iw;
			double zWall = (iz0 + (iz1 - iz0) * pixelRotation);

			if (zBufferWall[x] > zWall) continue;
			zBufferWall[x] = zWall;
            int xTexture = (int) ((itx0 + itxa * pixelRotation) / zWall);

            if (xt1 < 0) xTexture -= 1; // corrects texture shift from negative tex1

			double yPixelTop = yPixelLeftTop + (yPixelRightTop - yPixelLeftTop) * pixelRotation; // + 0.5??
			double yPixelBottom = yPixelLeftBottom + (yPixelRightBottom - yPixelLeftBottom) * pixelRotation;

			int yPixelTopInt = (int) Math.ceil(yPixelTop);
			int yPixelBottomInt = (int) Math.ceil(yPixelBottom);

			if (yPixelTopInt < 0) yPixelTopInt = 0;
			if (yPixelBottomInt > height) yPixelBottomInt = height;

            double ih = 1 / (yPixelBottom - yPixelTop);
			for (int y = yPixelTopInt; y < yPixelBottomInt; y++) {
				double pixelRotationY = (y - yPixelTop) * ih;
				int yTexture = (int) (16 * pixelRotationY);

                int colour = texture.pixels[(xTexture & 15) + (yTexture & 15) * 16];
                if (colour != 0xffff00ff) {
                    pixels[x + y * width] = pixels[x + y * width] = colour;
                    zBuffer[x + y * width] = 1 / zWall * 8;
                }
			}
		}
	}

	private void renderSprites() {
        for (int xBlock = xBlockStart; xBlock <= xBlockEnd; xBlock++) {
            for (int zBlock = zBlockStart; zBlock <= zBlockEnd; zBlock++) {
                Block block = p.level.getBlock(xBlock, zBlock);
                for (int i = 0; i < block.countSprites(); i++) {
					Sprite sprite = block.getSprite(block.spriteIndex);
                    renderSprite((xBlock + 0.5) + sprite.x, sprite.y, (zBlock + 0.5) + sprite.z, sprite.texture);
                }
			}
		}

        for (int i = 0; i < p.level.countEntities(); i++) {
            Entity entity = p.level.getEntity(i);
            if (entity.countSprites() > 0) {
                Sprite sprite = entity.getSprite(entity.spriteIndex);
                renderSprite((entity.posX + sprite.x) / 16, entity.posY + sprite.y, (entity.posZ + sprite.z) / 16, sprite.texture); // replace with: entity.posY + sprite.y
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

                    if (east.solidRender) {
                        renderWall(xBlock + openness, xBlock - 1 + openness, zBlock + 1 - rr, zBlock + 1 - rr, 0.5, block.wallTex);
                        renderWall(xBlock - 1 + openness, xBlock + openness, zBlock + rr, zBlock + rr, 0.5, block.wallTex);
                        renderWall(xBlock + openness, xBlock + openness, zBlock + rr, zBlock + 1 - rr, 0.5, block.wallTex);
                    } else {
                        openness = 2 - openness;
                        renderWall(xBlock + 1 - rr, xBlock + 1 - rr, zBlock - 1 + openness, zBlock + openness, 0.5, block.wallTex);
                        renderWall(xBlock + rr, xBlock + rr, zBlock + openness, zBlock - 1 + openness, 0.5, block.wallTex);
                        renderWall(xBlock + rr, xBlock + 1 - rr, zBlock - 1 + openness, zBlock - 1 + openness, 0.5, block.wallTex);
                    }
                }

                if (block.solidRender) {
                    if (!east.solidRender) {
                        renderWall(xBlock + 1, xBlock + 1, zBlock, zBlock + 1, 0.5, block.wallTex);
                    }
                    if (!south.solidRender) {
                        renderWall(xBlock + 1, xBlock, zBlock + 1, zBlock + 1, 0.5, block.wallTex);
                    }
                } else {
                    if (east.solidRender) {
                        renderWall(xBlock + 1, xBlock + 1, zBlock + 1, zBlock, 0.5, east.wallTex);
                    }
                    if (south.solidRender) {
                        renderWall(xBlock, xBlock + 1, zBlock + 1, zBlock + 1, 0.5, south.wallTex);
                    }
                }
			}
		}
	}

	private void renderDistanceLimiter() {
//        int renderDist = p.viewDist;
        int renderDist = 4092;
		int dropOfMult = 512;
		for (int i = 0; i < width * height; i++) {
			int colour = pixels[i];
			double iBuff = zBuffer[i];

			if (false && iBuff > p.viewDist) {
//                int xx = ((int) Math.floor((i % width) - p.rotation * 512 / (Math.PI * 2))) & 511;
//                int yy = i / width;
//                pixels[i] = Texture.sky.pixels[xx + yy * 512];

                pixels[i] = 0x000020;
            } else {
                int xp = (i % width);
				int yp = (i / width) * 14;
				double xx = ((i % width - width / 2.0) / width);

				// TODO: Incorporate p.viewDistance into this equation
                int brightness = (int) (256 - ((iBuff) * (((xx * xx) * 2) + 2)));
//				int brightness = (int) ((renderDist / iBuff) / (((xx * xx) * 2) + 2));
//                brightness = (brightness + ((xp + yp) & 3) * 4) >> 5 << 4;
//                brightness = (brightness + ((xp + yp) & 3) * 4) >> 4 << 2;
//                brightness = (brightness + ((xp + yp) & 3) * 4) >> 4 << 2;

//				int brightness = (int) (renderDist / iBuff);
//				brightness = brightness - (int) (iBuff / dropOfMult);

				if (brightness < 0) brightness = 0;
                else if (brightness > 255) brightness = 255;

                int r = (colour >> 16) & 0xff;
                int g = (colour >> 8) & 0xff;
                int b = (colour) & 0xff;

                r = r * brightness / 255;
                g = g * brightness / 255;
                b = b * brightness / 255;

                pixels[i] = r << 16 | g << 8 | b;
            }
		}
	}
}

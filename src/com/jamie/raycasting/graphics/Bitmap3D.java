package com.jamie.raycasting.graphics;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.entities.Entity;
import com.jamie.raycasting.entities.particles.Particle;
import com.jamie.raycasting.world.blocks.*;
import com.jamie.raycasting.world.levels.Level;

public class Bitmap3D extends Bitmap
{
    private int blockViewDist = 8;
    private int xBlockStart, xBlockEnd, zBlockStart, zBlockEnd;

    private double[] zBuffer;
    private double[] zBufferWall;

    private double xCentre = width / 2.0;
    private double yCentre = height / 2.5; // adjust to 'tilt' the horizon line
    private double cosine, sine;
    private double fov;

    private double px, py, pz;
    private Level level;


	public Bitmap3D(int width, int height) {
		super(width, height);
		setSize(width, height);
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		zBuffer = new double[width * height];
		zBufferWall = new double[width];
		fov = height;

		yCentre = height / 2.5;
		xCentre = width / 2.0;
	}

	public void render(Level level, double x, double y, double z, double rotation, double pitch, int viewDist) {
		this.level = level;
		px = x;
		py = y;
		pz = z;
		yCentre = height * pitch;
		cosine = Math.cos(rotation);
		sine = Math.sin(rotation);

		xBlockStart = (int) (px) - blockViewDist;
		xBlockEnd = (int) (px) + blockViewDist;
		zBlockStart = (int) (pz) - blockViewDist;
		zBlockEnd = (int) (pz) + blockViewDist;
		blockViewDist = viewDist;

        drawFloor();
        drawWalls();
        drawSprites();
        drawFog();
	}
    private void drawFloor() {
        for (int y = 0; y < height; y++) {
        	double yDist = (y - yCentre) / fov;
			double zDist = py / yDist;

			boolean isFloor = true;
            if (yDist < 0) {
                isFloor = false;
				zDist = (level.height - py) / -yDist;
            }

            for (int x = 0; x < width; x++) {
				double xDist = (x - xCentre) / height;
				xDist *= zDist;

				double xx = xDist * cosine + zDist * sine;
				double zz = zDist * cosine - xDist * sine;
				int xTexture = (int) Math.floor((xx + px) * 16);
				int zTexture = (int) Math.floor((zz + pz) * 16);
				int xTile = xTexture >> 4;
				int zTile = zTexture >> 4;

				Block block = level.getBlock(xTile, zTile);

				Bitmap tex;
				if (isFloor) {
					tex = block.floorTex;
				} else {
					tex = block.ceilTex;
				}

				if (tex != null) {
					pixels[x + y * width] = tex.pixels[(xTexture & 15) + (zTexture & 15) * 16];
				}
				zBuffer[x + y * width] = zDist;
                zBufferWall[x] = 0;
            }
        }
    }

	private void drawSprite(double x, double y, double z, Bitmap tex) {
		double xc = (x - px) * 2;
		double yc = (-y + (py - 0.5)) * 2;
		double zc = (z - pz) * 2;

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

//		int scale = tex.width; // this will fill the block width
		int scale = 16; // this will keep sprites to scale
		if (tex.width != scale || tex.height != scale) {
			Bitmap nTex = new Bitmap(scale, scale);
			nTex.draw(tex, (scale / 2) - tex.halfWidth(), scale - tex.height);
			tex = nTex; // makes a new texture to scale and draws the smaller one over top
		}

		for (int yp = yPixelLInt; yp < yPixelRInt; yp++) {
			double pixelRotationY = (yp - yPixelL) / (yPixelR - yPixelL);
			int yTexture = (int) (pixelRotationY * scale); // tex.height
			for (int xp = xPixelLInt; xp < xPixelRInt; xp++) {
				double pixelRotationX = (xp - xPixelL) / (xPixelR - xPixelL);
				int xTexture = (int) (pixelRotationX * scale); // tex.width

				if (zBuffer[xp + yp * width] > distBuffer) {
					int colour = tex.pixels[xTexture + yTexture * scale];
					if (colour != INVISIBLE) {
						pixels[xp + yp * width] = colour;
						zBuffer[xp + yp * width] = distBuffer;
					}
				}
			}
		}
	}


	private void drawWall(double xLeft, double zLeft, double xRight, double zRight, Bitmap texture) {
		drawWall(xLeft, zLeft, xRight, zRight, 0, texture);
	}


	private void drawWall(double xLeft, double zLeft, double xRight, double zRight, int yHeight, Bitmap texture) {
		if (texture == null) return;
		int texWidth = texture.width;
	    double yB = yHeight; // start y position.
	    double yT = 1; // y height.

		double xcLeft = (xLeft - px) * 2;
		double zcLeft = (zLeft - pz) * 2;

		double rotLeftSideX = xcLeft * cosine - zcLeft * sine;
        double yCornerTL = (-yB + (py - yT)) * 2;
        double yCornerBL = (-yB + py) * 2;
		double rotLeftSideZ = zcLeft * cosine + xcLeft * sine;

		double xcRight = (xRight - px) * 2;
		double zcRight = (zRight - pz) * 2;

		double rotRightSideX = xcRight * cosine - zcRight * sine;
        double yCornerTR = (-yB + (py - yT)) * 2;
        double yCornerBR = (-yB + py) * 2;
		double rotRightSideZ = zcRight * cosine + xcRight * sine;

		double xt0 = 0;
        double xt1;

        // below flips textures on opposite surfaces
        if (xRight - xLeft == 0) {
            xt1 = (zLeft - zRight) * texWidth;
        } else {
            xt1 = (xRight - xLeft) * texWidth;
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

		double xPixelLeft = (rotLeftSideX / rotLeftSideZ * height + xCentre);
		double xPixelRight = (rotRightSideX / rotRightSideZ * height + xCentre);

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
				int yTexture = (int) Math.floor(texWidth * pixelRotationY);

                int colour = texture.pixels[(xTexture & 15) + (yTexture & 15) * texWidth];
                if (colour != INVISIBLE) {
                    pixels[x + y * width] = colour;
                    zBuffer[x + y * width] = 1 / zWall * 0.5;
                }
			}
		}
	}

	private void drawSprites() {
        for (int xBlock = xBlockStart; xBlock <= xBlockEnd; xBlock++) {
            for (int zBlock = zBlockStart; zBlock <= zBlockEnd; zBlock++) {
				Sprite sprite = level.getBlock(xBlock, zBlock).getSprite();
				if (sprite != null && sprite.bitmap() != null) {
					if (level.getBlock(xBlock, zBlock) instanceof TorchBlock) {
						if (level.getBlock(xBlock + 1, zBlock).isSolid) {
							drawSprite((xBlock + 0.95) + sprite.x, sprite.y + 0.5, (zBlock + 0.5) + sprite.z, sprite.bitmap());
						} else if (level.getBlock(xBlock - 1, zBlock).isSolid) {
							drawSprite((xBlock + 0.05) + sprite.x, sprite.y + 0.5, (zBlock + 0.5) + sprite.z, sprite.bitmap());
						} else if (level.getBlock(xBlock, zBlock + 1).isSolid) {
							drawSprite((xBlock + 0.5) + sprite.x, sprite.y + 0.5, (zBlock + 0.95) + sprite.z, sprite.bitmap());
						} else if (level.getBlock(xBlock, zBlock - 1).isSolid) {
							drawSprite((xBlock + 0.5) + sprite.x, sprite.y + 0.5, (zBlock + 0.05) + sprite.z, sprite.bitmap());
						} else {
							drawSprite((xBlock + 0.5) + sprite.x, sprite.y, (zBlock + 0.5) + sprite.z, sprite.bitmap());
						}
					} else {
						drawSprite((xBlock + 0.5) + sprite.x, sprite.y, (zBlock + 0.5) + sprite.z, sprite.bitmap());
					}
				}
			}
		}

		for (int i = 0; i < level.countEntities(); i++) {
			Entity entity = level.getEntity(i);
			if (entity.isInside(xBlockStart, zBlockStart, xBlockEnd, zBlockEnd)) {
				Sprite sprite = entity.getRenderSprite();
				if (sprite != null && sprite.bitmap() != null) {
					drawSprite(entity.posX + sprite.x, sprite.y, entity.posZ + sprite.z, sprite.bitmap());
				}

				if (entity instanceof Particle) {
					for (int j = 0; j < ((Particle) entity).getSpriteParticles().length; j++) {
						Sprite spriteP = ((Particle) entity).getSpriteParticles()[j];
						drawSprite(entity.posX + spriteP.x, spriteP.y, entity.posZ + spriteP.z, spriteP.bitmap());
					}
				}
			}
		}
	}

	private void drawWalls() {
        for (int xBlock = xBlockStart; xBlock <= xBlockEnd; xBlock++) {
            for (int zBlock = zBlockStart; zBlock <= zBlockEnd; zBlock++) {
                Block block = level.getBlock(xBlock, zBlock);
                Block east = level.getBlock(xBlock + 1, zBlock);
                Block south = level.getBlock(xBlock, zBlock + 1);

				if (block instanceof DoorBlock) {
					double rr = 1 / 4.0;
					double openness = 1 - ((DoorBlock) block).openness * 7 / 8;

					if (east.isOpaque) {
						drawWall(xBlock + openness, zBlock + 1 - rr, xBlock - 1 + openness, zBlock + 1 - rr, block.wallTex);
						drawWall(xBlock - 1 + openness, zBlock + rr, xBlock + openness, zBlock + rr, block.wallTex);
						drawWall(xBlock + openness, zBlock + rr, xBlock + openness, zBlock + 1 - rr, block.wallTex);
					} else {
						openness = 2 - openness;
						drawWall(xBlock + 1 - rr, zBlock - 1 + openness, xBlock + 1 - rr, zBlock + openness, block.wallTex);
						drawWall(xBlock + rr, zBlock + openness, xBlock + rr, zBlock - 1 + openness, block.wallTex);
						drawWall(xBlock + rr, zBlock - 1 + openness, xBlock + 1 - rr, zBlock - 1 + openness, block.wallTex);
					}
				}

				int i = 0; // i can be replaced with the desired y start height
				if (block.isOpaque) {
					if (!east.isOpaque) {
						drawWall(xBlock + 1, zBlock, xBlock + 1, zBlock + 1, i, block.wallTex);
					}
					if (!south.isOpaque) {
						drawWall(xBlock + 1, zBlock + 1, xBlock, zBlock + 1, i, block.wallTex);
					}
				} else {
					if (east.isOpaque) {
						drawWall(xBlock + 1, zBlock + 1, xBlock + 1, zBlock, i, east.wallTex);
					}
					if (south.isOpaque) {
						drawWall(xBlock, zBlock + 1, xBlock + 1, zBlock + 1, i, south.wallTex);
					}
				}
			}
		}
	}

	private void drawFog() {
        int dist = (32 * blockViewDist); // render dist. 32 = one blocks dist
		for (int i = 0; i < width * height; i++) {
            if (zBuffer[i] > blockViewDist) {
                pixels[i] = level.fogColor;
            } else {
				double xx = ((i % width - xCentre) / width) * 4;
				double x2x = (((xx * xx) * 2) + 32);
				int brightness = (int) (dist - zBuffer[i] * x2x);
				// divide this value to soften the fog without changing the distance

				if (brightness < 0) brightness = 0;
				else if (brightness > 255) brightness = 255;
				double density = ((double) (brightness) / 255) * 100;

				pixels[i] = blendColor(pixels[i], level.fogColor, density);
			}
		}
	}
}

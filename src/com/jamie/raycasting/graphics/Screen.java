package com.jamie.raycasting.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;
import com.jamie.raycasting.input.InputHandler;

public class Screen extends Render
{
    private Mob p;

    private Render3D render;
    private Render3D superSampleRender;
    private Render3D downSampleRender;

    private Render viewPunch;
	private Render hudBar;

	private Render healthBarIcon;
	private Render healthBarBorder;
	private Render healthBar;

    public Render menuBackground;

    private Random random = new Random();

	public Screen(int width, int height, Game game) {
		super(width, height);
		p = game.player;

        // HUD
        hudBar = new Render(width, 9);
        for (int i = 0; i < hudBar.pixels.length; i++) {
            if (i < hudBar.pixels.length - (hudBar.pixels.length - hudBar.width)) {
                hudBar.pixels[i] = 0x404040;
            } else {
                hudBar.pixels[i] = 0x606060;
            }
        }

        // 3D render
        render = new Render3D(width, height - hudBar.height);
        superSampleRender = new Render3D(render.width * 2, render.height * 2);
        downSampleRender = new Render3D(render.width / 2, render.height / 2);

        viewPunch = new Render(width, height - hudBar.height);


        // HUD Items
        healthBarIcon = new Render(6, 4);
        int[] iconPixels = {1, 3, 6, 7, 8, 9, 10, 13, 14, 15, 20};
        for (int i = 0; i < iconPixels.length; i++) {
            healthBarIcon.pixels[iconPixels[i]] = 0xF00000;
        }

        healthBarBorder = new Render(32, 4);
        for (int i = 0; i < healthBarBorder.pixels.length; i++) {
            healthBarBorder.pixels[i] = 0x808080;
        }

        healthBar = new Render(30, 2);


        // Overlay Background
		menuBackground = new Render(width, height);

		int pBannerXSt = menuBackground.width * (int) (menuBackground.height * 0.2);
		int pBannerXEnd = menuBackground.width * (int) (menuBackground.height * 0.8);

        for (int i = 0; i < menuBackground.pixels.length; i++) {
            if (i >= pBannerXSt && i < pBannerXSt + menuBackground.width) {
                menuBackground.pixels[i] = 0x606060;
            } else if (i >= pBannerXSt + menuBackground.width && i < (pBannerXEnd - menuBackground.width)) {
                menuBackground.pixels[i] = 0x202020;
            } else if (i >= (pBannerXEnd - menuBackground.width) && i < pBannerXEnd) {
                menuBackground.pixels[i] = 0x606060;
            }
        }
    }
	
	public void render(Game game) {
		if (game.activeOverlay != null) {
		    if (game.activeOverlay instanceof LoadingOverlay) {
                ((LoadingOverlay) game.activeOverlay).render(this, game.level.name);
            } else {
                game.activeOverlay.render(this);
            }
        } else {
            for (int i = 0; i < (width * height); i++) {
                pixels[i] = 0;
            }

            // 3D Render objects
            p = game.player;

            int theCase = 2;

            if (theCase == 0) {
                render.render(p);
            } else if (theCase == 1) {
                superSampleRender.render(p);
                int[] sspx = superSampleRender.pixels;

                for (int i = 0; i < render.pixels.length; i++) {
                    int h = (i / render.width);
                    int i0 = (((h * render.width) + i) * 2);
                    int i1 = ((((h + 1) * render.width) + i) * 2);

                    int pix0 = sspx[i0];
                    int pix1 = sspx[i0 + 1];
                    int pix2 = sspx[i1];
                    int pix3 = sspx[i1 + 1];

                    int pix = (pix0 + pix1 + pix2 + pix3) / 4;

                    render.pixels[i] = pix;
                }
            } else if (theCase == 2) {
                downSampleRender.render(p);
                int[] dspx = downSampleRender.pixels;

                for (int i = 0; i < downSampleRender.pixels.length; i++) {
                    int h = (i / downSampleRender.width);
                    int i0 = (((h * downSampleRender.width) + i) * 2);
                    int i1 = ((((h + 1) * downSampleRender.width) + i) * 2);

                    int pix = dspx[i];

                    render.pixels[i0] = pix;
                    render.pixels[i0 + 1] = pix;
                    render.pixels[i1] = pix;
                    render.pixels[i1 + 1] = pix;
                }
            }
            draw(render, 0, 0);



            draw(hudBar, 0, height - hudBar.height);

            double healthBarWidth = ((double) p.health / (double) p.maxHealth) * (double) healthBar.width;

            for (int i = 0; i < healthBar.pixels.length; i++) {
                if (i < healthBarWidth || (i >= healthBar.width && i < (healthBarWidth + healthBar.width))) {
                    healthBar.pixels[i] = 0xF00000;
                } else {
                    healthBar.pixels[i] = 0xD07070;
                }
            }

            draw(healthBarIcon, 2, (height - hudBar.height) + 3);
            draw(healthBarBorder, 8, (height - hudBar.height) + 3);
            draw(healthBar, 9, (height - hudBar.height) + 4);
            if (p.getRightHandItem() != null) {
                draw(p.getRightHandItem().name, width - (p.getRightHandItem().name.length() * 6) - 2, (height - hudBar.height) + 1, 0x909090);
            }

            if (p.hurtTime >= 0) {
                double percentage = p.hurtTime / 60.0;
                for (int i = 0; i < viewPunch.pixels.length; i++) {
                    double xp = ((i % width) - viewPunch.width / 2.0) / width * 2;
                    double yp = ((i / width) - viewPunch.height / 2.0) / viewPunch.height * 2;
                    if (random.nextDouble() < percentage * Math.sqrt(xp * xp + yp * yp)) {
                        if (random.nextBoolean()) {
                            viewPunch.pixels[i] = 0x101010;
                        } else {
                            viewPunch.pixels[i] = 0x801010;
                        }
                    } else {
                        viewPunch.pixels[i] = 0;
                    }
                }
                draw(viewPunch, 0, 0);
            }

            for (int i = 0; i < p.hudHeadings.size(); i++) {
                draw(p.hudHeadings.get(i), (render.width - 2) - (p.hudHeadings.get(i).length() * 6), (i * 10) + 2, 0xF0F070);
            }
        }
    }
}

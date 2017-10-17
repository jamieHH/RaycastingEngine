package com.jamie.raycasting.graphics;

import java.util.Random;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;
import com.jamie.raycasting.input.InputHandler;

public class Screen extends Render
{
    private Mob p = new Mob(new InputHandler());

    private Render3D render;
    private Render viewPunch;
	private Render equippedItem;
	private Render hudBar;

	private Render healthBarIcon;
	private Render healthBarBorder;
	private Render healthBar;
	private Render itemName;

    public Render menuBackground;

    private Random random = new Random();

	public Screen(int width, int height, Game game) {
		super(width, height);
		p = game.player;

        equippedItem = Texture.screenSpear0;

        // HUD
        hudBar = new Render(width, 9);
        for (int i = 0; i < hudBar.pixels.length; i++) {
            if (i < (hudBar.pixels.length) - ((hudBar.pixels.length) - hudBar.width)) {
                hudBar.pixels[i] = 0x404040;
            } else {
                hudBar.pixels[i] = 0x606060;
            }
        }

        // 3D render
        render = new Render3D(width, height - (hudBar.height));

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

        itemName = new Render(64, 7);


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
		    return;
        }

        for (int i = 0; i < (width * height); i++ ) {
            pixels[i] = 0;
        }

        // 3D Render objects
        p = game.player;
        render.render(p);

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

//        draw(equippedItem, 0, render.height - equippedItem.height);
    }
}

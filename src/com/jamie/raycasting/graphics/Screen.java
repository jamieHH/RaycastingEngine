package com.jamie.raycasting.graphics;

import java.util.Random;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.LoadingOverlay;

public class Screen extends Render {
    public Mob p = new Mob();

    private Render3D render;
	private Render equipedItem;
	private Render hudBar;

	private Render healthBarIcon;
	private Render healthBarBorder;
	private Render healthBar;

    private Render viewPunch;

    public Render menuBackground;

    private Random random = new Random();

	public Screen(int width, int height, Game game) {
		super(width, height);

		p = game.player;

        // 3D render
		render = new Render3D(width, height - (height / 8));

		// HUD
        hudBar = new Render(width, height / 8);
        for (int i = 0; i < hudBar.width * hudBar.height; i++) {
            if (i < (hudBar.width * hudBar.height) - ((hudBar.width * hudBar.height) - hudBar.width)) {
                hudBar.pixels[i] = 0x404040;
            } else {
                hudBar.pixels[i] = 0x606060;
            }
        }

        equipedItem = Texture.screenSpear0;

        // HUD Items
        healthBarIcon = new Render(6, 4);
        int[] iconPixels = {1, 3, 6, 7, 8, 9, 10, 13, 14, 15, 20};
        for (int i = 0; i < iconPixels.length; i++) {
            healthBarIcon.pixels[iconPixels[i]] = 0xF00000;
        }

        healthBarBorder = new Render(32, 4);
        for (int i = 0; i < healthBarBorder.width * healthBarBorder.height; i++) {
            healthBarBorder.pixels[i] = 0x808080;
        }

        healthBar = new Render(30, 2);

		viewPunch = new Render(width, height - hudBar.height);

		// Overlay Background
		menuBackground = new Render(width, height);

		int pBannerXSt = menuBackground.width * (int) (menuBackground.height * 0.2);
		int pBannerXEnd = menuBackground.width * (int) (menuBackground.height * 0.8);

        for (int i = 0; i < menuBackground.width * menuBackground.height; i++) {
            if (i >= pBannerXSt && i < pBannerXSt + menuBackground.width) {
                menuBackground.pixels[i] = 0x606060;
            } else if (i >= pBannerXSt + menuBackground.width && i < (pBannerXEnd - menuBackground.width)) {
                menuBackground.pixels[i] = 0x202020;
            } else if (i >= (pBannerXEnd - menuBackground.width) && i < pBannerXEnd) {
                menuBackground.pixels[i] = 0x606060;
            } else {
//                menuBackground.pixels[i] = 0xF00000;
            }
        }
    }
	
	public void render(Game game) {
		if (game.menu != null) {
		    if (game.menu instanceof LoadingOverlay) {
                ((LoadingOverlay) game.menu).render(this, game.level.name);
            } else {
                game.menu.render(this);
            }
		    return;
        }

        for (int i = 0; i < (width * height); i++ ) {
            pixels[i] = 0;
        }

        // 3D Render objects
        p = game.player;
//        p = (Mob) game.level.entities.get(1);
        render.render(p);

        draw(render, 0, 0);

        draw(hudBar, 0, height - hudBar.height);

        double healthBarWidth = ((double) p.health / (double) p.maxHealth) * (double) healthBar.width;

        for (int i = 0; i < healthBar.width * healthBar.height; i++) {
            if (i < healthBarWidth || (i >= healthBar.width && i < (healthBarWidth + healthBar.width))) {
                healthBar.pixels[i] = 0xF00000;
            } else {
                healthBar.pixels[i] = 0xD07070;
            }
        }

        draw(healthBarIcon, 2, (height - hudBar.height) + 3);
        draw(healthBarBorder, 8, (height - hudBar.height) + 3);
        draw(healthBar, 9, (height - hudBar.height) + 4);

        if (p.damageTime >= 0) {
            double percentage = ((p.damageTime) / 60.0);
            for (int i = 0; i < viewPunch.width * viewPunch.height; i++) {
                if (random.nextInt(viewPunch.width * viewPunch.height) < ((percentage / 2) * (viewPunch.width * viewPunch.height))) {
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

        draw(equipedItem, 0, render.height - equipedItem.height);
        // TODO: Figure out why textures have negative hex values
    }
}

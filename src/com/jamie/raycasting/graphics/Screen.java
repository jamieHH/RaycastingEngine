package com.jamie.raycasting.graphics;

import java.util.Random;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.menus.InventoryOverlay;
import com.jamie.raycasting.graphics.overlays.menus.Menu;

public class Screen extends Render
{
    private Mob p;

    private Render3D render;

    private Render viewPunch;
	private Render hudBar;

	private Render healthBarIcon;
	private Render healthBarBorder;
	private Render healthBar;

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
    }
	
	public void render(Game game) {
        for (int i = 0; i < (width * height); i++) {
            pixels[i] = 0;
        }

        p = game.player;

        if (p != null) {
            render.render(p);
            draw(render, 0, 0);

            // Render held items
            if (p.getRightHandItem() != null) {
                Render rightItemTex = p.getRightHandItem().render();
                if (rightItemTex != null) {
                    draw(rightItemTex, render.width - rightItemTex.width, (render.height - rightItemTex.height) + ((int) (p.yBob * 100)) + 4);
                }
            }

            // render hudbar
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

            // Render pain
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

            // Render hud headings
            for (int i = 0; i < p.hudHeadings.size(); i++) {
                draw(p.hudHeadings.get(i), (render.width - 2) - (p.hudHeadings.get(i).length() * 6), (i * 10) + 2, 0xF0F070);
            }
        }

        if (game.activeOverlay != null) {
            game.activeOverlay.update();
            int cornerX = 0;
            int cornerY = 0;

            if (game.activeOverlay instanceof Menu) {
                cornerX = 0;
                cornerY = (int) (height * 0.2);
            } else if (game.activeOverlay instanceof InventoryOverlay) {
                cornerX = (int) (width * 0.1);
                cornerY = (int) (height * 0.2);
            }
            draw(game.activeOverlay, cornerX, cornerY); // find out why no draw
        }
    }
}

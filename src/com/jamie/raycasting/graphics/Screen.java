package com.jamie.raycasting.graphics;

import java.util.Random;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.HudBarOverlay;
import com.jamie.raycasting.graphics.overlays.StatBarOverlay;
import com.jamie.raycasting.graphics.overlays.ViewPunchOverlay;
import com.jamie.raycasting.graphics.overlays.menus.InventoryOverlay;
import com.jamie.raycasting.graphics.overlays.menus.Menu;

public class Screen extends Render
{
    private Mob p;

    private Render3D render;

    private ViewPunchOverlay viewPunch;
	private HudBarOverlay hudBar;

	private Render healthBarIcon;
	private StatBarOverlay healthBar;

    private Random random = new Random();

	public Screen(int width, int height, Game game) {
		super(width, height);
		p = game.player;

        // HUD
        hudBar = new HudBarOverlay(width, 9);
        healthBarIcon = new Render(6, 4);
        int[] iconPixels = {1, 3, 6, 7, 8, 9, 10, 13, 14, 15, 20};
        for (int i = 0; i < iconPixels.length; i++) {
            healthBarIcon.pixels[iconPixels[i]] = 0xF00000;
        }
        healthBar = new StatBarOverlay(40, 4, healthBarIcon);

        // 3D render
        render = new Render3D(width, height - hudBar.height);
        viewPunch = new ViewPunchOverlay(width, height - hudBar.height);
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
            if (p.getRightHandItem() != null) {
                hudBar.update(p.getRightHandItem().name);
            } else {
                hudBar.update("");
            }
            draw(hudBar, 0, height - hudBar.height);

            healthBar.update((double) (p.health) / (double) (p.maxHealth), 0xF00000);
            draw(healthBar, 2, (height - hudBar.height) + 3);

            // Render pain
            if (p.hurtTime >= 0) {
                viewPunch.update(p.hurtTime / 60.0, p.hurtType);
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
            draw(game.activeOverlay, cornerX, cornerY);
        }
    }
}

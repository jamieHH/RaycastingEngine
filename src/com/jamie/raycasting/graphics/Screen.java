package com.jamie.raycasting.graphics;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.HotkeyOverlay;
import com.jamie.raycasting.graphics.overlays.HudBarOverlay;
import com.jamie.raycasting.graphics.overlays.StatBarOverlay;
import com.jamie.raycasting.graphics.overlays.ViewPunchOverlay;

public class Screen extends Render
{
    private Mob p;

    private Render3D render;

    private ViewPunchOverlay viewPunch;
	private HudBarOverlay hudBar;

	private StatBarOverlay healthBar;

	public Screen(int width, int height, Game game) {
		super(width, height);
		p = game.player;

        // HUD
        hudBar = new HudBarOverlay(width, 9);
        healthBar = new StatBarOverlay(40, 4, Texture.heartIcon);

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

            healthBar.update((double) p.health / (double) p.maxHealth, 0xF00000);
            draw(healthBar, 2, (height - hudBar.height) + 3);

            // Render pain
            if (p.hurtTime >= 0) {
                viewPunch.update(p.hurtTime / 60.0, p.hurtType);
                draw(viewPunch, 0, 0);
            }

            // Render mobEffect bars
            for (int i = 0; i < p.mobEffects.size(); i++) {
                StatBarOverlay statBar = new StatBarOverlay(30, 4, p.mobEffects.get(i).effectHudIcon);
                statBar.update((double) p.mobEffects.get(i).duration / (double) p.mobEffects.get(i).maxDuration, p.mobEffects.get(i).effectHudColour);
                draw(statBar, 2, 2 + (i * 5));
            }

            // Render hud headings
            for (int i = 0; i < p.hudHeadings.size(); i++) {
                draw(p.hudHeadings.get(i), (render.width - 2) - (p.hudHeadings.get(i).length() * 6), (i * 10) + 2, 0xF0F070);
            }

            // Hotkey items
            for (int i = 1; i < 4; i++) {
                HotkeyOverlay hkIcon = new HotkeyOverlay();
                if (p.hotKeys.get(i) != null) {
                    hkIcon.update(p.inventory.getItem(p.hotKeys.get(i)));
                } else {
                    hkIcon.update(null);
                }
                draw(hkIcon, width - ((hkIcon.width + 1) * 4) + ((hkIcon.width + 1) * i), height - hkIcon.height);
            }
        }

        if (game.activeOverlay != null) {
            game.activeOverlay.update();
            int cornerX = (width / 2) - (game.activeOverlay.width / 2);
            int cornerY = (height / 2) - (game.activeOverlay.height / 2);
            draw(game.activeOverlay, cornerX, cornerY);
        }
    }
}

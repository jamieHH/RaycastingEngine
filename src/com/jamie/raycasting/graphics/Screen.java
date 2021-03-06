package com.jamie.raycasting.graphics;

import com.jamie.jamapp.Display;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.HotkeyOverlay;
import com.jamie.raycasting.graphics.overlays.HudBarOverlay;
import com.jamie.raycasting.graphics.overlays.StatBarOverlay;
import com.jamie.raycasting.graphics.overlays.ViewPunchOverlay;

public class Screen extends Display
{
    private Mob p;
    private Bitmap3D render;
    private ViewPunchOverlay viewPunch;
	private HudBarOverlay hudBar;
	private StatBarOverlay healthBar;


	public Screen(int width, int height) {
		super(width, height);
		setSize(width, height);
        this.p = Client.getPlayer();
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        Client.resizeMenus();
        // HUD
        hudBar = new HudBarOverlay(width, 9);
        healthBar = new StatBarOverlay(40, 4, Texture.heartIcon);
        // 3D render
        render = new Bitmap3D(width, height - hudBar.height);
        viewPunch = new ViewPunchOverlay(render.width, render.height);
    }

    public void tick() {
        fill(0x000000);
        this.p = Client.getPlayer();
        if (p != null) {
            if (!p.isDead) {
                render.render(p.level, p.posX, p.camY, p.posZ, p.getRotation(), (1 - p.camPitch), p.viewDist);
                draw(render, 0, 0);

                // Render held items
                if (p.getRightHandItem() != null) {
                    Bitmap rightItemTex = p.getRightHandItem().bitmap();
                    if (rightItemTex != null) {
                        int yOffs = (render.height - rightItemTex.height) + ((int) (p.camPitch * -8)) + ((int) (p.yBob * 100));
                        draw(rightItemTex, render.halfWidth() - rightItemTex.halfWidth(), yOffs + 10);
                    }
                }

                // Render pain
                if (p.hurtTicks >= 0) {
                    viewPunch.update(p.hurtTicks / 60.0, p.hurtType);
                    draw(viewPunch, 0, 0, 75);
                }

                // Render mobEffect bars
                for (int i = 0; i < p.mobEffects.size(); i++) {
                    StatBarOverlay statBar = new StatBarOverlay(30, 4, p.mobEffects.get(i).effectHudIcon);
                    statBar.update((double) p.mobEffects.get(i).duration / (double) p.mobEffects.get(i).maxDuration, p.mobEffects.get(i).effectHudColour, 0x808080);
                    draw(statBar, 2, 2 + (i * 5));
                }

                // Render hud headings
                for (int i = 0; i < p.hudHeadings.size(); i++) {
                    draw(p.hudHeadings.get(i), (render.width - 2) - (p.hudHeadings.get(i).length() * Bitmap.fontWidth()), (i * (fontHeight() + 1)) + 2, 0xF0F070);
                }
            } else {
                String overText;
                if (Client.playerLives > 0) {
                    overText = "YOU SUFFERED";
                } else {
                    overText = "GAME OVER";
                }
                draw(overText, halfWidth() - (overText.length() * fontWidth() / 2), render.halfHeight() - (fontHeight() / 2), 0xFF0000);
            }

            // render hudbar
            if (p.getRightHandItem() != null) {
                hudBar.update(p.getRightHandItem().name);
            } else {
                hudBar.update("");
            }
            draw(hudBar, 0, height - hudBar.height);

            int barColour = 0xF00000;
            int bgColour = 0x808080;
            if (p.hurtTicks != 0) {
                barColour = 0xFF6A00;
            }
            if (p.useTicks != 0) {
                bgColour = 0x505050;
            }
            healthBar.update((double) p.health / (double) p.maxHealth, barColour, bgColour);
            draw(healthBar, 2, (height - hudBar.height) + 3);

            // Hotkey items
            for (int i = 1; i < p.getHotkeys().length + 1; i++) {
                HotkeyOverlay hkIcon = new HotkeyOverlay(Integer.toString(i));
                if (p.getHotkey(i) != null) {
                    hkIcon.update(p.inventory.getItem(p.getHotkey(i)));
                } else {
                    hkIcon.update(null);
                }
                draw(hkIcon, width - ((hkIcon.width + 1) * (p.getHotkeys().length + 1)) + ((hkIcon.width + 1) * i), height - hkIcon.height);
            }
        }

        if (Client.getActiveOverlay() != null) {
            Client.getActiveOverlay().update();
            int cornerX = halfWidth() - Client.getActiveOverlay().halfWidth();
            int cornerY = halfHeight() - Client.getActiveOverlay().halfHeight();
            draw(Client.getActiveOverlay(), cornerX, cornerY, Client.getActiveOverlay().opacity);
        }
    }
}

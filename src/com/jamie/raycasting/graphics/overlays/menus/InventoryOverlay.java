package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class InventoryOverlay extends Overlay
{
    private Mob mob;
    private int itemIndex = 0;

    public InventoryOverlay(Mob mob) {
        this.mob = mob;
        if (mob.getRightHandItem() != null) {
            itemIndex = mob.getRightHandItemIndex();
        } else {
            itemIndex = 0;
        }
    }

    public void tick(Mob mob) {
        if (mob.input.inventory || mob.input.pause) {
            mob.input.setKeyGroupState("inventory", false);
            mob.input.setKeyGroupState("pause", false);
            mob.setActiveOverlay(null);
        }

        if (mob.input.forward) {
            mob.input.setKeyGroupState("forward", false);
            if ((itemIndex > 0)) {
                itemIndex--;
            }
        }
        if (mob.input.back) {
            mob.input.setKeyGroupState("back", false);

            if ((itemIndex < mob.getItems().size() - 1)) {
                itemIndex++;
            }
        }

        if (mob.input.action) {
            mob.input.setKeyGroupState("action", false);
            if (mob.getRightHandItem() != mob.getItem(itemIndex)) {
                // Tidy this if.
                mob.setRightHandItemIndex(itemIndex);
            } else {
                mob.setRightHandItemIndex(-1);
            }
        }
    }

    public void render(Screen screen) {
        int pad = 2;

        int x0 = screen.width / 8;
        int y0 = screen.height / 4;
        int x1 = screen.width - x0;
        int y1 = screen.height - y0;

        screen.fill(x0, y0, x1, y1, 0x202020);

        screen.fill(x0 + pad, (y0 + pad) + (itemIndex * 12), x1 - pad, (y0 + pad) + ((itemIndex + 1) * 12), 0x404040);

        for (int i = 0; i < mob.getItems().size(); i++) {
            String string;
            int colour;

            if (mob.getRightHandItemIndex() == i && !mob.rightHandEmpty) {
                string = "-> " + mob.getItems().get(i).name;
                colour = 0xF0F070;
                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                screen.draw(string, x0 + pad, (y0 + pad) + (i * 12) + 2, colour);
            } else {
                string = "  " + mob.getItems().get(i).name;
                colour = 0x707070;
                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                screen.draw(string, x0 + pad, (y0 + pad) + (i * 12) + 2, colour);
            }
        }
    }
}

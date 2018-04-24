package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.items.consumables.Consumable;

public class InventoryOverlay extends Overlay
{
    private Game game;
    private int itemIndex = 0;

    public InventoryOverlay(Game game) {
        this.game = game;
        if (game.player.getRightHandItem() != null) {
            itemIndex = game.player.getRightHandItemIndex();
        } else {
            itemIndex = 0;
        }
    }

    public void tick(Game game) {

        if (game.userInput.forward) {
            game.userInput.setKeyGroupState("forward", false);
            if ((itemIndex > 0)) {
                itemIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setKeyGroupState("back", false);

            if ((itemIndex < game.player.getItems().size() - 1)) {
                itemIndex++;
            }
        }

        if (game.userInput.action) {
            game.userInput.setKeyGroupState("action", false);
            if (!(game.player.getItems().get(itemIndex) instanceof Consumable)) { // TODO: fix empty selection crash
                if (game.player.getRightHandItem() != game.player.getItem(itemIndex)) {
                    game.player.setRightHandItemIndex(itemIndex);
                } else {
                    game.player.unequipRightHand();
                }
            } else {
                game.player.getItems().get(itemIndex).use();
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

        for (int i = 0; i < game.player.getItems().size(); i++) {
            String string;
            int colour;

            if (game.player.getRightHandItemIndex() == i && !game.player.rightHandEmpty) {
                string = "-> " + game.player.getItems().get(i).name;
                colour = 0xF0F070;
                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                screen.draw(string, x0 + pad, (y0 + pad) + (i * 12) + 2, colour);
            } else {
                string = "  " + game.player.getItems().get(i).name;
                colour = 0x707070;
                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                screen.draw(string, x0 + pad, (y0 + pad) + (i * 12) + 2, colour);
            }
        }
    }
}

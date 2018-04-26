package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.items.consumables.Consumable;

public class InventoryOverlay extends Overlay
{
    private Game game;
    private int itemIndex = 0;

    public InventoryOverlay(int width, int height, Game game) {
        super(width, height);
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

    public void update() {
        fill(0, 0, width, height, 0x202020);

        draw("  Items", borderPadding, borderPadding, 0xF0F0F0);

        fill(borderPadding, borderPadding + 10 + (itemIndex * 12), width - borderPadding, borderPadding + 10 + ((itemIndex + 1) * 12), 0x404040);

        for (int i = 0; i < game.player.getItems().size(); i++) {
            String string;
            int colour;

            if (game.player.getRightHandItemIndex() == i && !game.player.rightHandEmpty) {
                string = "-> " + game.player.getItems().get(i).name;
                colour = 0xF0F070;
                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                draw(string, borderPadding, borderPadding + 10 + (i * 12) + 2, colour);
            } else {
                string = " " + game.player.getItems().get(i).name;
                colour = 0x707070;
                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                draw(string, borderPadding, borderPadding + 10 + (i * 12) + 2, colour);
            }
        }
    }
}

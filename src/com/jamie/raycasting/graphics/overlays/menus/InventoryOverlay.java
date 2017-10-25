package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.Overlay;

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
        if (game.userInput.inventory || game.userInput.pause) {
            game.userInput.setKeyGroupState("inventory", false);
            game.userInput.setKeyGroupState("pause", false);
            game.setActiveOverlay(null);
        }

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
            if (game.player.getRightHandItem() != game.player.getItem(itemIndex)) {
                // Tidy this if.
                game.player.setRightHandItemIndex(itemIndex);
            } else {
                game.player.setRightHandItemIndex(-1);
            }
        }
    }

    public void render(Screen screen) {
        for (int i = 0; i < (screen.width * screen.height); i++) {
            screen.pixels[i] = screen.screenCapture.pixels[i];
        } // TODO: Change to use a render.fill for background behind text.

        for (int i = 0; i < game.player.getItems().size(); i++) {

//            screen.fill(8, 8 + (i * 10), 8, 8 + (i * 10), 0x80FF80);
            // Fix this method.

            String string;
            int col;

            if (game.player.getRightHandItemIndex() == i && !game.player.rightHandEmpty) {
                string = "-> " + game.player.getItems().get(i).name;
                col = 0xF0F070;
                if (itemIndex == i) {
                    col = 0xF0F0F0;
                }
                screen.draw(string, 2, 8 + (i * 10), col);
            } else {
                string = game.player.getItems().get(i).name;
                col = 0x707070;
                if (itemIndex == i) {
                    col = 0xF0F0F0;
                }
                screen.draw(string, 8, 8 + (i * 10), col);
            }
        }
    }
}

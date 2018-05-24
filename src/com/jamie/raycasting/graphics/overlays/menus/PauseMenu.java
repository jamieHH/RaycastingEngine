package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;

public class PauseMenu extends Menu
{
    public PauseMenu(int width, int height) {
        super(width, height);
        options.add("Resume");
        options.add("Main Menu");
    }

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.forward) {
            game.userInput.setKeyGroupState("forward", false);
            if ((optionIndex > 0)) {
                optionIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setKeyGroupState("back", false);

            if ((optionIndex < options.size() - 1)) {
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.userInput.setKeyGroupState("action", false);
            if (options.get(optionIndex).equals("Resume")) {
                game.setActiveOverlay(null);
            } else if (options.get(optionIndex).equals("Main Menu")) {
                game.stopGame();
            }
        }
    }

    public void update() {
        fill(0, 0, width, height, 0x202020);

        draw("  Paused", borderPadding, borderPadding, 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                draw("-> " + options.get(i), borderPadding, borderPadding + 10 + (i * 10), 0xD0D0D0);
            } else {
                draw(" " + options.get(i), borderPadding, borderPadding + 10 + (i * 10), 0x707070);
            }
        }
    }
}

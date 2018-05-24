package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;

public class MainMenu extends Menu
{
    public MainMenu(int width, int height) {
        super(width, height);
        options.add("New Game");
        options.add("Load Game");
        options.add("Options");
        options.add("Quit");
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
            if (options.get(optionIndex).equals("New Game")) {
                game.newGame();
            } else if (options.get(optionIndex).equals("Load Game")) {
                game.setActiveOverlay(game.loadMenu);
            } else if (options.get(optionIndex).equals("Options")) {
                game.setActiveOverlay(game.optionsMenu);
            } else if (options.get(optionIndex).equals("Quit")) {
                System.exit(0);
            }
        }
    }

    public void update() {
        fill(0, 0, width, height, 0x202020);

        draw("  Dungeon Raycaster", borderPadding, borderPadding, 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                draw("-> " + options.get(i), borderPadding, borderPadding + 10 + (i * 10), 0xD0D0D0);
            } else {
                draw(" " + options.get(i), borderPadding, borderPadding + 10 + (i * 10), 0x707070);
            }
        }
    }
}

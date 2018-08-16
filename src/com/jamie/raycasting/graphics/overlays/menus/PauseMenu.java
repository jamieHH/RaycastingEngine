package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.app.Sound;

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
            game.userInput.setInputState("forward", false);
            if ((optionIndex > 0)) {
                Sound.clickUp.play();
                optionIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setInputState("back", false);
            if ((optionIndex < options.size() - 1)) {
                Sound.clickDown.play();
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.userInput.setInputState("action", false);
            Sound.clickAction.play();
            if (options.get(optionIndex).equals("Resume")) {
                game.setActiveOverlay(null);
            } else if (options.get(optionIndex).equals("Main Menu")) {
                game.stopGame();
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Paused", bp, bp, 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                draw("-> " + options.get(i), bp, bp + 10 + (i * 10), 0xD0D0D0);
            } else {
                draw(" " + options.get(i), bp, bp + 10 + (i * 10), 0x707070);
            }
        }
    }
}

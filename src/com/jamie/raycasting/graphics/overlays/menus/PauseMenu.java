package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;

import java.util.Objects;

public class PauseMenu extends Menu
{
    public PauseMenu() {
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
            if (options.get(optionIndex) == "Resume") {
                game.setActiveOverlay(null);
            } else if (options.get(optionIndex) == "Main Menu") {
                game.stopGame();
            }
        }
    }

    public void render(Screen screen) {
        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Paused", textMarginX + 6, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options.get(i), selectedTextMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
            } else {
                screen.draw(options.get(i), textMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
            }
        }
    }
}

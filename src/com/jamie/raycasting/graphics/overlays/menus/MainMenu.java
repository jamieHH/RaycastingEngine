package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;

import java.util.Objects;

public class MainMenu extends Menu
{
    public MainMenu() {
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
            if (Objects.equals(options.get(optionIndex), "New Game")) {
                game.newGame();
            } else if (Objects.equals(options.get(optionIndex), "Load Game")) {
                game.setActiveOverlay(game.loadMenu);
            } else if (Objects.equals(options.get(optionIndex), "Options")) {
                game.setActiveOverlay(game.optionsMenu);
            } else if (Objects.equals(options.get(optionIndex), "Quit")) {
                System.exit(0);
            }
        }
    }

    public void render(Screen screen) {
        super.render(screen);

        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Dungeon Raycaster", textMarginX + 6, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options.get(i), selectedTextMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
            } else {
                screen.draw(options.get(i), textMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
            }
        }
    }
}

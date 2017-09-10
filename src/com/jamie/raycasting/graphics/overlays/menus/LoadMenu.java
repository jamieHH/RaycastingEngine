package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;

public class LoadMenu extends Menu {

    protected String[] saves = {
            "Empty",
            "Empty",
            "Empty",
    };

    public LoadMenu() {
        options.clear();
        options.add("Save Slot 1");
        options.add("Save Slot 2");
        options.add("Save Slot 3");
        options.add("Main Menu");
    }

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.forward) {
            game.pauseTime = 10;
            if ((optionIndex > 0)) {
                optionIndex--;
            }
        }
        if (game.userInput.back) {
            game.pauseTime = 10;
            if ((optionIndex < options.size() - 1)) {
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.pauseTime = 10;
            if (options.get(optionIndex) == "Main Menu") {
                game.setActiveMenu(game.mainMenu);
            }
        }
    }

    public void render(Screen screen) {
        super.render(screen);

        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Load Game", textMarginX + 6, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options.get(i), selectedTextMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);

                if (options.get(optionIndex) == "Save Slot 1") {
                    String string = "< " + saves[optionIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (options.get(optionIndex) == "Save Slot 2") {
                    String string = "< " + saves[optionIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (options.get(optionIndex) == "Save Slot 3") {
                    String string = "< " + saves[optionIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                }
            } else {
                screen.draw(options.get(i), textMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);

                if (options.get(i) == "Save Slot 1") {
                    String string = "< " + saves[i] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (options.get(i) == "Save Slot 2") {
                    String string = "< " + saves[i] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (options.get(i) == "Save Slot 3") {
                    String string = "< " + saves[i] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                }
            }
        }
    }
}

package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class LoadMenu extends Menu {

    protected String[] options = {
            "Save Slot 1",
            "Save Slot 2",
            "Save Slot 3",
            "Main Menu"
    };

    protected String[] saves = {
            "Empty",
            "Empty",
            "Empty",
    };

    protected int optionIndex = 0;

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
            if ((optionIndex < options.length - 1)) {
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.pauseTime = 10;
            if (options[optionIndex] == "Main Menu") {
                game.menu = new MainMenu();
            }
        }
    }

    public void render(Screen screen) {
        super.render(screen);

        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Load Game", textMarginX + 6, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.length; i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options[i], selectedTextMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);

                if (options[optionIndex] == "Save Slot 1") {
                    String string = "< " + saves[optionIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (options[optionIndex] == "Save Slot 2") {
                    String string = "< " + saves[optionIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (options[optionIndex] == "Save Slot 3") {
                    String string = "< " + saves[optionIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                }
            } else {
                screen.draw(options[i], textMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);

                if (options[i] == "Save Slot 1") {
                    String string = "< " + saves[i] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (options[i] == "Save Slot 2") {
                    String string = "< " + saves[i] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (options[i] == "Save Slot 3") {
                    String string = "< " + saves[i] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                }
            }
        }
    }
}

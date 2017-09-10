package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class OverMenu extends Menu {

    private  String[] options = {
            "Load Game",
            "Main Menu",
            "Quit Game"
    };

    private int optionIndex = 0;

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.pause && pauseTime == 0) {
            game.pauseTime = 10;
            game.menu = null;
        }

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
            } else if (options[optionIndex] == "Quit Game") {
                System.exit(0);
            }
        }
    }

    public void render(Screen screen) {
//        super.render(screen);

        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Game Over", textMarginX + 6, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.length; i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options[i], selectedTextMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
            } else {
                screen.draw(options[i], textMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
            }
        }
    }
}

package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class MainMenu extends Overlay {

    private String[] options = {
            "New Game",
            "Load Game",
            "Options",
            "Quit"
    };

    private int optionIndex = 0;

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.forward) {
            game.userInput.forward = false;
            game.pauseTime = 10;
            if ((optionIndex > 0)) {
                optionIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.back = false;
            game.pauseTime = 10;
            if ((optionIndex < options.length - 1)) {
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.pauseTime = 10;
            if (options[optionIndex] == "New Game") {
                game.newGame();
            } else if (options[optionIndex] == "Load Game") {
                game.menu = new LoadMenu();
            } else if (options[optionIndex] == "Options") {
                game.menu = new OptionsMenu();
            } else if (options[optionIndex] == "Quit") {
                System.exit(0);
            }
        }
    }

    public void render(Screen screen) {
        super.render(screen);

        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Dungeon Raycaster", 26, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.length; i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options[i], 20, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
            } else {
                screen.draw(options[i], 32, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
            }
        }
    }
}

package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.input.Controls;

public class MainMenu extends Menu
{
    public String[] getOptions() {
        return new String[] {
                "New Game",
                "Load Game",
                "Options",
                "Quit",
        };
    }


    public MainMenu(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {
        super.tick(game);

        if (game.input.check(Controls.ACTION) || game.input.check(Controls.ENTER)) {
            game.input.stopInput(Controls.ACTION);
            game.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("New Game")) {
                game.newGame();
            } else if (getOption(optionIndex).equals("Load Game")) {
                game.setActiveOverlay(game.loadMenu);
            } else if (getOption(optionIndex).equals("Options")) {
                game.setActiveOverlay(game.optionsMenu);
            } else if (getOption(optionIndex).equals("Quit")) {
                System.exit(0);
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Dungeon Raycaster", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + 10 + (i * 10), 0xD0D0D0);
            } else {
                draw(" " + getOption(i), bp, bp + 10 + (i * 10), 0x707070);
            }
        }
    }
}

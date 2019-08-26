package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.input.Controls;

public class PauseMenu extends Menu
{
    public String[] getOptions() {
        return new String[] {
                "Resume",
                "Main Menu"
        };
    }

    public PauseMenu(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.forward || game.userInput.up) {
            game.userInput.stopInput(Controls.FORWARD);
            game.userInput.stopInput(Controls.UP);
            if ((optionIndex > 0)) {
                Sound.clickUp.play();
                optionIndex--;
            }
        }
        if (game.userInput.back || game.userInput.down) {
            game.userInput.stopInput(Controls.BACK);
            game.userInput.stopInput(Controls.DOWN);
            if ((optionIndex < getOptions().length - 1)) {
                Sound.clickDown.play();
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.userInput.stopInput(Controls.ACTION);
            game.userInput.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("Resume")) {
                game.setActiveOverlay(null);
            } else if (getOption(optionIndex).equals("Main Menu")) {
                game.stopGame();
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Paused", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + 10 + (i * 10), 0xD0D0D0);
            } else {
                draw(" " + getOption(i), bp, bp + 10 + (i * 10), 0x707070);
            }
        }
    }
}

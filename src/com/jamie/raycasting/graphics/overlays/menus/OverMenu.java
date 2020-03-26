package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.App;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.input.Controls;

public class OverMenu extends Menu
{
    public String[] getOptions() {
        return new String[] {
                "Main Menu",
                "Quit Game",
        };
    }


    public OverMenu() {
        super(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    }

    public void tick() {
        super.tick();

        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("Main Menu")) {
                Client.stopGame();
            } else if (getOption(optionIndex).equals("Quit Game")) {
                System.exit(0);
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Game Over", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0xD0D0D0);
            } else {
                draw(" " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0x707070);
            }
        }
    }
}

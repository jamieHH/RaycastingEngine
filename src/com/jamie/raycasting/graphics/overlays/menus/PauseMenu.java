package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.input.Controls;

public class PauseMenu extends Menu
{
    public String[] getOptions() {
        return new String[] {
                "Resume",
                "Load Game",
                "Options",
                "Main Menu"
        };
    }


    public PauseMenu(int width, int height) {
        super(width, height);
    }

    public void tick() {
        super.tick();

        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("Resume")) {
                Client.setActiveOverlay(null);
            } else if (getOption(optionIndex).equals("Load Game")) {
                Client.setActiveOverlay(Client.loadMenu);
            } else if (getOption(optionIndex).equals("Options")) {
                Client.setActiveOverlay(Client.optionsMenu);
            } else if (getOption(optionIndex).equals("Main Menu")) {
                Client.stopGame();
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Paused", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0xD0D0D0);
            } else {
                draw(" " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0x707070);
            }
        }
    }
}

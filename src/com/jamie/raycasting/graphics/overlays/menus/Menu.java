package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.input.Controls;

public abstract class Menu extends Overlay
{
    public int optionIndex = 0;
    public abstract String[] getOptions();
    public String getOption(int i) {
        return getOptions()[i];
    }


    public Menu(int width, int height) {
        super(width, height);
    }

    public void tick() {
        super.tick();

        if (Client.input.check(Controls.FORWARD) || Client.input.check(Controls.UP)) {
            Client.input.stopInput(Controls.FORWARD);
            Client.input.stopInput(Controls.UP);
            if ((optionIndex > 0)) {
                optionIndex--;
                Sound.clickUp.play();
            }
        }
        if (Client.input.check(Controls.BACK)|| Client.input.check(Controls.DOWN)) {
            Client.input.stopInput(Controls.BACK);
            Client.input.stopInput(Controls.DOWN);
            if ((optionIndex < getOptions().length - 1)) {
                optionIndex++;
                Sound.clickDown.play();
            }
        }
    }

    public void resetSelection() {
        optionIndex = 0;
    }
}

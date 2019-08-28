package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
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

    public void tick(Game game) {
        super.tick(game);

        if (game.input.check(Controls.FORWARD) || game.input.check(Controls.UP)) {
            game.input.stopInput(Controls.FORWARD);
            game.input.stopInput(Controls.UP);
            if ((optionIndex > 0)) {
                optionIndex--;
                Sound.clickUp.play();
            }
        }
        if (game.input.check(Controls.BACK)|| game.input.check(Controls.DOWN)) {
            game.input.stopInput(Controls.BACK);
            game.input.stopInput(Controls.DOWN);
            if ((optionIndex < getOptions().length - 1)) {
                optionIndex++;
                Sound.clickDown.play();
            }
        }
    }
}

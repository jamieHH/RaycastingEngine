package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.input.KeyControls;

public class LoadMenu extends Menu
{
    private final String[] saves = {
            "Empty",
            "Empty",
            "Empty",
    };

    public String[] getOptions() {
        return new String[] {
                "Save Slot 1",
                "Save Slot 2",
                "Save Slot 3",
                "Main Menu",
        };
    }

    public LoadMenu(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.forward) {
            game.userInput.stopInput(KeyControls.FORWARD);
            if ((optionIndex > 0)) {
                Sound.clickUp.play();
                optionIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.stopInput(KeyControls.BACK);
            if ((optionIndex < getOptions().length - 1)) {
                Sound.clickDown.play();
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.userInput.stopInput(KeyControls.ACTION);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("Main Menu")) {
                game.setActiveOverlay(game.mainMenu);
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Load Game", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + 10 + (i * 10), 0xD0D0D0);

                if (getOption(optionIndex).equals("Save Slot 1")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Save Slot 2")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Save Slot 3")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                }
            } else {
                draw(" " + getOption(i), bp, bp + 10 + (i * 10), 0x707070);

                if (getOption(i).equals("Save Slot 1")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (getOption(i).equals("Save Slot 2")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (getOption(i).equals("Save Slot 3")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                }
            }
        }
    }
}

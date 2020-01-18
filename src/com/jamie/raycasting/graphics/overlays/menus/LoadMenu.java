package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.input.Controls;

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
                "Back",
        };
    }


    public LoadMenu(int width, int height) {
        super(width, height);
    }

    public void tick() {
        super.tick();

        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("Back")) {
                Client.setActiveOverlay(Client.getPreviousOverlay());
            }
        }
    }

    public void update() {
        fill(0x202020);

        int lineHeight = lineHeight();
        draw("  Load Game", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + lineHeight + (i * lineHeight), 0xD0D0D0);

                if (getOption(optionIndex).equals("Save Slot 1")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + lineHeight + (i * lineHeight), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Save Slot 2")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + lineHeight + (i * lineHeight), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Save Slot 3")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + lineHeight + (i * lineHeight), 0xD0D0D0);
                }
            } else {
                draw(" " + getOption(i), bp, bp + lineHeight + (i * lineHeight), 0x707070);

                if (getOption(i).equals("Save Slot 1")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + lineHeight + (i * lineHeight), 0x707070);
                } else if (getOption(i).equals("Save Slot 2")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + lineHeight + (i * lineHeight), 0x707070);
                } else if (getOption(i).equals("Save Slot 3")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + lineHeight + (i * lineHeight), 0x707070);
                }
            }
        }
    }
}

package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;

public class LoadMenu extends Menu
{
    private final String[] saves = {
            "Empty",
            "Empty",
            "Empty",
    };

    public LoadMenu(int width, int height) {
        super(width, height);
        options.add("Save Slot 1");
        options.add("Save Slot 2");
        options.add("Save Slot 3");
        options.add("Main Menu");
    }

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.forward) {
            game.userInput.setKeyGroupState("forward", false);
            if ((optionIndex > 0)) {
                optionIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setKeyGroupState("back", false);
            if ((optionIndex < options.size() - 1)) {
                optionIndex++;
            }
        }

        if (game.userInput.action) {
            game.userInput.setKeyGroupState("action", false);
            if (options.get(optionIndex).equals("Main Menu")) {
                game.setActiveOverlay(game.mainMenu);
            }
        }
    }

    public void update() {
        fill(0, 0, width, height, 0x202020);

        draw("  Load Game", bp, bp, 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                draw("-> " + options.get(i), bp, bp + 10 + (i * 10), 0xD0D0D0);

                if (options.get(optionIndex).equals("Save Slot 1")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (options.get(optionIndex).equals("Save Slot 2")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (options.get(optionIndex).equals("Save Slot 3")) {
                    String string = "< " + saves[optionIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                }
            } else {
                draw(" " + options.get(i), bp, bp + 10 + (i * 10), 0x707070);

                if (options.get(i).equals("Save Slot 1")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (options.get(i).equals("Save Slot 2")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (options.get(i).equals("Save Slot 3")) {
                    String string = "< " + saves[i] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                }
            }
        }
    }
}

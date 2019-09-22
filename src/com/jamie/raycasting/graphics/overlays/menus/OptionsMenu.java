package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.App;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.input.Controls;

public class OptionsMenu extends Menu
{
    // resolution options
    private final String[] scales = {
            "1",
            "2",
            "4",
            "8",
    };

    private final String[][] resolutions = {
            {"200", "150", "(4:3)"},
            {"400", "300", "(4:3)"},
            {"800", "600", "(4:3)"},
            {"256", "144", "(16:9)"},
            {"512", "288", "(16:9)"},
            {"1024", "576", "(16:9)"},
    };

    private int resolutionIndex = 1;
    private int scaleIndex = 3;

    private boolean fullscreenEnabled = App.fullscreen;
    private int resolutionWidth = App.getDisplayWidth();
    private int resolutionHeight = App.getDisplayHeight();
    private int scale = App.scale;
    private boolean soundEnabled = App.soundEnabled;

    public String[] getOptions() {
        return new String[] {
                "Fullscreen",
                "Resolution",
                "Scaling",
                "Sound",
                "Reset Defaults",
                "Accept",
                "Main Menu",
        };
    }


    public OptionsMenu(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {
        super.tick(game);

        if (game.input.check(Controls.LEFT) || game.input.check(Controls.ROTLEFT)) {
            game.input.stopInput(Controls.LEFT);
            game.input.stopInput(Controls.ROTLEFT);
            if (getOption(optionIndex).equals("Fullscreen")) {
                fullscreenEnabled = !fullscreenEnabled;
            } else if (getOption(optionIndex).equals("Resolution")) {
                if (resolutionIndex > 0) {
                    resolutionIndex--;
                    resolutionWidth = Integer.parseInt(resolutions[resolutionIndex][0]);
                    resolutionHeight = Integer.parseInt(resolutions[resolutionIndex][1]);
                }
            } else if (getOption(optionIndex).equals("Scaling")) {
                if (scaleIndex > 0) {
                    scaleIndex--;
                    scale = Integer.parseInt(scales[scaleIndex]);
                }
            }else if (getOption(optionIndex).equals("Sound")) {
                soundEnabled = !soundEnabled;
            }
        }

        if (game.input.check(Controls.RIGHT) || game.input.check(Controls.ROTRIGHT)) {
            game.input.stopInput(Controls.RIGHT);
            game.input.stopInput(Controls.ROTRIGHT);
            if (getOption(optionIndex).equals("Fullscreen")) {
                fullscreenEnabled = !fullscreenEnabled;
            } else if (getOption(optionIndex).equals("Resolution")) {
                if (resolutionIndex < resolutions.length - 1) {
                    resolutionIndex++;
                    resolutionWidth = Integer.parseInt(resolutions[resolutionIndex][0]);
                    resolutionHeight = Integer.parseInt(resolutions[resolutionIndex][1]);
                }
            } else if (getOption(optionIndex).equals("Scaling")) {
                if (scaleIndex < scales.length - 1) {
                    scaleIndex++;
                    scale = Integer.parseInt(scales[scaleIndex]);
                }
            } else if (getOption(optionIndex).equals("Sound")) {
                soundEnabled = !soundEnabled;
            }
        }

        if (game.input.check(Controls.ACTION) || game.input.check(Controls.ENTER)) {
            game.input.stopInput(Controls.ACTION);
            game.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("Reset Defaults")) {
                resolutionIndex = 1;
                scaleIndex = 2;
                soundEnabled = true;
            } else if (getOption(optionIndex).equals("Accept")) {
                App.fullscreen = fullscreenEnabled;
                App.newWidth = resolutionWidth;
                App.newHeight = resolutionHeight;
                App.newScale = scale;

                App.display.setSize(resolutionWidth, resolutionHeight);
                game.mainMenu.setSize(resolutionWidth, (int) (resolutionHeight * 0.6));
                game.loadMenu.setSize(resolutionWidth, (int) (resolutionHeight * 0.6));
                game.optionsMenu.setSize(resolutionWidth, (int) (resolutionHeight * 0.6));
                game.pauseMenu.setSize(resolutionWidth, (int) (resolutionHeight * 0.6));
                game.overMenu.setSize(resolutionWidth, (int) (resolutionHeight * 0.6));
                game.inventoryOverlay.setSize((int) (resolutionWidth * 0.8), (int) (resolutionHeight * 0.6));

                App.soundEnabled = soundEnabled;
                App.setNewOptions = true;
                game.setActiveOverlay(game.mainMenu);
            } else if (getOption(optionIndex).equals("Main Menu")) {
                game.setActiveOverlay(game.mainMenu);
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Options", bp, bp, 0xF0F0F0);

        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + 10 + (i * 10), 0xD0D0D0);

                if (getOption(optionIndex).equals("Fullscreen")) {
                    String string = "< " + ((fullscreenEnabled) ? "On" : "Off") + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Resolution")) {
                    String string = "< " + resolutionWidth + ", " + resolutionHeight + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Scaling")) {
                    String string = "< " + scales[scaleIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Sound")) {
                    String string = "< " + ((soundEnabled) ? "On" : "Off") + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                }
            } else {
                draw(" " + getOption(i), bp, bp + 10 + (i * 10), 0x707070);

                if (getOption(i).equals("Fullscreen")) {
                    String string = "< " + ((fullscreenEnabled) ? "On" : "Off") + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (getOption(i).equals("Resolution")) {
                    String string = resolutionWidth + ", " + resolutionHeight;
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (getOption(i).equals("Scaling")) {
                    String string = scales[scaleIndex] + "";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (getOption(i).equals("Sound")) {
                    String string = ((soundEnabled) ? "On" : "Off");
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                }
            }
        }
    }
}

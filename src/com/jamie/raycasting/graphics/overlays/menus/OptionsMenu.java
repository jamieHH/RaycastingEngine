package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.App;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.input.Controls;

public class OptionsMenu extends Menu
{
    // resolution options
    private final String[] aspectRatios = {
            "4:3",
            "16:9"
    };

    private final int[] scales = {
            1,
            2,
            4,
            8,
    };

    private final int[][] resolutions4x3 = {
            {100, 75},
            {200, 150},
            {400, 300},
            {800, 600},
    };

    private final int[][] resolutions16x9 = {
            {128, 72},
            {256, 144},
            {512, 288},
            {1024, 576},
    };

    private int aspectRatioIndex = 0;
    private int resolutionIndex = 1;
    private int scaleIndex = 2;
    private boolean soundEnabled = App.soundEnabled;


    public String[] getOptions() {
        return new String[] {
                "Aspect Ratio",
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

        if (game.userInput.left || game.userInput.rotLeft) {
            game.userInput.stopInput(Controls.LEFT);
            game.userInput.stopInput(Controls.ROTLEFT);
            if (getOption(optionIndex).equals("Aspect Ratio")) {
                if ((aspectRatioIndex > 0)) {
                    aspectRatioIndex--;
                }
            } else if (getOption(optionIndex).equals("Resolution")) {
                if ((resolutionIndex > 0)) {
                    resolutionIndex--;
                }
            } else if (getOption(optionIndex).equals("Scaling")) {
                if ((scaleIndex > 0)) {
                    scaleIndex--;
                }
            }else if (getOption(optionIndex).equals("Sound")) {
                soundEnabled = !soundEnabled;
            }
        }

        if (game.userInput.right || game.userInput.rotRight) {
            game.userInput.stopInput(Controls.RIGHT);
            game.userInput.stopInput(Controls.ROTRIGHT);
            if (getOption(optionIndex).equals("Aspect Ratio")) {
                if ((aspectRatioIndex < aspectRatios.length - 1)) {
                    aspectRatioIndex++;
                }
            } else if (getOption(optionIndex).equals("Resolution")) {
                if ((resolutionIndex < 4 - 1)) {
                    resolutionIndex++;
                }
            } else if (getOption(optionIndex).equals("Scaling")) {
                if ((scaleIndex < scales.length - 1)) {
                    scaleIndex++;
                }
            } else if (getOption(optionIndex).equals("Sound")) {
                soundEnabled = !soundEnabled;
            }
        }

        if (game.userInput.action) {
            game.userInput.stopInput(Controls.ACTION);
            game.userInput.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (getOption(optionIndex).equals("Reset Defaults")) {
                aspectRatioIndex = 0;
                resolutionIndex = 1;
                scaleIndex = 2;
                soundEnabled = true;
            } else if (getOption(optionIndex).equals("Accept")) {
                App.newScale = scales[scaleIndex];
                if (aspectRatios[aspectRatioIndex].equals("16:9")) {
                    App.newWidth = resolutions16x9[resolutionIndex][0];
                    App.newHeight = resolutions16x9[resolutionIndex][1];
                    App.display.setSize(resolutions16x9[resolutionIndex][0], resolutions16x9[resolutionIndex][1]);

                    game.mainMenu.setSize(resolutions16x9[resolutionIndex][0], (int) (resolutions16x9[resolutionIndex][1] * 0.6));
                    game.loadMenu.setSize(resolutions16x9[resolutionIndex][0], (int) (resolutions16x9[resolutionIndex][1] * 0.6));
                    game.optionsMenu.setSize(resolutions16x9[resolutionIndex][0], (int) (resolutions16x9[resolutionIndex][1] * 0.6));
                    game.pauseMenu.setSize(resolutions16x9[resolutionIndex][0], (int) (resolutions16x9[resolutionIndex][1] * 0.6));
                    game.overMenu.setSize(resolutions16x9[resolutionIndex][0], (int) (resolutions16x9[resolutionIndex][1] * 0.6));
                    game.inventoryOverlay.setSize((int) (resolutions16x9[resolutionIndex][0] * 0.8), (int) (resolutions16x9[resolutionIndex][1] * 0.6));

                } else if (aspectRatios[aspectRatioIndex].equals("4:3")) {
                    App.newWidth = resolutions4x3[resolutionIndex][0];
                    App.newHeight = resolutions4x3[resolutionIndex][1];
                    App.display.setSize(resolutions4x3[resolutionIndex][0], resolutions4x3[resolutionIndex][1]);

                    game.mainMenu.setSize(resolutions4x3[resolutionIndex][0], (int) (resolutions4x3[resolutionIndex][1] * 0.6));
                    game.loadMenu.setSize(resolutions4x3[resolutionIndex][0], (int) (resolutions4x3[resolutionIndex][1] * 0.6));
                    game.optionsMenu.setSize(resolutions4x3[resolutionIndex][0], (int) (resolutions4x3[resolutionIndex][1] * 0.6));
                    game.pauseMenu.setSize(resolutions4x3[resolutionIndex][0], (int) (resolutions4x3[resolutionIndex][1] * 0.6));
                    game.overMenu.setSize(resolutions4x3[resolutionIndex][0], (int) (resolutions4x3[resolutionIndex][1] * 0.6));
                    game.inventoryOverlay.setSize((int) (resolutions4x3[resolutionIndex][0] * 0.8), (int) (resolutions4x3[resolutionIndex][1] * 0.6));

                }
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

                if (getOption(optionIndex).equals("Aspect Ratio")) {
                    String string = "< " + aspectRatios[aspectRatioIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("4:3")) {
                    String string = "< " + resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (getOption(optionIndex).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("16:9")) {
                    String string = "< " + resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1] + " >";
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

                if (getOption(i).equals("Aspect Ratio")) {
                    String string = aspectRatios[aspectRatioIndex];
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (getOption(i).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("4:3")) {
                    String string = resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1];
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (getOption(i).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("16:9")) {
                    String string = resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1];
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

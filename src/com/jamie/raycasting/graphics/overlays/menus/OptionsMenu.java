package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.App;
import com.jamie.raycasting.app.Game;

public class OptionsMenu extends Menu
{
    // resolution options
    private final String[] aspectRatios = {
            "4:3",
            "16:9"
    };

    private final int[] scales = {
            8,
            4,
            2,
            1
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
    private int scaleIndex = 1;

    public OptionsMenu(int width, int height) {
        super(width, height);
        options.add("Aspect Ratio");
        options.add("Resolution");
        options.add("Scaling");
        options.add("Reset Defaults");
        options.add("Accept");
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

        if (game.userInput.left || game.userInput.rotLeft) {
            game.userInput.setKeyGroupState("rotLeft", false);
            if (options.get(optionIndex).equals("Aspect Ratio")) {
                if ((aspectRatioIndex > 0)) {
                    aspectRatioIndex--;
                }
            } else if (options.get(optionIndex).equals("Resolution")) {
                if ((resolutionIndex > 0)) {
                    resolutionIndex--;
                }
            } else if (options.get(optionIndex).equals("Scaling")) {
                if ((scaleIndex > 0)) {
                    scaleIndex--;
                }
            }
        }

        if (game.userInput.right || game.userInput.rotRight) {
            game.userInput.setKeyGroupState("rotRight", false);
            if (options.get(optionIndex).equals("Aspect Ratio")) {
                if ((aspectRatioIndex < aspectRatios.length - 1)) {
                    aspectRatioIndex++;
                }
            } else if (options.get(optionIndex).equals("Resolution")) {
                if ((resolutionIndex < 4 - 1)) {
                    resolutionIndex++;
                }
            } else if (options.get(optionIndex).equals("Scaling")) {
                if ((scaleIndex < scales.length - 1)) {
                    scaleIndex++;
                }
            }
        }

        if (game.userInput.action) {
            game.userInput.setKeyGroupState("action", false);
            if (options.get(optionIndex).equals("Reset Defaults")) {
                aspectRatioIndex = 0;
                resolutionIndex = 1;
                scaleIndex = 1;
            } else if (options.get(optionIndex).equals("Accept")) {
                App.newScale = scales[scaleIndex];
                if (aspectRatios[aspectRatioIndex].equals("16:9")) {
                    App.newWidth = resolutions16x9[resolutionIndex][0];
                    App.newHeight = resolutions16x9[resolutionIndex][1];
                } else if (aspectRatios[aspectRatioIndex].equals("4:3")) {
                    App.newWidth = resolutions4x3[resolutionIndex][0];
                    App.newHeight = resolutions4x3[resolutionIndex][1];
                }
                App.setNewOptions = true;
                game.setActiveOverlay(game.mainMenu);
            }
        }
    }

    public void update() {
        fill(0, 0, width, height, 0x202020);

        draw("  Options", bp, bp, 0xF0F0F0);

        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                draw("-> " + options.get(i), bp, bp + 10 + (i * 10), 0xD0D0D0);

                if (options.get(optionIndex).equals("Aspect Ratio")) {
                    String string = "< " + aspectRatios[aspectRatioIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (options.get(optionIndex).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("4:3")) {
                    String string = "< " + resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (options.get(optionIndex).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("16:9")) {
                    String string = "< " + resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                } else if (options.get(optionIndex).equals("Scaling")) {
                    String string = "< " + scales[scaleIndex] + " >";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0xD0D0D0);
                }
            } else {
                draw(" " + options.get(i), bp, bp + 10 + (i * 10), 0x707070);

                if (options.get(i).equals("Aspect Ratio")) {
                    String string = aspectRatios[aspectRatioIndex];
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (options.get(i).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("4:3")) {
                    String string = resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1];
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (options.get(i).equals("Resolution") && aspectRatios[aspectRatioIndex].equals("16:9")) {
                    String string = resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1];
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                } else if (options.get(i).equals("Scaling")) {
                    String string = scales[scaleIndex] + "";
                    draw(string, width - ((string.length() * 6) + bp), bp + 10 + (i * 10), 0x707070);
                }
            }
        }
    }
}

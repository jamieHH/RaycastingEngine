package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class OptionsMenu extends Overlay {

    // resolution options
    private String[] aspectRatios = {
            "4:3",
            "16:9"
    };

    private int[] scales = {
            8,
            4,
            2,
            1
    };

    private int[][] resolutions4x3 = {
            {100, 75},
            {200, 150},
            {400, 300},
            {800, 600},
    };

    private int[][] resolutions16x9= {
            {128, 72},
            {256, 144},
            {512, 288},
            {1024, 576},
    };
    //

    private  String[] options = {
            "Aspect Ratio",
            "Resolution",
            "Scaling",
            "Reset Defaults",
            "Accept"
    };

    private int aspectRatioIndex = 0;
    private int resolutionIndex = 1;
    private int scaleIndex = 1;
    private int optionIndex = 0;

    public void tick(Game game) {
        super.tick(game);

        if (game.userInput.forward) {
            game.pauseTime = 10;
            if ((optionIndex > 0)) {
                optionIndex--;
            }
        }
        if (game.userInput.back) {
            game.pauseTime = 10;
            if ((optionIndex < options.length - 1)) {
                optionIndex++;
            }
        }

        if (game.userInput.left || game.userInput.rotLeft) {
            game.pauseTime = 10;
            if (options[optionIndex] == "Aspect Ratio") {
                if ((aspectRatioIndex > 0)) {
                    aspectRatioIndex--;
                }
            } else if (options[optionIndex] == "Resolution") {
                if ((resolutionIndex > 0)) {
                    resolutionIndex--;
                }
            } else if (options[optionIndex] == "Scaling") {
                if ((scaleIndex > 0)) {
                    scaleIndex--;
                }
            }
        }

        if (game.userInput.right || game.userInput.rotRight) {
            game.pauseTime = 10;
            if (options[optionIndex] == "Aspect Ratio") {
                if ((aspectRatioIndex < aspectRatios.length - 1)) {
                    aspectRatioIndex++;
                }
            } else if (options[optionIndex] == "Resolution") {
                if ((resolutionIndex < 4 - 1)) {
                    resolutionIndex++;
                }
            } else if (options[optionIndex] == "Scaling") {
                if ((scaleIndex < scales.length - 1)) {
                    scaleIndex++;
                }
            }
        }

        if (game.userInput.action) {
            game.pauseTime = 10;
            if (options[optionIndex] == "Reset Defaults") {
                aspectRatioIndex = 0;
                resolutionIndex = 1;
                scaleIndex = 1;
            } else if (options[optionIndex] == "Accept") {
                // Apply Changes
                game.menu = new MainMenu();
            }
        }
    }

    public void render(Screen screen) {
        super.render(screen);

        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Options", 26, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.length; i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options[i], 20, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);

                if (options[optionIndex] == "Aspect Ratio") {
                    String string = "< " + aspectRatios[aspectRatioIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (options[optionIndex] == "Resolution" && aspectRatios[aspectRatioIndex] == "4:3") {
                    String string = "< " + resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (options[optionIndex] == "Resolution" && aspectRatios[aspectRatioIndex] == "16:9") {
                    String string = "< " + resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (options[optionIndex] == "Scaling") {
                    String string = "< " + scales[scaleIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                }
            } else {
                screen.draw(options[i], 32, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);

                if (options[i] == "Aspect Ratio") {
                    String string = aspectRatios[aspectRatioIndex];
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (options[i] == "Resolution" && aspectRatios[aspectRatioIndex] == "4:3") {
                    String string = resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1];
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (options[i] == "Resolution" && aspectRatios[aspectRatioIndex] == "16:9") {
                    String string = resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1];
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (options[i] == "Scaling") {
                    String string = scales[scaleIndex] + "";
                    screen.draw(string, screen.width - ((string.length() * 6) + 20), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                }
            }
        }
    }
}

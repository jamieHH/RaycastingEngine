package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.App;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;

import java.util.Objects;

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

    public OptionsMenu() {
        options.clear();
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
            if (Objects.equals(options.get(optionIndex), "Aspect Ratio")) {
                if ((aspectRatioIndex > 0)) {
                    aspectRatioIndex--;
                }
            } else if (Objects.equals(options.get(optionIndex), "Resolution")) {
                if ((resolutionIndex > 0)) {
                    resolutionIndex--;
                }
            } else if (Objects.equals(options.get(optionIndex), "Scaling")) {
                if ((scaleIndex > 0)) {
                    scaleIndex--;
                }
            }
        }

        if (game.userInput.right || game.userInput.rotRight) {
            game.userInput.setKeyGroupState("rotRight", false);
            if (Objects.equals(options.get(optionIndex), "Aspect Ratio")) {
                if ((aspectRatioIndex < aspectRatios.length - 1)) {
                    aspectRatioIndex++;
                }
            } else if (Objects.equals(options.get(optionIndex), "Resolution")) {
                if ((resolutionIndex < 4 - 1)) {
                    resolutionIndex++;
                }
            } else if (Objects.equals(options.get(optionIndex), "Scaling")) {
                if ((scaleIndex < scales.length - 1)) {
                    scaleIndex++;
                }
            }
        }

        if (game.userInput.action) {
            game.userInput.setKeyGroupState("action", false);
            if (Objects.equals(options.get(optionIndex), "Reset Defaults")) {
                aspectRatioIndex = 0;
                resolutionIndex = 1;
                scaleIndex = 1;
            } else if (Objects.equals(options.get(optionIndex), "Accept")) {
                App.newScale = scales[scaleIndex];
                if (Objects.equals(aspectRatios[aspectRatioIndex], "16:9")) {
                    App.newWidth = resolutions16x9[resolutionIndex][0];
                    App.newHeight = resolutions16x9[resolutionIndex][1];
                } else if (Objects.equals(aspectRatios[aspectRatioIndex], "4:3")) {
                    App.newWidth = resolutions4x3[resolutionIndex][0];
                    App.newHeight = resolutions4x3[resolutionIndex][1];
                }
                App.setNewOptions = true;
                game.setActiveOverlay(game.mainMenu);
            }
        }
    }

    public void render(Screen screen) {
        super.render(screen);

        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Options", textMarginX + 6, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options.get(i), selectedTextMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);

                if (Objects.equals(options.get(optionIndex), "Aspect Ratio")) {
                    String string = "< " + aspectRatios[aspectRatioIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (Objects.equals(options.get(optionIndex), "Resolution") && Objects.equals(aspectRatios[aspectRatioIndex], "4:3")) {
                    String string = "< " + resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (Objects.equals(options.get(optionIndex), "Resolution") && Objects.equals(aspectRatios[aspectRatioIndex], "16:9")) {
                    String string = "< " + resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                } else if (Objects.equals(options.get(optionIndex), "Scaling")) {
                    String string = "< " + scales[scaleIndex] + " >";
                    screen.draw(string, screen.width - ((string.length() * 6) + selectedTextMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
                }
            } else {
                screen.draw(options.get(i), textMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);

                if (Objects.equals(options.get(i), "Aspect Ratio")) {
                    String string = aspectRatios[aspectRatioIndex];
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (Objects.equals(options.get(i), "Resolution") && Objects.equals(aspectRatios[aspectRatioIndex], "4:3")) {
                    String string = resolutions4x3[resolutionIndex][0] + ", " + resolutions4x3[resolutionIndex][1];
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (Objects.equals(options.get(i), "Resolution") && Objects.equals(aspectRatios[aspectRatioIndex], "16:9")) {
                    String string = resolutions16x9[resolutionIndex][0] + ", " + resolutions16x9[resolutionIndex][1];
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                } else if (Objects.equals(options.get(i), "Scaling")) {
                    String string = scales[scaleIndex] + "";
                    screen.draw(string, screen.width - ((string.length() * 6) + textMarginX), 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
                }
            }
        }
    }
}

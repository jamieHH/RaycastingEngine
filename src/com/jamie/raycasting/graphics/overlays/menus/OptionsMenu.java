package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.App;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.input.Controls;

public class OptionsMenu extends Menu
{
    private final String[][] resolutions = {
            {"200", "150", "(4:3)"},
            {"400", "300", "(4:3)"},
//            {"800", "600", "(4:3)"},
            {"256", "144", "(16:9)"},
            {"512", "288", "(16:9)"},
//            {"1024", "576", "(16:9)"},
    };

    private final String[] scales = {
            "1",
            "2",
            "4",
            "8",
    };

    private int resIndex = 2;
    private int scaleIndex = 3;

    private boolean fullscreenEnabled = App.getFullscreenEnabled();
    private int resWidth = App.getDisplayWidth();
    private int resHeight = App.getDisplayHeight();
    private int scale = App.getDisplayScale();
    private boolean soundEnabled = App.getSoundEnabled();

    public String[] getOptions() {
        return new String[] {
                "Fullscreen",
                "Resolution",
                "Scaling",
                "Sound",
                "Reset Defaults",
                "Accept",
                "Back",
        };
    }


    public OptionsMenu() {
        super(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    }

    public void tick() {
        super.tick();
        if (Client.input.check(Controls.LEFT) || Client.input.check(Controls.ROTLEFT)) {
            Client.input.stopInput(Controls.LEFT);
            Client.input.stopInput(Controls.ROTLEFT);
            switch (getOption(optionIndex)) {
                case "Fullscreen":
                    fullscreenEnabled = !fullscreenEnabled;
                    break;
                case "Resolution":
                    if (resIndex > 0) {
                        resIndex--;
                    }
                    break;
                case "Scaling":
                    if (scaleIndex > 0) {
                        scaleIndex--;
                    }
                    break;
                case "Sound":
                    soundEnabled = !soundEnabled;
                    break;
            }
        }
        if (Client.input.check(Controls.RIGHT) || Client.input.check(Controls.ROTRIGHT)) {
            Client.input.stopInput(Controls.RIGHT);
            Client.input.stopInput(Controls.ROTRIGHT);
            switch (getOption(optionIndex)) {
                case "Fullscreen":
                    fullscreenEnabled = !fullscreenEnabled;
                    break;
                case "Resolution":
                    if (resIndex < resolutions.length - 1) {
                        resIndex++;
                    }
                    break;
                case "Scaling":
                    if (scaleIndex < scales.length - 1) {
                        scaleIndex++;
                    }
                    break;
                case "Sound":
                    soundEnabled = !soundEnabled;
                    break;
            }
        }
        resWidth = Integer.parseInt(resolutions[resIndex][0]);
        resHeight = Integer.parseInt(resolutions[resIndex][1]);
        scale = Integer.parseInt(scales[scaleIndex]);

        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            switch (getOption(optionIndex)) {
                case "Reset Defaults":
                    resWidth = 256;
                    resHeight = 144;
                    fullscreenEnabled = true;
                    scale = 8;
                    soundEnabled = true;
                    break;
                case "Accept":
                    App.setDisplayResolution(resWidth, resHeight);
                    App.setDisplayScale(scale);
                    App.enableFullscreen(fullscreenEnabled);
                    App.enableSound(soundEnabled);

                    App.display.setSize(App.getDisplayWidth(), App.getDisplayHeight());
                    Client.resizeMenus();
                    break;
                case "Back":
                    Client.setActiveOverlay(Client.getPreviousOverlay());
                    break;
            }
        }
    }

    public void update() {
        fill(0x202020);
        draw("  Options", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            String string = "";
            switch (getOption(i)) {
                case "Fullscreen":
                    string = (fullscreenEnabled) ? "Enabled" : "Disabled";
                    break;
                case "Resolution":
                    string = resWidth + ", " + resHeight;
                    break;
                case "Scaling":
                    string = "x" + scale;
                    break;
                case "Sound":
                    string = (soundEnabled) ? "Enabled" : "Disabled";
                    break;
            }

            int col = 0x707070;
            String optionString = " " + getOption(i);
            if (optionIndex == i) {
                col = 0xD0D0D0;
                optionString = "-> " + getOption(i);
            }
            draw(optionString, bp, bp + lineHeight() + (i * lineHeight()), col);

            if (string.length() > 0) {
                if (optionIndex == i) {
                    string = "< " + string + " >";
                }
                draw(string, width - ((string.length() * fontWidth()) + bp), bp + lineHeight() + (i * lineHeight()), col);
            }
        }
    }
}

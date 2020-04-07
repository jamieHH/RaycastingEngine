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

    private boolean fullscreenEnabled = App.getFullscreenEnabled();
    private int resIndex = 0;
    private int scaleIndex = 3;
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

        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            switch (getOption(optionIndex)) {
                case "Reset Defaults":
                    fullscreenEnabled = true;
                    resIndex = 0;
                    scaleIndex = 3;
                    soundEnabled = true;
                    break;
                case "Accept":
                    App.enableFullscreen(fullscreenEnabled);
                    App.setDisplayResolution(getSetWidth(), getSetHeight());
                    App.setDisplayScale(getSetScale());
                    App.enableSound(soundEnabled);
                    optionIndex++;
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
                    string = getSetWidth() + ", " + getSetHeight();
                    break;
                case "Scaling":
                    string = "x" + getSetScale();
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

    private int getSetWidth() {
        return Integer.parseInt(resolutions[resIndex][0]);
    }

    private int getSetHeight() {
        return Integer.parseInt(resolutions[resIndex][1]);
    }

    private int getSetScale() {
        return Integer.parseInt(scales[scaleIndex]);
    }
}

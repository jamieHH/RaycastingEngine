package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.input.UserInputHandler;

public class RunApp
{
    public static final boolean defaultInDev = true;
    public static final int defaultResolutionWidth = 256;
    public static final int defaultResolutionHeight = 144;
    public static final int defaultDisplayScale = 8;
    public static final boolean defaultFullscreenEnabled = false;
    public static final boolean defaultSoundEnabled = true;
    public static final boolean defaultMouseEnabled = true;

    public static void main(String[] args) {
        App.inDev = defaultInDev;

        if (App.inDev) {
            App.setTitle("raycasting_engine_pre_0.98");
        } else {
            App.setTitle("Dungeon Crawler");
        }
        App.setDisplayResolution(defaultResolutionWidth, defaultResolutionHeight);
        App.setDisplayScale(defaultDisplayScale);
        App.enableFullscreen(defaultFullscreenEnabled);
        App.enableSound(defaultSoundEnabled);

        App.input = new UserInputHandler();
        App.input.enableMouse = defaultMouseEnabled;
        App.game = new Client(App.input);
        App.display = new Screen(App.getDisplayWidth(), App.getDisplayHeight());

        App app = new App();
        app.start();
    }
}

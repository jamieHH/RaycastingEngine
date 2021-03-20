package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.input.UserInputHandler;

public class RunApp
{
    public static final boolean defaultInDev = true;
    public static final int defaultResWidth = 200;
    public static final int defaultResHeight = 150;
    public static final int defaultDisplayScale = 4;
    public static final boolean defaultFullscreenEnabled = false;
    public static final boolean defaultSoundEnabled = true;

    public static void main(String[] args) {
        App.setIsInDev(defaultInDev);

        App.setTitle("raycasting_engine_pre_0.98");
        App.enableFullscreen(defaultFullscreenEnabled);
        App.setDisplayScale(defaultDisplayScale);
        App.enableSound(defaultSoundEnabled);

        UserInputHandler inputListener = new UserInputHandler();
        Client client = new Client(inputListener);
        Screen display = new Screen(defaultResWidth, defaultResHeight);

        App app = new App(client, inputListener, display);
        app.start();
    }
}

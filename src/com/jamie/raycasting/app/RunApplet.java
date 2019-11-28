package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.input.UserInputHandler;

import java.applet.Applet;
import java.awt.*;

public class RunApplet extends Applet
{
    public App app = new App();

    public static boolean defaultInDev = true;
    public static int defaultResolutionWidth = 256;
    public static int defaultResolutionHeight = 144;
    public static int defaultDisplayScale = 8;
    public static boolean defaultFullscreenEnabled = false;
    public static boolean defaultSoundEnabled = true;
    public static boolean defaultMouseEnabled = false;

    public void init() {
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

        setLayout(new BorderLayout());
        add(app);
    }

    public void start() {
        app.start();
    }

    public void stop() {
        app.stop();
    }
}

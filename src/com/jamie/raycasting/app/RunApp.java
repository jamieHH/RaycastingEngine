package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.input.UserInputHandler;

public class RunApp
{
    public static int defaultWidth = 200;
    public static int defaultHeight = 150;
    public static int defaultScale = 8;
    public static boolean defaultSoundEnabled = true;
    public static boolean defaultMouseEnabled = true;
    public static boolean defaultInDev = true;

    public static void main(String args[]) {
        App.inDev = defaultInDev;

        if (App.inDev) {
            App.title = "raycasting_engine_pre_0.98";
        } else {
            App.title = "Dungeon Crawler";
        }
        App.width = defaultWidth;
        App.height = defaultHeight;
        App.scale = defaultScale;
        App.soundEnabled = defaultSoundEnabled;

        App.input = new UserInputHandler();
        App.input.enableMouse = defaultMouseEnabled;
        App.game = new Game(App.input);
        App.display = new Screen(App.width, App.height);

        App app = new App();
        app.start();
    }
}

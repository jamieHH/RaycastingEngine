package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.raycasting.input.UserInputHandler;

public class RunApp
{
    public static int defaultWidth = 200;
    public static int defaultHeight = 150;
    public static int defaultScale = 4;
    public static boolean defaultSoundEnabled = false;

    public static void main(String args[]) {
        App.title = "raycasting_engine_pre_0.98";
        App.width = defaultWidth;
        App.height = defaultHeight;
        App.scale = defaultScale;
        App.soundEnabled = defaultSoundEnabled;

        App.input = new UserInputHandler();
        App.game = new Game(App.input);
//        App.display = new Screen(App.width, App.height, App.game);

        App app = new App();
        app.start();
    }
}

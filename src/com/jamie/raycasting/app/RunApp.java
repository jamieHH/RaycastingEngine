package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.raycasting.input.UserInputHandler;

public class RunApp
{
    public static void main(String args[]) {
        App.title = "raycasting_engine_pre_0.98";
        App.width = 200;
        App.height = 150;
        App.scale = 4;
        App.soundEnabled = false;

        App.input = new UserInputHandler();

        App app = new App();
        app.prepare();
        app.start();
    }
}

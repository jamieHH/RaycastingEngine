package com.jamie.jamapp;

public abstract class JamappClient
{
    public static InputHandler input;

    public JamappClient(InputHandler i) {
        input = i;
    }

    public abstract void tick();
}

package com.jamie.jamapp;

public abstract class GameLoop
{
    public InputHandler input;

    public GameLoop(InputHandler input) {
        this.input = input;
    }

    public abstract void tick();
}

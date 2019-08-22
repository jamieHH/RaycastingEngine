package com.jamie.jamapp;

public abstract class GameLoop
{
    public InputHandler userInput;

    public GameLoop(InputHandler input) {
        this.userInput = input;
    }

    public abstract void tick();
}

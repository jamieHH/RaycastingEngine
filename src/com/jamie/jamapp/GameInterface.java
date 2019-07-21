package com.jamie.jamapp;

public abstract class GameInterface
{
    public InputHandler userInput;

    public GameInterface(InputHandler input) {
        this.userInput = input;
    }

    public abstract void tick();
}

package com.jamie.raycasting.input;

import com.jamie.jamapp.InputHandler;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtificialInputHandler extends InputHandler
{
    private Map<String, Boolean> inputGroups = Stream.of(new Object[][] {
            {Controls.FORWARD, false},
            {Controls.BACK, false},
            {Controls.LEFT, false},
            {Controls.RIGHT, false},
            {Controls.ROTLEFT, false},
            {Controls.ROTRIGHT, false},
            {Controls.UP, false},
            {Controls.DOWN, false},
            {Controls.ENTER, false},
            {Controls.ACTION, false},
            {Controls.CROUCH, false},
            {Controls.HOT1, false},
            {Controls.HOT2, false},
            {Controls.HOT3, false},
            {Controls.INVENTORY, false},
            {Controls.PAUSE, false},
            {Controls.ROANDOMLEVEL, false},
            {Controls.LOADLEVEL, false},
            {Controls.NEXTMOB, false},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (boolean) data[1]));


    public boolean check(String key) {
        return inputGroups.get(key);
    }

    public void setInput(String key, boolean state) {
        inputGroups.replace(key, state);
    }

    public void stopInput(String inputGroup) {
        setInput(inputGroup, false);
    }
}

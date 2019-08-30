package com.jamie.raycasting.input;

import com.jamie.jamapp.InputHandler;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtificialInputHandler extends InputHandler
{
    private Map<String, Integer> inputGroups = Stream.of(new Object[][] {
            {Controls.FORWARD, 0},
            {Controls.BACK, 0},
            {Controls.LEFT, 0},
            {Controls.RIGHT, 0},
            {Controls.ROTLEFT, 0},
            {Controls.ROTRIGHT, 0},
            {Controls.UP, 0},
            {Controls.DOWN, 0},
            {Controls.ENTER, 0},
            {Controls.ACTION, 0},
            {Controls.CROUCH, 0},
            {Controls.HOT1, 0},
            {Controls.HOT2, 0},
            {Controls.HOT3, 0},
            {Controls.INVENTORY, 0},
            {Controls.PAUSE, 0},
            {Controls.ROANDOMLEVEL, 0},
            {Controls.LOADLEVEL, 0},
            {Controls.NEXTMOB, 0},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));


    public boolean check(String key) {
        return inputGroups.get(key) > 0;
    }

    public void setInput(String key, boolean state) {
        inputGroups.replace(key, (state) ? 1 : 0);
    }

    public void stopInput(String inputGroup) {
        setInput(inputGroup, false);
    }
}

package com.jamie.jamapp;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public abstract class InputHandler implements KeyListener, FocusListener
{
    protected Map<String, int[]> inputGroups = new HashMap<String, int[]>();
    public boolean
            forward,
            back,
            left,
            right,
            rotLeft,
            rotRight,
            hot1,
            hot2,
            hot3,
            action,
            inventory,
            pause,
            randomLevel,
            loadLevel,
            nextMob;



	public void tick() {}

    public void setInputState(String inputGroup, boolean state) {}

    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}

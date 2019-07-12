package com.jamie.jamapp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyBinder
{
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

    private static final String UP = "W";
    private static final String DOWN = "S";
    private static final String LEFT = "A";
    private static final String RIGHT = "D";
    private static final String ROTLEFT = "LEFT";
    private static final String ROTRIGHT = "RIGHT";
    private static final String ACTION = "SPACE";
    private static final String INVENTORY = "INVENTORY";
    private static final String HOTKEY1 = "1";
    private static final String HOTKEY2 = "2";
    private static final String HOTKEY3 = "3";
    private static final String PAUSE = "ESCAPE";

    private static final String RANDOMLEVEL = "RANDOMLEVEL";
    private static final String LOADLEVEL = "LOADLEVEL";
    private static final String NEXTMOB = "NEXTMOB";

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean rotLeft;
    public boolean rotright;
    public boolean action;
    public boolean inventory;
    public boolean hotkey1;
    public boolean hotkey2;
    public boolean hotkey3;
    public boolean pause;

    public boolean randomLevel;
    public boolean loadLevel;
    public boolean nextMob;





    public KeyBinder() {
        System.out.println("key binder initialised");

        setKeyBinding(UP, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                up = true;
            }
        });
        setKeyBinding(DOWN, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                down = true;
            }
        });
        setKeyBinding(LEFT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                left = true;
            }
        });
        setKeyBinding(RIGHT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                right = true;
            }
        });
        setKeyBinding(ROTLEFT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                rotLeft = true;
            }
        });
        setKeyBinding(ROTRIGHT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                rotright = true;
            }
        });
        setKeyBinding(ACTION, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                action = true;
            }
        });
        setKeyBinding(INVENTORY, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                inventory = true;
            }
        });
        setKeyBinding(HOTKEY1, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                hotkey1 = true;
            }
        });
        setKeyBinding(HOTKEY2, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                hotkey2 = true;
            }
        });
        setKeyBinding(HOTKEY3, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                hotkey3 = true;
            }
        });
        setKeyBinding(PAUSE, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pause = true;
            }
        });

        setKeyBinding(RANDOMLEVEL, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                randomLevel = true;
            }
        });
        setKeyBinding(LOADLEVEL, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                loadLevel = true;
            }
        });
        setKeyBinding(NEXTMOB, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                nextMob = true;
            }
        });
    }

    private void setKeyBinding(String keyString, AbstractAction action) {
        App.panel.getInputMap(IFW).put(KeyStroke.getKeyStroke(keyString), keyString);
        App.panel.getActionMap().put(keyString, action);
    }

    public void tick() {
//        up = false;
//        down = false;
//        left = false;
//        right = false;
//        rotLeft = false;
//        rotright = false;
//        action = false;
//        inventory = false;
//        hotkey1 = false;
//        hotkey2 = false;
//        hotkey3 = false;
//        pause = false;
//
//        randomLevel = false;
//        loadLevel = false;
//        nextMob = false;

        System.out.println(up);
    }
}

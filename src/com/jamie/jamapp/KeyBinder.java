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


    public KeyBinder() {
        System.out.println("key binder initialised");

        setKeyBinding(UP, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed up!");
            }
        });
        setKeyBinding(DOWN, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed down!");
            }
        });
        setKeyBinding(LEFT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed left!");
            }
        });
        setKeyBinding(RIGHT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed right!");
            }
        });
        setKeyBinding(ROTLEFT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed rot left!");
            }
        });
        setKeyBinding(ROTRIGHT, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed rot right!");
            }
        });
        setKeyBinding(ACTION, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed action!");
            }
        });
        setKeyBinding(INVENTORY, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed inventory!");
            }
        });
        setKeyBinding(HOTKEY1, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed hotkey 1!");
            }
        });
        setKeyBinding(HOTKEY2, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed hotkey 2!");
            }
        });
        setKeyBinding(HOTKEY3, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed hotkey 3!");
            }
        });
        setKeyBinding(PAUSE, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed pause!");
            }
        });


    }

    private void setKeyBinding(String keyString, AbstractAction action) {
        App.panel.getInputMap(IFW).put(KeyStroke.getKeyStroke(keyString), keyString);
        App.panel.getActionMap().put(keyString, action);
    }
}

package com.jamie.jamapp;

import java.awt.*;
import java.awt.event.*;

public abstract class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener, MouseWheelListener
{
    public boolean enableMouse = true;
    public boolean enableMouseDiffX = true;
    public boolean enableMouseDiffY = false;

    private int cX() {
        return (App.getFrameWidth()) / 2;
    }
    private int cY() {
        return (App.getFrameHeight()) / 2;
    }

    private Robot robot;
    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private int mouseX;
    private int mouseY;
    private int diffMouseX;
    private int diffMouseY;
    private int oldMouseX;
    private int oldMouseY;
    private double mouseSensitivity = 0.001;
    private boolean lockCursor = true;

    protected KeyEvent lastKeyEvent = null;


    protected InputHandler() {

    }

    public abstract boolean check(String key);

    public abstract void setInput(String key, boolean state);

    public abstract void stopInput(String inputGroup);

    public void setLastKeyEvent(KeyEvent c) {
        lastKeyEvent = c;
    }

    public KeyEvent grabLastKeyEvent() {
        KeyEvent c = lastKeyEvent;
        lastKeyEvent = null;
        return c;
    }

    public double getDiffMouseX() {
        if (enableMouseDiffX) {
            return diffMouseX * mouseSensitivity;
        }
        return 0;
    }

    public double getDiffMouseY() {
        if (enableMouseDiffY) {
            return diffMouseY * mouseSensitivity;
        }
        return 0;
    }

    public void tick() {
        if (enableMouse) {
            if (enableMouseDiffX) {
                diffMouseX = mouseX - oldMouseX;
                oldMouseX = cX() - App.getFrame().getInsets().left;
            }

            if (enableMouseDiffY) {
                diffMouseY = mouseY - oldMouseY;
                oldMouseY = cY() - App.getFrame().getInsets().top;
            }

            if (lockCursor) {
                centerCursor();
            }
        }
    }

    public void lockCursor() {
        if (enableMouse && !lockCursor) {
            lockCursor = true;
            centerCursor();
        }
    }

    public void unlockCursor() {
        if (enableMouse && lockCursor) {
            lockCursor = false;
            centerCursor();
        }
    }

    public void centerCursor() {
        Point p = App.getFrame().getLocationOnScreen();
        robot.mouseMove(p.x + cX(), p.y + cY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (enableMouse) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (enableMouse) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        setLastKeyEvent(keyEvent);
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

    }
}

package com.jamie.jamapp;

import java.awt.*;
import java.awt.event.*;

public abstract class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener, MouseWheelListener
{
    public Robot robot;
    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public int mouseX;
    public int mouseY;
    public int diffMouseX;
    public int diffMouseY;
    public int oldMouseX;
    public int oldMouseY;
    public double mouseSensitivity = 0.001;

    public boolean enableMouse = false;
    public boolean lockCursor = true;

    public boolean
            forward,
            back,
            left,
            right,
            rotLeft,
            rotRight,
            up,
            down,
            crouch,
            action,
            hot1,
            hot2,
            hot3,
            inventory,
            pause,
            randomLevel,
            loadLevel,
            nextMob;


    protected InputHandler() {

    }

    public double getDiffMouseX() {
        return diffMouseX * mouseSensitivity;
    }

    public double getDiffMouseY() {
        return diffMouseY * mouseSensitivity;
    }

    public void tick() {
        if (enableMouse) {
            int cX = (App.width * App.scale) / 2;
            int cY = (App.height * App.scale) / 2;

            diffMouseX = mouseX - oldMouseX;
            if (lockCursor) {
                oldMouseX = cX - 3;
            } else {
                oldMouseX = mouseX;
            }

            diffMouseY = mouseY - oldMouseY;
            if (lockCursor) {
                oldMouseY = cY - 37;
            } else {
                oldMouseY = mouseY;
            }

            if (lockCursor) {
                centerCursor();
            }
        }
    }

    public void lockCursor() {
        if (enableMouse) {
            lockCursor = true;
            centerCursor();
        }
    }

    public void unlockCursor() {
        if (enableMouse) {
            if (lockCursor) {
                lockCursor = false;
                centerCursor();
            }
        }
    }

    public void centerCursor() {
        int cX = (App.width * App.scale) / 2;
        int cY = (App.height * App.scale) / 2;
        Point p = App.frame.getLocationOnScreen();
        robot.mouseMove(p.x + cX, p.y + cY);
    }

    public void stopInput(String inputGroup) {}

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
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

    }
}

package com.jamie.jamapp;

import java.awt.event.*;

public abstract class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{
    public int mouseX;
    public int mouseY;
    public int diffMouseX;
    public int diffMouseY;
    public int oldMouseX;
    public int oldMouseY;
    public boolean enableMouse = false;

    public boolean
            forward,
            back,
            left,
            right,
            rotLeft,
            rotRight,
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

    public void tick() {
        if (enableMouse) {
            diffMouseX = mouseX - oldMouseX;
            oldMouseX = mouseX;
            diffMouseY = mouseY - oldMouseY;
            oldMouseY = mouseY;
        }
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
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (enableMouse) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }
}

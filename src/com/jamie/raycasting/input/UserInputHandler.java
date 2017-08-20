package com.jamie.raycasting.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class UserInputHandler extends InputHandler implements KeyListener, FocusListener {

	public boolean[] key = new boolean[68836];

	public boolean randomLevel, loadLevel, nextMob, pause;

	public int disableKey;
	public List<Object> disableKeys = new ArrayList<Object>();

	public void tick() {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

        if (disableTime > 0) {
            disableTime--;
            disableInputs();
            return;
        }

		forward = key[KeyEvent.VK_W] || key[KeyEvent.VK_UP];
		back = key[KeyEvent.VK_S] || key[KeyEvent.VK_DOWN];
		left = key[KeyEvent.VK_A];
		right = key[KeyEvent.VK_D];
		rotLeft = key[KeyEvent.VK_LEFT];
		rotRight = key[KeyEvent.VK_RIGHT];
		crouch = key[KeyEvent.VK_CONTROL];
		run = key[KeyEvent.VK_SHIFT];
		action = key[KeyEvent.VK_ENTER] || key[KeyEvent.VK_SPACE];

		randomLevel = key[KeyEvent.VK_R];
		loadLevel = key[KeyEvent.VK_P];
		nextMob = key[KeyEvent.VK_G];
        pause = key[KeyEvent.VK_ESCAPE];

        // add a method to disable keys
//        disableKey(disableKey);
    }

    private void disableKey(int keyEvent) {
        if (keyEvent == disableKey) {
            key[keyEvent] = false;
            disableKey = 0;
        }
    }

//    private void disableKeys(int keyEvent) {
//        for (int i = 0; i < disableKeys.size(); i++) {
//            // find a way to append key integers to a list and disable accordingly
//            int disableKey = disableKeys.get(i);
//
//            if (keyEvent == disableKey) {
//                key[keyEvent] = false;
//                disableKey = 0;
//            }
//        }
//    }

    protected void disableInputs() {
        forward = false;
        back  = false;
        left = false;
        right = false;
        rotLeft = false;
        rotRight = false;
        crouch = false;
        run = false;
        action = false;

        pause = false;

        randomLevel = false;
        loadLevel = false;
        nextMob = false;
    }

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		for (int i = 0; i < key.length; i++) {
			key[i] = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length) {
			key[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length) {
			key[keyCode] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

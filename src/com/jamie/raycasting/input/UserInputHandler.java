package com.jamie.raycasting.input;

import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.app.RunApp;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

public class UserInputHandler extends InputHandler
{
	private final boolean[] key = new boolean[68836];


    public UserInputHandler() {
        int[] forwardKeys = {KeyEvent.VK_W, KeyEvent.VK_UP};
        int[] backKeys = {KeyEvent.VK_S, KeyEvent.VK_DOWN};
        int[] leftKeys = {KeyEvent.VK_A};
        int[] rightKeys = {KeyEvent.VK_D};
        int[] rotLeftKeys = {KeyEvent.VK_LEFT};
        int[] rotRightKeys = {KeyEvent.VK_RIGHT};
        int[] actionKeys = {KeyEvent.VK_ENTER, KeyEvent.VK_SPACE};
        int[] inventoryKeys = {KeyEvent.VK_E};
        int[] hot1 = {KeyEvent.VK_1};
        int[] hot2 = {KeyEvent.VK_2};
        int[] hot3 = {KeyEvent.VK_3};
        int[] pauseKeys = {KeyEvent.VK_ESCAPE};
        int[] nextMob = {KeyEvent.VK_G};

        inputGroups.put(KeyControls.FORWARD, forwardKeys);
        inputGroups.put(KeyControls.BACK, backKeys);
        inputGroups.put(KeyControls.LEFT, leftKeys);
        inputGroups.put(KeyControls.RIGHT, rightKeys);
        inputGroups.put(KeyControls.ROTLEFT, rotLeftKeys);
        inputGroups.put(KeyControls.ROTRIGHT, rotRightKeys);
        inputGroups.put(KeyControls.ACTION, actionKeys);
        inputGroups.put(KeyControls.INVENTORY, inventoryKeys);
        inputGroups.put(KeyControls.HOT1, hot1);
        inputGroups.put(KeyControls.HOT2, hot2);
        inputGroups.put(KeyControls.HOT3, hot3);
        inputGroups.put(KeyControls.PAUSE, pauseKeys);
        inputGroups.put(KeyControls.NEXTMOB, nextMob);
    }

    protected boolean checkKeyGroup(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            if (key[keys[i]]) {
                return true;
            }
        }

    	return false;
	}
	
	private void setKeyState(int[] keys, boolean state) {
        for (int i = 0; i < keys.length; i++) {
            key[keys[i]] = state;
        }
    }

    public void stopInput(String inputGroup) {
        setKeyState(inputGroups.get(inputGroup), false);
    }

	public void tick() {
        forward = checkKeyGroup(inputGroups.get(KeyControls.FORWARD));
        back = checkKeyGroup(inputGroups.get(KeyControls.BACK));
        left = checkKeyGroup(inputGroups.get(KeyControls.LEFT));
        right = checkKeyGroup(inputGroups.get(KeyControls.RIGHT));
        rotLeft = checkKeyGroup(inputGroups.get(KeyControls.ROTLEFT));
        rotRight = checkKeyGroup(inputGroups.get(KeyControls.ROTRIGHT));
        action = checkKeyGroup(inputGroups.get(KeyControls.ACTION));
        inventory = checkKeyGroup(inputGroups.get(KeyControls.INVENTORY));
        hot1 = checkKeyGroup(inputGroups.get(KeyControls.HOT1));
        hot2 = checkKeyGroup(inputGroups.get(KeyControls.HOT2));
        hot3 = checkKeyGroup(inputGroups.get(KeyControls.HOT3));
        pause = checkKeyGroup(inputGroups.get(KeyControls.PAUSE));
        if (RunApp.inDev) {
            nextMob = checkKeyGroup(inputGroups.get(KeyControls.NEXTMOB));
            randomLevel = key[KeyEvent.VK_R];
            loadLevel = key[KeyEvent.VK_P];
        }
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

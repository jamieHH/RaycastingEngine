package com.jamie.raycasting.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class UserInputHandler extends InputHandler implements KeyListener, FocusListener
{
	private final boolean[] key = new boolean[68836];

    private Map<String, int[]> keyGroups = new HashMap<String, int[]>();

    public boolean randomLevel, loadLevel, nextMob, inventory, pause, yKey;

    public UserInputHandler() {
        int[] forwardKeys = {KeyEvent.VK_W, KeyEvent.VK_UP};
        int[] backKeys = {KeyEvent.VK_S, KeyEvent.VK_DOWN};
        int[] leftKeys = {KeyEvent.VK_A};
        int[] rightKeys = {KeyEvent.VK_D};
        int[] rotLeftKeys = {KeyEvent.VK_LEFT};
        int[] rotRightKeys = {KeyEvent.VK_RIGHT};
        int[] crouchKeys = {KeyEvent.VK_CONTROL};
        int[] actionKeys = {KeyEvent.VK_ENTER, KeyEvent.VK_SPACE};
        int[] inventoryKeys = {KeyEvent.VK_E};
        int[] pauseKeys = {KeyEvent.VK_ESCAPE};

        keyGroups.put("forward", forwardKeys);
        keyGroups.put("back", backKeys);
        keyGroups.put("left", leftKeys);
        keyGroups.put("right", rightKeys);
        keyGroups.put("rotLeft", rotLeftKeys);
        keyGroups.put("rotRight", rotRightKeys);
        keyGroups.put("crouch", crouchKeys);
        keyGroups.put("action", actionKeys);
        keyGroups.put("inventory", inventoryKeys);
        keyGroups.put("pause", pauseKeys);
    }

    private boolean checkKeyGroup(int[] keys) {
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

    public void setKeyGroupState(String keyGroup, boolean state) {
        setKeyState(keyGroups.get(keyGroup), state);
    }

	public void tick() {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

		forward = checkKeyGroup(keyGroups.get("forward"));
		back = checkKeyGroup(keyGroups.get("back"));
		left = checkKeyGroup(keyGroups.get("left"));
		right = checkKeyGroup(keyGroups.get("right"));
		rotLeft = checkKeyGroup(keyGroups.get("rotLeft"));
		rotRight = checkKeyGroup(keyGroups.get("rotRight"));
        crouch = checkKeyGroup(keyGroups.get("crouch"));
        action = checkKeyGroup(keyGroups.get("action"));
		inventory = checkKeyGroup(keyGroups.get("inventory"));
		pause = checkKeyGroup(keyGroups.get("pause"));

//		run = key[KeyEvent.VK_SHIFT];

        randomLevel = key[KeyEvent.VK_R];
        loadLevel = key[KeyEvent.VK_P];
        nextMob = key[KeyEvent.VK_G];
        yKey = key[KeyEvent.VK_Y];
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

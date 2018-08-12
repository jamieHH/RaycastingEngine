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

    public boolean randomLevel, loadLevel, nextMob, inventory, pause;

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
        int[] hot1 = {KeyEvent.VK_1};
        int[] hot2 = {KeyEvent.VK_2};
        int[] hot3 = {KeyEvent.VK_3};
        int[] pauseKeys = {KeyEvent.VK_ESCAPE};
        int[] nextMob = {KeyEvent.VK_G};

        keyGroups.put("forward", forwardKeys);
        keyGroups.put("back", backKeys);
        keyGroups.put("left", leftKeys);
        keyGroups.put("right", rightKeys);
        keyGroups.put("rotLeft", rotLeftKeys);
        keyGroups.put("rotRight", rotRightKeys);
        keyGroups.put("crouch", crouchKeys);
        keyGroups.put("action", actionKeys);
        keyGroups.put("inventory", inventoryKeys);
        keyGroups.put("hot1", hot1);
        keyGroups.put("hot2", hot2);
        keyGroups.put("hot3", hot3);
        keyGroups.put("pause", pauseKeys);
        keyGroups.put("nextMob", nextMob);
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
		forward = checkKeyGroup(keyGroups.get("forward"));
		back = checkKeyGroup(keyGroups.get("back"));
		left = checkKeyGroup(keyGroups.get("left"));
		right = checkKeyGroup(keyGroups.get("right"));
		rotLeft = checkKeyGroup(keyGroups.get("rotLeft"));
		rotRight = checkKeyGroup(keyGroups.get("rotRight"));
        crouch = checkKeyGroup(keyGroups.get("crouch"));
        action = checkKeyGroup(keyGroups.get("action"));
		inventory = checkKeyGroup(keyGroups.get("inventory"));
        hot1 = checkKeyGroup(keyGroups.get("hot1"));
        hot2 = checkKeyGroup(keyGroups.get("hot2"));
        hot3 = checkKeyGroup(keyGroups.get("hot3"));
        pause = checkKeyGroup(keyGroups.get("pause"));
        nextMob = checkKeyGroup(keyGroups.get("nextMob"));

//		run = key[KeyEvent.VK_SHIFT];

        randomLevel = key[KeyEvent.VK_R];
        loadLevel = key[KeyEvent.VK_P];
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

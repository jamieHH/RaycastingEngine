package com.jamie.raycasting.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInputHandler extends InputHandler implements KeyListener, FocusListener
{
	private final boolean[] key = new boolean[68836];
    public boolean randomLevel, loadLevel, nextMob, inventory, pause;


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

        inputGroups.put("forward", forwardKeys);
        inputGroups.put("back", backKeys);
        inputGroups.put("left", leftKeys);
        inputGroups.put("right", rightKeys);
        inputGroups.put("rotLeft", rotLeftKeys);
        inputGroups.put("rotRight", rotRightKeys);
        inputGroups.put("action", actionKeys);
        inputGroups.put("inventory", inventoryKeys);
        inputGroups.put("hot1", hot1);
        inputGroups.put("hot2", hot2);
        inputGroups.put("hot3", hot3);
        inputGroups.put("pause", pauseKeys);
        inputGroups.put("nextMob", nextMob);
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

    public void setInputState(String inputGroup, boolean state) {
        setKeyState(inputGroups.get(inputGroup), state);
    }

	public void tick() {
		forward = checkKeyGroup(inputGroups.get("forward"));
		back = checkKeyGroup(inputGroups.get("back"));
		left = checkKeyGroup(inputGroups.get("left"));
		right = checkKeyGroup(inputGroups.get("right"));
		rotLeft = checkKeyGroup(inputGroups.get("rotLeft"));
		rotRight = checkKeyGroup(inputGroups.get("rotRight"));
        action = checkKeyGroup(inputGroups.get("action"));
		inventory = checkKeyGroup(inputGroups.get("inventory"));
        hot1 = checkKeyGroup(inputGroups.get("hot1"));
        hot2 = checkKeyGroup(inputGroups.get("hot2"));
        hot3 = checkKeyGroup(inputGroups.get("hot3"));
        pause = checkKeyGroup(inputGroups.get("pause"));
        nextMob = checkKeyGroup(inputGroups.get("nextMob"));

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

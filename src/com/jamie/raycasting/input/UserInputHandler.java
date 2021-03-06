package com.jamie.raycasting.input;

import com.jamie.jamapp.InputHandler;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInputHandler extends InputHandler
{
    private final boolean[] key = new boolean[68836];
    private Map<String, Integer> inputGroups = Stream.of(new Object[][] {
            {Controls.FORWARD, KeyEvent.VK_W},
            {Controls.BACK, KeyEvent.VK_S},
            {Controls.LEFT, KeyEvent.VK_A},
            {Controls.RIGHT, KeyEvent.VK_D},
            {Controls.ROTLEFT, KeyEvent.VK_LEFT},
            {Controls.ROTRIGHT, KeyEvent.VK_RIGHT},
            {Controls.UP, KeyEvent.VK_UP},
            {Controls.DOWN, KeyEvent.VK_DOWN},
            {Controls.ENTER, KeyEvent.VK_ENTER},
            {Controls.ACTION, KeyEvent.VK_SPACE},
            {Controls.CROUCH, KeyEvent.VK_CONTROL},
            {Controls.HOT1, KeyEvent.VK_1},
            {Controls.HOT2, KeyEvent.VK_2},
            {Controls.HOT3, KeyEvent.VK_3},
            {Controls.INVENTORY, KeyEvent.VK_E},
            {Controls.PAUSE, KeyEvent.VK_ESCAPE},
            {Controls.CONSOLE, KeyEvent.VK_BACK_QUOTE},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (int) data[1]));


    public UserInputHandler() {

    }

    public boolean check(String key) {
        return this.key[inputGroups.get(key)];
	}
	
	public void setInput(String key, boolean state) {
        this.key[inputGroups.get(key)] = state;
    }

    public void stopInput(String inputGroup) {
        setInput(inputGroup, false);
    }

	@Override
	public void focusLost(FocusEvent e) {
		for (int i = 0; i < key.length; i++) {
			key[i] = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
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
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (enableMouse) {
            if (e.getWheelRotation() > 0) {
                setInput(Controls.DOWN, true);
            } else {
                setInput(Controls.UP, true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        super.mousePressed(mouseEvent);
        if (enableMouse) {
            setInput(Controls.ACTION, true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        super.mouseReleased(mouseEvent);
        if (enableMouse) {
            setInput(Controls.ACTION, false);
        }
    }
}

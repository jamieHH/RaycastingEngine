package com.jamie.raycasting.input;

import com.jamie.jamapp.InputHandler;
import com.jamie.raycasting.app.RunApp;

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
    private static Map<String, Integer> inputGroups = Stream.of(new Object[][] {
            {Controls.FORWARD, KeyEvent.VK_W,},
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
            {Controls.NEXTMOB, KeyEvent.VK_G},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (int) data[1]));


    public UserInputHandler() {
        enableMouse = true;
    }

    protected boolean check(String key) {
        return this.key[inputGroups.get(key)];
	}
	
	private void setKeyState(String key, boolean state) {
        this.key[inputGroups.get(key)] = state;
    }

    public void stopInput(String inputGroup) {
        setKeyState(inputGroup, false);
    }

	public void tick() {
        super.tick();
        forward = check(Controls.FORWARD);
        back = check(Controls.BACK);
        left = check(Controls.LEFT);
        right = check(Controls.RIGHT);
        rotLeft = check(Controls.ROTLEFT);
        rotRight = check(Controls.ROTRIGHT);
        up = check(Controls.UP);
        down = check(Controls.DOWN);
        action = check(Controls.ACTION) || check(Controls.ENTER);
        crouch = check(Controls.CROUCH);
        hot1 = check(Controls.HOT1);
        hot2 = check(Controls.HOT2);
        hot3 = check(Controls.HOT3);
        inventory = check(Controls.INVENTORY);
        pause = check(Controls.PAUSE);
        if (RunApp.inDev) {
            nextMob = check(Controls.NEXTMOB);
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

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        super.mouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        super.mousePressed(mouseEvent);
        if (enableMouse) {
            setKeyState(Controls.ACTION, true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        super.mouseReleased(mouseEvent);
        if (enableMouse) {
            setKeyState(Controls.ACTION, false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        super.mouseEntered(mouseEvent);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        super.mouseExited(mouseEvent);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (enableMouse) {
            System.out.println(e.getWheelRotation());
            if (e.getWheelRotation() > 0) {
                setKeyState(Controls.DOWN, true);
            } else {
                setKeyState(Controls.UP, true);
            }
        }
    }
}

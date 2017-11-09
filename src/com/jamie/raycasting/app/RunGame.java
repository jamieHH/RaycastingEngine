package com.jamie.raycasting.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class RunGame
{
    public JFrame frame;

    private Image getAppIcon() {
		Image img = null;
		try {
			img = ImageIO.read(new FileInputStream("res/textures/strongDoor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img;
	}

	public RunGame() {
		// BufferedImage cursor, cursor blank ??
		App game = new App();
		App.runGame = this;

		frame = new JFrame();
		frame.add(game);

		frame.setIconImage(getAppIcon());

		frame.setTitle(App.TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

	public void refreshFrame() {
        frame.dispose();

        frame = new JFrame();

		frame.setIconImage(getAppIcon());

        frame.setTitle(App.TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}

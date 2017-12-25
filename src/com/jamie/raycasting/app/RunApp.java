package com.jamie.raycasting.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class RunApp
{
    public JFrame frame;

	public RunApp() {
		App app = new App();
		App.runApp = this;

        frame = newFrame(app);

		app.start();
	}

	public void refreshFrame(App app) {
		frame.dispose();

        frame = newFrame(app);
	}

	private JFrame newFrame(App app) {
        JFrame f = new JFrame();
		f.add(app);

		f.setIconImage(getAppIcon());

		f.setTitle(App.TITLE);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty"));

		return f;
	}

    private Image getAppIcon() {
		Image img = null;
		try {
			img = ImageIO.read(new FileInputStream("res/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img;
	}
}

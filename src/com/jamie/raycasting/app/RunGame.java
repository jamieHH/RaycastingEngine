package com.jamie.raycasting.app;

import javax.swing.JFrame;

public class RunGame {

    public JFrame frame;

	public RunGame() {
		// BufferedImage cursor, cursor blank ??
		App game = new App();
		App.runGame = this;

		frame = new JFrame();

		frame.add(game);
		frame.setTitle(App.TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
			
		System.out.println("Running...");
		
		game.start();
	}

	public void refreshFrame() {
        frame.dispose();

        frame = new JFrame();
        frame.setTitle(App.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}

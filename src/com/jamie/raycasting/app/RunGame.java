package com.jamie.raycasting.app;

import javax.swing.JFrame;

public class RunGame {

	public RunGame() {
		// BufferedImage cursor, cursor blank ??
		App game = new App();
		JFrame frame = new JFrame();
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
}

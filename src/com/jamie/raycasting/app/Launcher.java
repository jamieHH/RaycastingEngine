package com.jamie.raycasting.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Launcher extends JFrame
{
	private static final long serialVersionUID = 1L;

	private final boolean autoLaunch = true;

	protected final JPanel window = new JPanel();

	private final int windowWidth = 220;
	private final int windowHeight = 220;

	protected final int button_width = 100;
	protected final int button_height = 30;
	protected final int select_width = 80;
	protected final int select_height = 30;
	protected final int label_height = 25;

	public Launcher(int id) {
		if (autoLaunch) {
//            App.width = 100;
//            App.height = 75;
//            App.scale = 8;
            App.width = 200;
            App.height = 150;
            App.scale = 4;
//			  App.width = 800;
//			  App.height = 600;
//			  App.scale = 1;
			new RunGame();
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}

			setTitle("Raycasting Engine");
			setSize(new Dimension(windowWidth, windowHeight));
			getContentPane().add(window);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			window.setLayout(null);

			if (id == 0) {
				drawButtons();
			}
			setVisible(true);
		}
	}

	private void drawButtons() {
		JButton play = new JButton("Play");
		play.setBounds((windowWidth / 2) - (button_width / 2), 40, button_width, button_height);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Play!");
				dispose();
				new RunGame();
			}
		});

		JButton options = new JButton("Options");
		options.setBounds((windowWidth / 2) - (button_width / 2), 80, button_width, button_height);
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Options!");
				dispose();
				new LaunchOptions();
			}
		});

		JButton quit = new JButton("Quit");
		quit.setBounds((windowWidth / 2) - (button_width / 2), 120, button_width, button_height);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		window.add(play);
		window.add(options);
		window.add(quit);
	}
}
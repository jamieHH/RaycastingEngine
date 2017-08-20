package com.jamie.raycasting.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.jamie.raycasting.app.RunGame;

public class Launcher extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private boolean devAutoLaunch = false ; // Used to start the game without launch menu.
	
	protected JPanel window = new JPanel();
	
	private int windowWidth = 220;
	private int windowHeight = 220;

	protected int button_width = 100;
	protected int button_height = 30;
	protected int select_width = 80;
	protected int select_height = 30;
	protected int label_height = 25;
	
	public Launcher(int id) {
		if (devAutoLaunch) {
			new RunGame();
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			setTitle("Launcher - Raycasting Engine");
			setSize(new Dimension(windowWidth, windowHeight));
			getContentPane().add(window);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

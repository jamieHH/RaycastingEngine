package com.jamie.raycasting.app;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class LaunchOptions extends Launcher {
	private static final long serialVersionUID = 1L;
	
	private int windowWidth = 340;
	private int windowHeight = 220;

	private int defaultAspectRatioIndex = 0;
	private int defaultResolutionIndex = 0;
	private int defaultScaleIndex = 0;

	// resolution options
	private String[] aspectRatios = {
		"4:3",
		"16:9"
	};
	
	private int[] scales = {
		8,
		4,
		2,
		1
	};
	
	private int[][] resolutions4x3 = {
		{100, 75},
		{200, 150},
		{400, 300},
		{800, 600},
	};
	
	private int[][] resolutions16x9= {
		{128, 72},
		{256, 144},
		{512, 288},
		{1024, 576},
	};
	//
	
	private Choice aspectRatio = new Choice();
	private Choice resolution = new Choice();
	private Choice scale = new Choice();
	
	public LaunchOptions() {
		super(1);
		setTitle("Options - Raycasting Engine");
		setSize(new Dimension(windowWidth, windowHeight));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		drawButtons();
		setVisible(true);
	}
	
	private void drawButtons() {
		// Aspect Ratio
		Label laspectRatio = new Label();
		laspectRatio.setText("Aspect Ratio");
		laspectRatio.setBounds(20, 20, select_width, label_height);

		aspectRatio.setBounds(20, 45, select_width, select_height);
		for(int i = 0; i < aspectRatios.length; i++) {
			aspectRatio.add(aspectRatios[i]);
		}
		aspectRatio.select(defaultAspectRatioIndex);

		// Resolution
		Label lresolution = new Label();
		lresolution.setText("Resolution");
		lresolution.setBounds(120, 20, select_width, label_height);

		resolution.setBounds(120, 45, select_width, select_height);
		for(int i = 0; i < resolutions4x3.length; i++) {
			resolution.add(Integer.toString(resolutions4x3[i][0]) + ", " + Integer.toString(resolutions4x3[i][1]));
		}
		resolution.select(defaultResolutionIndex);

		// Scale
		Label lscale = new Label();
		lscale.setText("Scale");
		lscale.setBounds(220, 20, select_width, label_height);

		scale.setBounds(220, 45, select_width, select_height);
		for(int i = 0; i < resolutions4x3.length; i++) {
			scale.add(Integer.toString(scales[i]) + ":1");
		}
		scale.select(defaultScaleIndex);
		
		// OK
		JButton OK = new JButton("OK");
		Rectangle rOK = new Rectangle((windowWidth - 120), (windowHeight - 70), button_width, button_height);
		OK.setBounds(rOK);
		
		// Cancel
		JButton cancel = new JButton("Cancel");
		Rectangle rcancel = new Rectangle(20, (windowHeight - 70), button_width, button_height);
		cancel.setBounds(rcancel);
		
		// Action Listeners
		aspectRatio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (aspectRatio.getSelectedItem() == "4:3") {
					resolution.removeAll();
					for(int i = 0; i < resolutions4x3.length; i++) {
						resolution.add(Integer.toString(resolutions4x3[i][0]) + ", " + Integer.toString(resolutions4x3[i][1]));
					}
					resolution.select(defaultResolutionIndex);
				} else if (aspectRatio.getSelectedItem() == "16:9") {
					resolution.removeAll();
					for(int i = 0; i < resolutions4x3.length; i++) {
						resolution.add(Integer.toString(resolutions16x9[i][0]) + ", " + Integer.toString(resolutions16x9[i][1]));
					}
					resolution.select(defaultResolutionIndex);
				}
			}
		});
		
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGameLaunchOptions();
				dispose();
				new Launcher(0);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher(0);
			}
		});

		window.add(laspectRatio);
		window.add(aspectRatio);
        window.add(lresolution);
        window.add(resolution);
        window.add(lscale);
        window.add(scale);
        window.add(cancel);
        window.add(OK);
	}
	
	public void setGameLaunchOptions() {
		if (aspectRatio.getSelectedItem() == "4:3") {
			App.width = resolutions4x3[resolution.getSelectedIndex()][0];
			App.height = resolutions4x3[resolution.getSelectedIndex()][1];
		} else if (aspectRatio.getSelectedItem() == "16:9") {
			App.width = resolutions16x9[resolution.getSelectedIndex()][0];
			App.height = resolutions16x9[resolution.getSelectedIndex()][1];
		}

		App.scale = scales[scale.getSelectedIndex()];
	}
}

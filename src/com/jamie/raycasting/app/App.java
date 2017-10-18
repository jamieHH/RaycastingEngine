package com.jamie.raycasting.app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.input.UserInputHandler;

public class App extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "raycasting_engine_pre_0.72";
	public static int width = 200;
	public static int height = 150;
	public static int scale = 4;
	
	private Thread thread;
	private Screen screen;
	private Game game;
	private BufferedImage img;
	private Boolean running = false;
	private final UserInputHandler input;
	private int[] pixels;
	private int ups;
	private int fps;

	public static boolean setNewOptions = false;
    public static int newWidth;
    public static int newHeight;
    public static int newScale;

	public static RunGame runGame;
	
	public App() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		input = new UserInputHandler();
		game = new Game(input);

		screen = new Screen(width, height, game);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		
		addKeyListener(input);
		addFocusListener(input);
	}

	public void changeResolution(int width, int height) {
	    App.width = width;
	    App.height = height;
        Dimension size = new Dimension(width * scale, height * scale);

        runGame.refreshFrame();
        runGame.frame.add(this);
        runGame.frame.setPreferredSize(size);
        runGame.frame.setMinimumSize(size);
        runGame.frame.setMaximumSize(size);
        runGame.frame.setLocationRelativeTo(null);
        runGame.frame.requestFocusInWindow();

		screen = new Screen(width, height, game);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    }
	
	public void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void run() {
		int updates = 0;
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1/60.0;
		int tickCount = 0;

		requestFocusInWindow();
		while (running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;

    		boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				if (tickCount % 60 == 0) {
					ups = updates;
					fps = frames;
					previousTime += 1000;
					updates = 0;
					frames = 0;
				}
			}

			if (ticked) {
				render();
				frames++;
			}

//			render();
//			frames++;
			updates++;
		}
	}
	
	private void tick() {
		input.tick();
		game.tick();

        if (setNewOptions) {
            App.scale = newScale;
            changeResolution(newWidth, newHeight);
            setNewOptions = false;
        }
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int fontSize = 16;
		
		screen.render(game);
		
		for (int i = 0; i < width * height; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, width * scale, height * scale, null);
		
		g.setFont(new Font("Verdana", Font.PLAIN, fontSize));
		g.setColor(Color.YELLOW);
		
		g.drawString("UPS: " + ups, 0, fontSize);
		g.drawString("FPS: " + fps , 0, (fontSize * 2));

//		g.drawString("Clip:" + ((game.player.clipping) ? "YES" : "NO"), 0, (height * scale) - (fontSize * 2) - 4);
//		g.drawString("X:" + game.player.posX, 0, (height * scale) - fontSize - 4);
//		g.drawString("Z:" + game.player.posZ, 0, (height * scale) - 4);
//		g.drawString("Y:" + game.player.camY, 0, (height * scale) - 4);
//		g.drawString("R:" + game.player.rotation, 0, (height * scale) - 4);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Launcher(0);
	}
}

package com.jamie.jamapp;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileInputStream;
import java.io.IOException;

import com.jamie.raycasting.app.Game;

import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	public static String title;
	public static int width;
	public static int height;
	public static int scale;
	public static boolean soundEnabled;
	public static boolean inDev;

	public static InputHandler input;
	public static Game game;
	public static Display display;

	public static boolean setNewOptions = false;
	public static int newWidth, newHeight, newScale;

    public static JFrame frame;
    private Thread thread;
    private BufferedImage img;
    private Boolean running = false;
    private int[] pixels;
    private int ups, fps;

    private boolean hadFocus;
    private Cursor emptyCursor, defaultCursor;


	public App() {
		emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty");
		defaultCursor = getCursor();

		addKeyListener(input);
		addFocusListener(input);

		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
	}

	public void start() {
		initialiseFrame();
		if (!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (running) {
			running = false;
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void run() {
		int updates = 0;
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1/60.0;
		int tickCount = 0;

		while (running) {
			requestFocus();
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

			updates++;
			if (ticked) {
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void changeResolution(int w, int h) {
		App.width = w;
		App.height = h;
		frame.dispose();

		initialiseFrame();
	}

    private void initialiseFrame() {
		display.setSize(width,height);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		frame = newFrame(this);
	}

    private static JFrame newFrame(App app) {
        JFrame f = new JFrame();
        f.add(app);
        f.setIconImage(getAppIcon());
        f.setTitle(App.title);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        return f;
    }

	private void tick() {
		input.tick();
		game.tick();

        if (setNewOptions) {
            setNewOptions = false;
            if (App.scale != newScale || App.width != newWidth || App.height != newHeight) {
                App.scale = newScale;
                changeResolution(newWidth, newHeight);
            }
        }
	}

	private void render() {
		if (hadFocus != hasFocus()) {
			hadFocus = !hadFocus;
			setCursor(hadFocus ? emptyCursor : defaultCursor);
		}

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

        display.render();
        System.arraycopy(display.pixels, 0, pixels, 0, width * height);

        Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, width * scale, height * scale, null);
		if (inDev) {
			int fontSize = 16;
			g.setFont(new Font("Verdana", Font.PLAIN, fontSize));
			g.setColor(Color.YELLOW);
			g.drawString("UPS: " + ups, 0, fontSize);
			g.drawString("FPS: " + fps, 0, (fontSize * 2));
		}
		g.dispose();
		bs.show();
	}

    private static Image getAppIcon() {
        Image img = null;
        try {
            img = ImageIO.read(new FileInputStream("res/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}

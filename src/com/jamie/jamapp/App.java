package com.jamie.jamapp;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	private static String title = "Untitled";
	private static int displayWidth;
	private static int displayHeight;
	private static int displayScale = 1;
	private static boolean soundEnabled = true;
	private static boolean fullscreenEnabled = false;
	private static boolean inDev = false;

	private static boolean reinitialiseFrame = false;

	private static JamappClient game;
	private static Display display;

    private static JFrame frame;
    private static BufferedImage img;
	private static int[] pixels;
	private Thread thread;
	private Boolean running = false;
    private int ups, fps;

    private boolean hadFocus;
    private final Cursor emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(
    		new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
			new Point(0, 0),
			"empty"
	);


	public App(JamappClient client, InputHandler input, Display display) {
		App.game = client;
		App.display = display;
		App.setDisplayResolution(display.width, display.height);

		addKeyListener(input);
		addFocusListener(input);

		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
	}

	public static JFrame getFrame() {
	    return frame;
    }

	public synchronized void start() {
		initialiseFrame();
		if (!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public synchronized void stop() {
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

	public static void setDisplayResolution(int w, int h) {
		reinitialiseFrame = true;
		App.displayWidth = w;
		App.displayHeight = h;
	}

	public static void setDisplayScale(int s) {
		reinitialiseFrame = true;
		App.displayScale = s;
	}

	public static void enableFullscreen(boolean i) {
		reinitialiseFrame = true;
		fullscreenEnabled = i;
	}

    private void initialiseFrame() {
		reinitialiseFrame = false;
		if (frame != null) {
			frame.dispose();
		}
		display.setSize(getDisplayWidth(), getDisplayHeight());
		img = new BufferedImage(getDisplayWidth(), getDisplayHeight(), BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

		Dimension size = new Dimension(getFrameWidth(), getFrameHeight());
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		frame = newFrame(this);
		requestFocus();
	}

    private static JFrame newFrame(App app) {
        JFrame f = new JFrame();
        f.setUndecorated(fullscreenEnabled);
        f.add(app);
        f.setIconImage(getAppIcon());
        f.setTitle(App.title);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
		f.setVisible(true);
		f.pack();
		f.setLocationRelativeTo(null);
		return f;
    }

	private void tick() {
		game.tick();

		if (reinitialiseFrame) {
			initialiseFrame();
		}
	}

	private void render() {
		if (hadFocus != hasFocus()) {
			hadFocus = !hadFocus;
			setCursor(hadFocus ? emptyCursor : getCursor());
		}

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

        display.tick();
        System.arraycopy(display.pixels, 0, pixels, 0, getDisplayWidth() * getDisplayHeight());

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, getFrameWidth(), getFrameHeight(), null);
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
			//img = ImageIO.read(App.class.getClassLoader().getResource("logo.png"));
            img = ImageIO.read(new File("res/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static int getFrameWidth() {
		if (fullscreenEnabled) {
			return Toolkit.getDefaultToolkit().getScreenSize().width;
		}

		return displayWidth * displayScale;
	}

	public static int getFrameHeight() {
		if (fullscreenEnabled) {
			return Toolkit.getDefaultToolkit().getScreenSize().height;
		}

		return displayHeight * displayScale;
	}

	public static int getDisplayWidth() {
		return displayWidth;
	}

	public static int getDisplayHeight() {
		return displayHeight;
	}

	public static int getDisplayScale() {
		return displayScale;
	}

	public static void enableSound(boolean i) {
		soundEnabled = i;
	}

	public static boolean getFullscreenEnabled() {
		return fullscreenEnabled;
	}

	public static boolean getSoundEnabled() {
		return soundEnabled;
	}

	public static void setTitle(String title) {
		App.title = title;
	}

	public static void setIsInDev(boolean i) {
		App.inDev = i;
	}

	public static boolean getIsInDev() {
		return App.inDev;
	}
}

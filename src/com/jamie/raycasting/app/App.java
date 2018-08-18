package com.jamie.raycasting.app;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileInputStream;
import java.io.IOException;

import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.input.UserInputHandler;

import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	public static final String TITLE = "raycasting_engine_pre_0.96";
	public static int width = 200;
	public static int height = 150;
	public static int scale = 4;
	public static boolean soundEnabled = true;

	public static boolean setNewOptions = false;
	public static int newWidth, newHeight, newScale;

    public static JFrame frame;
    private Thread thread;
    private Screen screen;
    private Game game;
    private BufferedImage img;
    private Boolean running = false;
    private final UserInputHandler input;
    private int[] pixels;
    private int ups, fps;

    private boolean hadFocus;
	private Cursor emptyCursor, defaultCursor;


	public App() {
        setCanvas();

		input = new UserInputHandler();
		game = new Game(input);

		screen = new Screen(width, height, game);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();

		emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty");
		defaultCursor = getCursor();

		addKeyListener(input);
		addFocusListener(input);
	}

    public static void main(String args[]) {
        App app = new App();
        frame = newFrame(app);
        app.start();
    }

    private void setCanvas() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

	private void changeResolution(int width, int height) {
	    App.width = width;
	    App.height = height;
        setCanvas();

        refreshFrame(this);
		requestFocus();

		screen = new Screen(width, height, game);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    }

	public void start() {
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

		requestFocus();
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
			}
		}
	}

	private void tick() {
		input.tick();
		game.tick();

        if (setNewOptions) {
            setNewOptions = false;
            App.scale = newScale;
            changeResolution(newWidth, newHeight);
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

        screen.render(game);
        System.arraycopy(screen.pixels, 0, pixels, 0, width * height);

        int fontSize = 16;
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

	private void refreshFrame(App app) {
		frame.dispose();
		frame = newFrame(app);
	}

	private static JFrame newFrame(App app) {
        JFrame f = new JFrame();
        f.add(app);
        f.setIconImage(getAppIcon());
        f.setTitle(App.TITLE);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
		return f;
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

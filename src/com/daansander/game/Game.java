package com.daansander.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.daansander.game.graphics.Screen;
import com.daansander.game.input.InputHandler;
import com.daansander.game.level.Level;
import com.daansander.game.level.tile.Tile;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private JFrame frame;
	private Screen screen;
	private InputHandler input;
	private Level level;

	private boolean running = false;

	public Game() {
		Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);

		frame = new JFrame(NAME);
		screen = new Screen(WIDTH, HEIGHT);
		input = new InputHandler(this);
		level = new Level(2);

		frame.setResizable(false);
		frame.setTitle(NAME);
		frame.add(this);

		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		frame.requestFocus();

		start();
	}

	public synchronized void start() {
		if (!running) {
			running = true;
			new Thread(this, "Game").start();
		}
	}

	public synchronized void stop() {
		running = false;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;

		double delta = 0;

		int frames = 0;
		int ticks = 0;
		while (running) {
			long currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / ns;

			lastTime = currentTime;

			if (delta > 1) {
				tick();
				ticks++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;

				frame.setTitle(NAME + " | fps " + frames + " , tps " + ticks);

				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {

		if (input.up.down) yOffset--;
		if (input.down.down) yOffset++;
		if (input.right.down) xOffset++;
		if (input.left.down) xOffset--;

		input.tick();
	}

	int xOffset = 0, yOffset = 0;

	public void render() {
		BufferStrategy bf = getBufferStrategy();

		if (bf == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics graphics = bf.getDrawGraphics();

		screen.clear();
		// screen.render(xOffset , yOffset);
//		screen.renderTile(1, 0, Tile.grassTile);
		level.render(screen, xOffset, yOffset);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		graphics.dispose();
		bf.show();
	}

	public static void main(String[] args) {
		new Game();
	}

}
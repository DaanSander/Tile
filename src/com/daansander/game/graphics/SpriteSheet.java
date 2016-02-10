package com.daansander.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public final int SIZE;
	public int[] pixels;
	private String path;

	public static SpriteSheet sprites = new SpriteSheet("/sprites.png", 256);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;

		pixels = new int[SIZE * SIZE];
		load();
	}

	public void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));

			int width = image.getWidth();
			int height = image.getHeight();

			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
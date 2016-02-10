package com.daansander.game.graphics;

public class Sprite {

	public final int SIZE;
	public int[] pixels;
	private SpriteSheet sheet;
	private int x, y;

	public static Sprite grassSprite = new Sprite(0, 0, 16, SpriteSheet.sprites);

	public Sprite(int x, int y, int size, SpriteSheet sheet) {
		this.x = x;
		this.y = y;
		this.SIZE = size;
		this.sheet = sheet;

		pixels = new int[SIZE * SIZE];

		load();
	}

	public void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[((this.x * SIZE) + x) + ((this.y * SIZE) + y) * sheet.SIZE];
			}
		}
	}
}
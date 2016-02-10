package com.daansander.game.graphics;

import java.util.Random;

import com.daansander.game.level.tile.Tile;

public class Screen {

	public int[] pixels;
	public int[] tiles;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	private Random random;
	private int width, height;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		random = new Random();
		pixels = new int[width * height];
		tiles = new int[MAP_SIZE * MAP_SIZE];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xfffffff);
		}

		tiles[0] = 0;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		// for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
		// tiles[i] = random.nextInt(0xfffffff);
		// }
		for (int y = 0; y < height; y++) {
			int yp = y + yOffset;
			if (yp >= height || yp < 0) continue;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp >= width || xp < 0) continue;
				int tileIndex = ((xp >> 4) & MAP_SIZE_MASK) + ((yp >> 4) & MAP_SIZE_MASK) * MAP_SIZE;

				// pixels[xp + yp * width] = tiles[(xp >> 5) & MAP_SIZE - 1 +
				// (yp >> 5) & MAP_SIZE - 1 * MAP_SIZE];
				pixels[x + y * width] = Sprite.grassSprite.pixels[(xp & 15) + (yp & 15) * Sprite.grassSprite.SIZE];

			}
		}
	}

	public void renderTile(int x, int y, Tile tile) {
		x = x * 16;
		y = y * 16;
		for (int yp = 0; yp < tile.sprite.SIZE; yp++) {
			if (yp + y >= height || yp + y < 0) continue;
			for (int xp = 0; xp < tile.sprite.SIZE; xp++) {
				if (xp + x >= width || xp + x < 0) continue;
				pixels[(xp + x) + (yp + y) * width] = tile.sprite.pixels[xp + yp * tile.sprite.SIZE];
			}
		}
	}
}
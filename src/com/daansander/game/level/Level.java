package com.daansander.game.level;

import java.util.Random;

import com.daansander.game.Game;
import com.daansander.game.graphics.Screen;
import com.daansander.game.level.tile.Tile;

public class Level {

	public final int SIZE;

	protected int[] tiles;
	protected Random random = new Random();

	public Level(int size) {
		this.SIZE = size;

		tiles = new int[Game.WIDTH * Game.HEIGHT];
		
		generate();
	}

	protected void generate() {
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = 0;
		}
	}
	
	public void render(Screen screen, int xScroll, int yScroll) {
		renderTiles(screen, xScroll, yScroll);
	}

	int y = 0, x = 0;
	int allTiles = 0;

	protected void renderTiles(Screen screen, int xScroll, int yScroll) {
		for(int y = 0; y < (Game.HEIGHT + yScroll) / 16; y++) {
			for(int x = 0; x < (Game.WIDTH + xScroll) / 16; x++) {
				System.out.println("x" + x + "y" + y);
//				if(x * 16 >= Game.WIDTH - 16 || x * 16 < 0 || y * 16 >= Game.HEIGHT - 16 || y * 16 < 0) continue;
				allTiles++;
//				screen.renderTile(x, y, Tile.grassTile);\
				getTile(x, y).render(x, y, screen);
			}
		}
		System.out.println("Tiles " + allTiles);	
		allTiles = 0;
	}
	
	public Tile getTile(int x, int y) {
		return (Tile.tiles[tiles[x + y * Game.WIDTH]]);
	}
	
}
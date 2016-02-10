package com.daansander.game.level;

import com.daansander.game.Game;
import com.daansander.game.graphics.Screen;
import com.daansander.game.level.tile.Tile;

public class Level {

	public final int SIZE;

	private Tile[] tiles;

	public Level(int size) {
		this.SIZE = size;

		tiles = new Tile[SIZE * SIZE];
		
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = Tile.grassTile;
		}
	}

	public void render(Screen screen, int xScroll, int yScroll) {
		renderTiles(screen, xScroll, yScroll);
	}

	int y = 0, x = 0;

	private void renderTiles(Screen screen, int xScroll, int yScroll) {
		for(int y = 0; y < (Game.HEIGHT + yScroll) / 16; y++) {
			for(int x = 0; x < (Game.WIDTH + xScroll) / 16; x++) {
				screen.renderTile(x, y, Tile.grassTile);
			}
		}
	}
}
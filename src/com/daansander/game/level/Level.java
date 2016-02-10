package com.daansander.game.level;

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
		for (int i = 0; i < tiles.length; i++) {
			if (x >= SIZE) {
				y++;
				x = 0;
			}
			
//			screen.renderTile(x, y, Tile.grassTile);
			tiles[i].render(x + xScroll, y + yScroll, screen);
			x++;
		}

		x = 0;
		y = 0;
	}
}
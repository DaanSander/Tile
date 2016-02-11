package com.daansander.game.level.tile;

import com.daansander.game.Game;
import com.daansander.game.graphics.Screen;
import com.daansander.game.graphics.Sprite;

public class Tile {

	public Sprite sprite;
	public final int id;
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile();
	public static Tile VoidTile = new VoidTile();

	protected Tile(Sprite sprite, int id) {
		this.id = id;
		if(tiles[id] != null) {
			System.err.println("Duplicate id!");
			return;
		}
		tiles[id] = this;
		
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
		if (!(x * 16 >= Game.WIDTH || x * 16 < 0 || y * 16 >= Game.HEIGHT || y < 0)) {
			screen.renderTile(x, y, this);
		}
	}

	public void tick() {

	}
}
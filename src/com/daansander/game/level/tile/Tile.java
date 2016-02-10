package com.daansander.game.level.tile;

import com.daansander.game.graphics.Screen;
import com.daansander.game.graphics.Sprite;

public class Tile {

	public Sprite sprite;
	
	public static Tile grassTile = new GrassTile();
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this);	
	}
	
	public void tick() {
		
	}
}
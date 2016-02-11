package com.daansander.game.level;

public class RandomLevel extends Level {

	public RandomLevel(int size) {
		super(size);
	}

	@Override
	protected void generate() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(2);
		}
	}

}
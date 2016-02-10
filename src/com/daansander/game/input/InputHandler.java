package com.daansander.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.daansander.game.Game;

public class InputHandler implements KeyListener {

	public class Key {

		public boolean down = false, pressed = false;
		private int polled = 0;

		protected Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if (down != pressed) {
				down = pressed;
				this.pressed = pressed;
			}
			if (down) {
				down = true;
				polled++;
			}
		}

		public void tick() {
			if (down)
				polled++;
			else
				polled = 0;

			if (polled > 1) pressed = false;
		}

	}

	private List<Key> keys = new ArrayList<>();

	public Key up = new Key();
	public Key down = new Key();
	public Key right = new Key();
	public Key left = new Key();
	public Key space = new Key();

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++)
			keys.get(i).tick();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e.getKeyCode(), false);
	}

	public void toggle(int key, boolean pressed) {
		if (key == KeyEvent.VK_UP) up.toggle(pressed);
		if (key == KeyEvent.VK_DOWN) down.toggle(pressed);
		if (key == KeyEvent.VK_RIGHT) right.toggle(pressed);
		if (key == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (key == KeyEvent.VK_SPACE) space.toggle(pressed);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
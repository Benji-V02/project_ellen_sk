package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;

public class KeeperController implements KeyboardListener {

	@Override
	public void keyPressed(Input.@NotNull Key key) {
		KeyboardListener.super.keyPressed(key);
		if (key == Input.Key.ENTER) {
		}
	}
}

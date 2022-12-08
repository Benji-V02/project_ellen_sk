package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;

public class KeeperController implements KeyboardListener {

	private final Keeper keeper;

	public KeeperController(Keeper keeper) {
		this.keeper = keeper;
	}


	@Override
	public void keyPressed(Input.@NotNull Key key) {
		KeyboardListener.super.keyPressed(key);
		switch (key) {
			case ENTER:
				new Take<>().scheduleFor(keeper);
				break;
			case BACKSPACE:
				new Drop<>().scheduleFor(keeper);
				break;
			case S:
				new Shift<>().scheduleFor(keeper);
		}
	}
}

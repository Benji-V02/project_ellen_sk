package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

public class KeeperController implements KeyboardListener {

	private final Keeper keeper;

	public KeeperController(Keeper keeper) {
		this.keeper = keeper;
	}


	@Override
	public void keyPressed(Input.Key key) {
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
				break;
			case U:
				getImpostor();
				break;
			case B:
				getFromBackpack();
				break;
			default:
				break;

		}
	}


	private void getImpostor() {
		for (Actor impostor : keeper.getScene().getActors()) {
			if (impostor.intersects(keeper) && impostor instanceof Usable) {
				new Use<>((Usable<?>) impostor).scheduleForIntersectingWith(keeper);
				return;
			}
		}
	}

	private void getFromBackpack() {
		if (keeper.getBackpack().peek() instanceof Usable) {
			new Use<>((Usable<?>) keeper.getBackpack().peek()).scheduleForIntersectingWith(keeper);
		}
	}
}

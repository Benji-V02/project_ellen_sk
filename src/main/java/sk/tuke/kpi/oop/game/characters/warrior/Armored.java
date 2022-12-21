package sk.tuke.kpi.oop.game.characters.warrior;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.characters.Health;

public class Armored extends AbstractActor implements Mutable {

	private final Mutable mutation;

	public Armored() {
		mutation = null;
	}

	@Override
	public int getSpeed() {
		if (mutation == null) return -1;
		return -1 + mutation.getSpeed();
	}

	@Override
	public Health getHealth() {
		return null;
	}
}

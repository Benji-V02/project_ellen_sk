package sk.tuke.kpi.oop.game.characters.warrior;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Health;

public class Warrior extends AbstractWarrior implements Movable, Alive, Enemy, Mutable {

	private Mutable mutation;

	public Warrior() {
		mutation = null;
	}

	@Override
	public int getSpeed() {
		if (mutation == null) return 2;
		return 2 + mutation.getSpeed();
	}

	@Override
	public void startedMoving(Direction direction) {
		Movable.super.startedMoving(direction);
	}

	@Override
	public Health getHealth() {
		return null;
	}

	public void setMutation(Mutable mutation) {

	}
}

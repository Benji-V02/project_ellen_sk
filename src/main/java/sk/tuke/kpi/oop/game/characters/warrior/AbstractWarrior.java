package sk.tuke.kpi.oop.game.characters.warrior;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class AbstractWarrior extends AbstractActor implements Warrior {

	protected Warrior mutation;

	public AbstractWarrior(Warrior mutation) {
		this.mutation = mutation;
	}

	@Override
	public int getSpeed() {
		return mutation.getSpeed();
	}

	public int getHealthModifier() {
		if (mutation == null) return 0;
		return mutation.getHealthModifier();
	}
}

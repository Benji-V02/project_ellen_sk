package sk.tuke.kpi.oop.game.characters.warrior;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Armored extends AbstractWarrior {

	public Armored(Warrior mutation) {
		super(mutation);
		setAnimation(new Animation("sprites/solger/helmet.png"));
	}

	@Override
	public int getSpeed() {
		return -1 + mutation.getSpeed();
	}

	@Override
	public int getHealthModifier() {
		return 100 + mutation.getHealthModifier();
	}

}

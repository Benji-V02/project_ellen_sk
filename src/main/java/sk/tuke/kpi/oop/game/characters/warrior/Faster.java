package sk.tuke.kpi.oop.game.characters.warrior;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Faster extends AbstractWarrior {

	public Faster(Warrior mutation) {
		super(mutation);
		setAnimation(new Animation("sprites/solger/sprint.png"));
	}

	@Override
	public int getSpeed() {
		return mutation.getSpeed() + 1;
	}
}

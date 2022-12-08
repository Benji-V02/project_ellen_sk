package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo extends AbstractActor implements Usable<Ripley> {

	public Ammo() {
		setAnimation(new Animation("sprites/ammo.png"));
	}

	@Override
	public void useWith(Ripley actor) {
		if (actor.getEnergy() == 500)
			return;
		actor.setEnergy(50);
		this.getScene().removeActor(this);
	}

	@Override
	public Class<Ripley> getUsingActorClass() {
		return Ripley.class;
	}
}

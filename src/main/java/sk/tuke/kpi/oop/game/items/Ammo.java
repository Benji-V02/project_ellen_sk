package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Actor;

public class Ammo extends AbstractActor implements Usable<Actor> {

	public Ammo() {
		setAnimation(new Animation("sprites/ammo.png"));
	}

	@Override
	public void useWith(Actor actor) {
		if (actor.getAmmo() == 500)
			return;
		actor.setAmmo(50);
		this.getScene().removeActor(this);
	}

	@Override
	public Class<Actor> getUsingActorClass() {
		return Actor.class;
	}
}

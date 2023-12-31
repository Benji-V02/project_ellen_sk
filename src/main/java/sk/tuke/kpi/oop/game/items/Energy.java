package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Energy extends AbstractActor implements Usable<Alive>, Collectible {


	public Energy() {
		setAnimation(new Animation("sprites/energy.png"));
	}


	@Override
	public void useWith(Alive actor) {
		if (actor == null || actor.getHealth().getValue() == 100 || actor.getHealth().getValue() == 0)
			return;
		actor.getHealth().restore();
		this.getScene().removeActor(this);
	}

	@Override
	public Class<Alive> getUsingActorClass() {
		return Alive.class;
	}
}

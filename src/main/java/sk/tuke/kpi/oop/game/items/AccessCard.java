package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor> {
	@Override
	public void useWith(LockedDoor actor) {

	}

	@Override
	public Class<LockedDoor> getUsingActorClass() {
		return null;
	}
}

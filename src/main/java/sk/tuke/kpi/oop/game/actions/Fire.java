package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

public class Fire<A extends Armed> extends AbstractAction<A> {
	@Override
	public void execute(float deltaTime) {
		setDone(true);
		if (getActor() == null) return;
		Fireable bullet = getActor().getFirearm().fire();
		if (bullet == null) return;
		getActor().getScene().addActor(bullet);
		new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(bullet);
	}
}

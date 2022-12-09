package sk.tuke.kpi.oop.game.behaviour;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class RandomlyMoving implements Behaviour<Movable> {


	@Override
	public void setUp(Movable actor) {
		if (actor == null)
			return;

		new Loop<>(
			new ActionSequence<>(
				new Invoke<>(() -> {
					actor.startedMoving(Direction.values()[(int) (Math.random() * 9)]);
				}),
				new Wait<>((int) (Math.random() * 10))
			)).scheduleFor(actor);

	}
}

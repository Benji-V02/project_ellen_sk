package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving implements Behaviour<Movable> {

	private Disposable disposable;

	public RandomlyMoving() {
	}

	@Override
	public void setUp(Movable actor) {
		if (actor == null)
			return;

		new Loop<>(
			new ActionSequence<>(
				new Invoke<>(() -> {
					if (disposable != null) disposable.dispose();
					disposable = new Move<>(Direction.values()[(int) (Math.random() * 8)], Float.MAX_VALUE).scheduleFor(actor);
				}),
				new Wait<>(1)
			)).scheduleFor(actor);

	}
}

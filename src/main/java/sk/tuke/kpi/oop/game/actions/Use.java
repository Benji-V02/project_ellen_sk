package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;


public class Use<A extends Actor> extends AbstractAction<A> {

	private final Usable<A> actor;
	private A guy;


	public Use(Usable<A> actor) {
		this.actor = actor;
	}

	@Override
	public @NotNull Disposable scheduleFor(@NotNull A actor) {
		guy = actor;
		return super.scheduleFor(actor);
	}

	@Override
	public void execute(float deltaTime) {
		if (guy.intersects(actor)) {

		}

		setDone(true);
	}
}

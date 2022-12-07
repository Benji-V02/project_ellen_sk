package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Keeper;

public class Take<A extends Keeper> implements Action<A> {
	@Override
	public @Nullable A getActor() {
		return null;
	}

	@Override
	public void setActor(@Nullable A actor) {

	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public void execute(float deltaTime) {

	}

	@Override
	public void reset() {

	}
}

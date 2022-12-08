package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<M extends Movable> implements Action<M> {

	private Direction direction;
	private final float duration;
	private float timer;
	private M actor;
	protected final static float ZERO = 1e-5f;

	public Move(Direction direction, float duration) {
		this.direction = direction;
		this.duration = duration;
		this.timer = this.duration;
	}

	public Move(Direction direction) {
		this(direction, ZERO);
	}

	@Override
	public @Nullable M getActor() {
		return actor;
	}

	@Override
	public void setActor(@Nullable M actor) {
		this.actor = actor;
	}

	@Override
	public boolean isDone() {
		//System.out.println((timer > ZERO && timer <= duration) || actor == null);
		//if (actor == null) return false;
		if ((timer >= ZERO && timer <= duration) || actor == null) return false;
		actor.stoppedMoving();
		return true;
	}

	@Override
	public void execute(float deltaTime) {
		//System.out.println(timer);
		if (timer == duration) {
			actor.startedMoving(direction);
		} else if (timer < ZERO) {
			actor.stoppedMoving();
			timer = 0;
			direction = Direction.NONE;
			return;
		}
		if (timer <= duration && timer > ZERO && !getActor().getScene().getMap().intersectsWithWall(actor)) {
			actor.setPosition(
				actor.getPosX() - (int) Math.round(actor.getSpeed() * Math.sin(Math.toRadians(direction.getAngle()))),
				actor.getPosY() + (int) Math.round(actor.getSpeed() * Math.cos(Math.toRadians(direction.getAngle())))
			);
		}

		timer -= deltaTime;
	}

	@Override
	public void reset() {
		timer = duration;
	}

	public void stop() {
		timer = ZERO;
		isDone();
	}
}

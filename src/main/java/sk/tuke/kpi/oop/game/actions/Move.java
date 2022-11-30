package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move implements Action<Movable> {

	private Direction direction;
	private final float duration;
	private float timer;
	private Movable actor;
	protected final static float ZERO = 1e-5f;

	public Move(Direction direction, float duration) {
		this.direction = direction;
		this.duration = duration;
		this.timer = this.duration;
	}

	public Move(Direction direction) {
		this(direction, 0f);
	}

	@Override
	public @Nullable Movable getActor() {
		return actor;
	}

	@Override
	public void setActor(@Nullable Movable actor) {
		this.actor = actor;
	}

	@Override
	public boolean isDone() {
		if ((timer > ZERO && timer <= duration) || actor == null) return false;
		actor.stoppedMoving();
		return true;
	}

	@Override
	public void execute(float deltaTime) {
		//System.out.println(timer);
		if (timer == duration) {
			actor.startedMoving(direction);
		} else if (timer <= ZERO) {
			actor.stoppedMoving();
			timer = 0;
			direction = Direction.NONE;
			return;
		}
		if (timer <= duration && timer > ZERO) {
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

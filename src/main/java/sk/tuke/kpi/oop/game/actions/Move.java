package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<M extends Movable> implements Action<M> {

	private Direction direction;
	private boolean done;
	private final float duration;
	private float timer;
	private M actor;
	protected final static float ZERO = 1e-5f;

	public Move(Direction direction, float duration) {
		this.direction = direction;
		this.duration = duration;
		this.timer = this.duration;
		done = false;
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
		if (timer <= ZERO) done = true;
		if (done) actor.stoppedMoving();
		return done;
	}

	@Override
	public void execute(float deltaTime) {
		//System.out.println(timer);
		if (timer == duration) {
			actor.startedMoving(direction);
		} else if (timer <= ZERO) {

			timer = ZERO;
			direction = Direction.NONE;
			return;
		}
		if (timer <= duration && timer > ZERO && !getActor().getScene().getMap().intersectsWithWall(actor)) {
			actor.setPosition(
				actor.getPosX() - (int) Math.round(actor.getSpeed() * Math.sin(Math.toRadians(direction.getAngle()))),
				actor.getPosY() + (int) Math.round(actor.getSpeed() * Math.cos(Math.toRadians(direction.getAngle())))
			);
		}
		if (getActor().getScene().getMap().intersectsWithWall(actor)) {
			actor.setPosition(
				actor.getPosX() + (int) Math.round(actor.getSpeed() * Math.sin(Math.toRadians(direction.getAngle()))),
				actor.getPosY() - (int) Math.round(actor.getSpeed() * Math.cos(Math.toRadians(direction.getAngle())))
			);
			actor.collidedWithWall();
		}

		timer -= deltaTime;
	}

	@Override
	public void reset() {
		timer = duration;
		done = false;
	}

	public void stop() {
		timer = ZERO;
		isDone();
	}
}

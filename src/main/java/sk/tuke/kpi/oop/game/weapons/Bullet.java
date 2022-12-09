package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;

public class Bullet extends AbstractActor implements Fireable {

	private final int speed;

	public Bullet() {
		this.speed = 4;
		setAnimation(new Animation("sprites/bullet.png"));
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void startedMoving(Direction direction) {
		if (direction != null && direction != Direction.NONE) {
			this.getAnimation().setRotation(direction.getAngle());
		}

	}


	@Override
	public void collidedWithWall() {
		getScene().removeActor(this);
	}

}

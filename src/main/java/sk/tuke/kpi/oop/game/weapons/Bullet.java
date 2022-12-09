package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;

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

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<>(() -> {
			Actor target = scene.getActors()
			                    .stream()
			                    .filter(member -> member.intersects(this))
			                    .filter(member -> member instanceof Alive)
			                    .findFirst()
			                    .orElse(null);
			if (target != null) {
				((Alive) target).getHealth().drain(10);
				scene.removeActor(this);
			}
		})).scheduleFor(this);
	}
}

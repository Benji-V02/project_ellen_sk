package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Ripley extends AbstractActor implements Movable {

	private final Animation anime;
	private final int SPEED;

	public Ripley() {
		super("Ellen");
		anime = new Animation("sprites/player.png",
			32, 32,
			.1f, Animation.PlayMode.LOOP_PINGPONG);
		setAnimation(anime);
		anime.pause();
		SPEED = 2;
	}


	@Override
	public int getSpeed() {
		return SPEED;
	}

	@Override
	public void startedMoving(Direction direction) {
		anime.setRotation(direction.getAngle());
		anime.play();
	}

	@Override
	public void stoppedMoving() {
		anime.pause();
	}


}

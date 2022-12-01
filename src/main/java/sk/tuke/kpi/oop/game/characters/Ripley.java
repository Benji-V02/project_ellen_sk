package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Ripley extends AbstractActor implements Movable {

	private final Animation anime;
	private final int SPEED;
	private int energy;
	private int ammo;

	public Ripley() {
		super("Ellen");
		anime = new Animation("sprites/player.png",
			32, 32,
			.1f, Animation.PlayMode.LOOP_PINGPONG);
		setAnimation(anime);
		anime.pause();
		SPEED = 2;
		energy = 100;
		ammo = 0;
	}


	@Override
	public int getSpeed() {
		return SPEED;
	}

	@Override
	public void startedMoving(Direction direction) {
		if (direction.equals(Direction.NONE)) return;
		anime.setRotation(direction.getAngle());
		anime.play();
	}

	@Override
	public void stoppedMoving() {
		anime.pause();
	}

	public void setEnergy(int energy) {
		if (energy < 0 || energy > 100) return;
		this.energy = energy;
	}

	public int getEnergy() {
		return this.energy;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		if (ammo > 500) return;
		this.ammo = ammo;
	}
}

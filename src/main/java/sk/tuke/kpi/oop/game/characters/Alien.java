package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {

	private final Health health;
	private final Behaviour<? super Alien> behaviour;
	private final int speed;

	public Alien(int healthValue, Behaviour<? super Alien> behaviour) {
		this.behaviour = behaviour;
		setAnimation(new Animation("sprites/alien.png", 32, 32, .1f, Animation.PlayMode.LOOP_PINGPONG));
		health = new Health(healthValue);
		health.onExhaustion(() -> getScene().removeActor(this));
		speed = 1;
	}

	public Alien() {
		this(100, new RandomlyMoving());
	}

	@Override
	public Health getHealth() {
		return health;
	}

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		if (behaviour != null) behaviour.setUp(this);
	}

	@Override
	public int getSpeed() {
		return speed;
	}
}

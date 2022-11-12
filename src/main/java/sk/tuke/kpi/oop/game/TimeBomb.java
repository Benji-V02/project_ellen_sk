package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

	private final float time;
	private boolean activated;
	private final Animation animeNotActive;
	private final Animation animeActive;
	private final Animation animeBlown;
	private static final float EXPLOSION_TIME = .05f;

	public TimeBomb(@NotNull float time) {
		this.time = time;
		this.activated = false;
		animeActive = new Animation("sprites/bomb_activated.png", 16, 16, .09f, Animation.PlayMode.LOOP_PINGPONG);
		animeNotActive = new Animation("sprites/bomb.png");
		animeBlown = new Animation("sprites/small_explosion.png", 32, 32, EXPLOSION_TIME, Animation.PlayMode.ONCE);
		setAnimation(animeNotActive);
	}


	public boolean isActivated() {
		return activated;
	}


	public void activate() {
		this.activated = true;
		setAnimation(animeActive);
		new ActionSequence<>(
			new Wait<>(this.time),
			new Invoke<>(() ->
			{
				setAnimation(animeBlown);
			}),
			new Wait<>(EXPLOSION_TIME),
			new Invoke<>(() ->
			{
				super.getScene().removeActor(this);
			})
		).scheduleFor(this);
	}
}

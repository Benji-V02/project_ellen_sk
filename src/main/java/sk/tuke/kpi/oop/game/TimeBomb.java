package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

	private final float time;
	private final Animation animeNotActive;
	private final Animation animeActive;

	public TimeBomb(float time) {
		this.time = time;
		animeActive = new Animation("sprites/bomb_activated.png", 16, 16, .09f, Animation.PlayMode.LOOP_PINGPONG);
		animeNotActive = new Animation("sprites/bomb.png");
		setAnimation(animeNotActive);
	}


}

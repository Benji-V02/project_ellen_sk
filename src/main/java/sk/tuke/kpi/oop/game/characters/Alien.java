package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Alien extends AbstractActor {

	public Alien() {
		setAnimation(new Animation("sprites/alien.png", 32, 32, .1f, Animation.PlayMode.LOOP_PINGPONG));
	}
}

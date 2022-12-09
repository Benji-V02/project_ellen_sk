package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviour.Behaviour;

public class AlienMother extends Alien {
	private final Health health;

	public AlienMother(Behaviour<? super Alien> behaviour) {
		super(behaviour);
		setAnimation(new Animation("sprites/mother.png", 112, 162, .2f, Animation.PlayMode.LOOP_PINGPONG));
		health = new Health(200);
	}

	@Override
	public Health getHealth() {
		return health;
	}
}

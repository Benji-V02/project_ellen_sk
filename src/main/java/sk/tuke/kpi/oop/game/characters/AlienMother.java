package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class AlienMother extends Alien {
	private final Health health;

	public AlienMother() {
		setAnimation(new Animation("sprites/mother.png", 112, 162, .2f, Animation.PlayMode.LOOP_PINGPONG));
		health = new Health(200);
	}

	@Override
	public Health getHealth() {
		return health;
	}
}

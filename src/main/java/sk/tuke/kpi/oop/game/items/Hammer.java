package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> implements Collectible {

	public Hammer() {
		super(1);
		setAnimation(new Animation("sprites/hammer.png"));
	}

	public Hammer(int remainingUses) {
		super(remainingUses);
		setAnimation(new Animation("sprites/hammer.png"));
	}

	public int getUses() {
		return super.getRemainingUses();
	}


	@Override
	public void useWith(Reactor actor) {
		if (actor == null) return;
		if (actor.repair()) super.use();
	}

	@Override
	public Class<Reactor> getUsingActorClass() {
		return Reactor.class;
	}
}

package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class FireExtinguisher extends BreakableTool<Reactor> {


	public FireExtinguisher() {
		super(1);
		new Animation("sprites/extinguisher.png").play();
	}

	@Override
	public void useWith(Reactor actor) {
		if (actor == null) return;
		if (actor.extinguish()) super.use();
	}
}

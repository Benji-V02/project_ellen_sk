package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> {

	public Hammer(){
		super(1);
		new Animation("sprites/hammer.png").play();
	}

	public Hammer(int remainingUses) {
		super(remainingUses);
		new Animation("sprites/hammer.png").play();
	}

	public int getUses() {
		return super.getRemainingUses();
	}


	@Override
	public void useWith(Reactor actor) {
		if(actor == null) return;
		if(actor.repair()) super.use();
	}
}

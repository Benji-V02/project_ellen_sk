package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool<DefectiveLight>{

	public Wrench(){
		super(2);
		setAnimation(new Animation("sprites/wrench.png"));
	}
	@Override
	public void useWith(DefectiveLight actor) {
		if(actor == null) return;
		if(actor.repair()) super.use();
	}
}

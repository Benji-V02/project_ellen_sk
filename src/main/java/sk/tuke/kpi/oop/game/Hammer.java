package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends BreakableTool {

	public Hammer(){
		super(1, new Animation("sprites/hammer.png"));
	}

	public Hammer(int remainingUses){
		super(remainingUses, new Animation("sprites/hammer.png"));
	}

	public int getUses() {
		return super.getRemainingUses();
	}



}

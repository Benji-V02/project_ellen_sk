package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.Usable;

public abstract class BreakableTool extends AbstractActor implements Usable {

	private int remainingUses;


	public BreakableTool(int remainingUses, Animation animation){
		this.remainingUses = remainingUses;
		Animation anime = animation;
		setAnimation(anime);
	}

	public void use(){
		remainingUses--;
		if(remainingUses == 0){
			this.getScene().removeActor(this);
		}
	}

	public int getRemainingUses(){
		return this.remainingUses;
	}
}

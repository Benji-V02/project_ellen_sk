package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {

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

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {

	private Animation anime;
	private int uses;

	public Hammer(){
		anime = new Animation("sprites/hammer.png");
		setAnimation(anime);
		uses = 1;
	}

	public int getUses() {
		return uses;
	}


	public void use(){
		this.uses--;
		if(getUses() == 0) super.getScene().removeActor(this);
	}


}

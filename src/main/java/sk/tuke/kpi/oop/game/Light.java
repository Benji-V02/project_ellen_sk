package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {

	private Animation animeOff;
	private Animation animeOn;
	private boolean isActive;
	private boolean electricity;

	public Light(){
		animeOn = new Animation("sprites/light_on.png");
		animeOff = new Animation("sprites/light_off.png");
		setAnimation(animeOff);
		this.isActive = false;
		electricity = false;
	}

	public void toggle(){
		this.isActive = !this.isActive;
		updateAnimation();
	}

	public boolean isActiveState(){return this.isActive;}


	private void updateAnimation(){
		if(isActive && electricity)  setAnimation(animeOn);
		else setAnimation(animeOff);
	}


	public void setElectricityFlow(boolean electricity){
		this.electricity = electricity;
		updateAnimation();
	}

	public boolean isElectricityFlow(){return this.electricity;}


	public Animation getAnime(int index){
		if(index == 1) return animeOn;
		return animeOff;
	}

}

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer{

	private boolean electricity;
	private Animation anime;
	public Computer(){
		this.electricity = false;
		anime = new Animation("sprites/computer.png", 80, 48, .2f, Animation.PlayMode.LOOP_PINGPONG);
		setAnimation(anime);
		updateAnimation();
	}

	public long add(int a, int b){
		if(electricity)return a + b;
		else return 0;
	}

	public double add(float a, float b){
		if(electricity)return a + b;
		else return 0f;
	}

	public long sub(int a, int b){
		if(electricity)return a - b;
		else return 0;
	}

	public double sub(float a, float b){
		if(electricity)return a - b;
		else return 0f;
	}

	@Override
	public void setPowered(boolean electricity) {
		this.electricity = electricity;
		updateAnimation();
	}

	private void updateAnimation(){
		if(this.electricity)
			this.anime.play();
		else
			this.anime.pause();
	}
}

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor {



	public Computer(){
		Animation anime = new Animation("sprites/computer.png", 80, 48, .2f, Animation.PlayMode.LOOP_PINGPONG);
		setAnimation(anime);
	}

	public long add(int a, int b){
		return a + b;
	}

	public double add(float a, float b){
		return a + b;
	}

	public long sub(int a, int b){
		return a - b;
	}

	public double sub(float a, float b){
		return a - b;
	}
}

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor {

	private Animation anime;
	private Reactor reactor;
	private boolean on;

	public Cooler(Reactor reactor){
		anime = new Animation("sprites/fan.png", 32, 32, .3f);
		setAnimation(anime);
		anime.pause();
		on = false;

		this.reactor = reactor;
	}


	private void coolReactor(){
		if(this.isOn()) reactor.decreaseTemperature(1);
	}


	public void turnOn(){on = true; updateAnimation();}

	public void turnOff(){on = false; updateAnimation();}

	public boolean isOn(){return on;}


	public void addedToScene(Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);

	}

	private void updateAnimation(){
		if(isOn()) anime.play();
		else anime.pause();
	}
}

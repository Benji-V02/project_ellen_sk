package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Controller extends AbstractActor {
	private Reactor reactor;
	private Animation anime;

	public Controller(Reactor reactor){
		this.reactor = reactor;
		this.anime = new Animation("sprites/switch.png");
		setAnimation(this.anime);
	}

	void toggle(){
		if(reactor.isRunning()) reactor.turnOff();
		else reactor.turnOn();
	}
}

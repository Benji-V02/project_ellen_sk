package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Controller extends AbstractActor {
	private Reactor reactor;

	public Controller(Reactor reactor){
		this.reactor = reactor;
		Animation anime = new Animation("sprites/switch.png");
		setAnimation(anime);
	}

	void toggle(){
		if(reactor.isRunning()) reactor.turnOff();
		else reactor.turnOn();
	}
}

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch<A extends Switchable> extends AbstractActor {
	private A reactor;

	public PowerSwitch(A reactor){
		this.reactor = reactor;
		Animation anime = new Animation("sprites/switch.png");
		setAnimation(anime);
	}

	void toggle(){
		if(reactor.isOn()) {
			switchOff();
		}
		else {
			this.switchOn();
		}
	}


	public A getDevice(){return this.reactor;}


	public void switchOn(){
		reactor.turnOn();
		getAnimation().setTint(Color.WHITE);
	}


	public void switchOff(){
		reactor.turnOff();
		getAnimation().setTint(Color.GRAY);
	}
}

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
			reactor.turnOff();
			getAnimation().setTint(Color.GRAY);
		}
		else {
			reactor.turnOn();
			getAnimation().setTint(Color.WHITE);
		}
	}
}

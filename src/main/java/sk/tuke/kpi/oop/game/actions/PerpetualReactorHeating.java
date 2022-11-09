package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;



public class PerpetualReactorHeating extends AbstractAction<Reactor> {

	private int temp;

	public PerpetualReactorHeating(int temperature){
		temp = temperature;
	}

	public PerpetualReactorHeating(){
		this(1);
	}

	public void execute(float deltaTime){
		Reactor reactor = super.getActor();
		if(reactor != null) reactor.increaseTemperature(this.temp);
	}

}

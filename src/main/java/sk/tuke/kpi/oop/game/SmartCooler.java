package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler {

	public SmartCooler(Reactor reactor) {
		super(reactor);
	}


	private void smart() {
		if (getReactor().getTemperature() > 2500 && !super.isOn()) super.turnOn();
		if (getReactor().getTemperature() < 1500 && super.isOn()) super.turnOff();
	}

	@Override
	public void addedToScene(Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<>(this::smart)).scheduleFor(this);
	}
}

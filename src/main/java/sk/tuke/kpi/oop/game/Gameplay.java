package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;

import java.util.Map;

public class Gameplay extends Scenario {

	public void setupPlay(Scene scene) {
		Map<String, MapMarker> markers = scene.getMap().getMarkers();
		MapMarker reactorPos = markers.get("reactor-area-1");
		Reactor reactor = new Reactor();
		scene.addActor(reactor, reactorPos.getPosX(), reactorPos.getPosY());
		reactor.turnOn();

		MapMarker coolerPos = markers.get("cooler-area-1");
		Cooler cooler = new Cooler(reactor);
		scene.addActor(cooler, coolerPos.getPosX(), coolerPos.getPosX());

		new ActionSequence<>(
			new Wait<>(5),
			new Invoke<>(cooler::turnOn)
		).scheduleFor(cooler);


		PowerSwitch<Reactor> pSwitch = new PowerSwitch<>(reactor);
		scene.addActor(pSwitch, 60, 60);
	}
}

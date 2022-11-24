package sk.tuke.kpi.oop.game.scenarios;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.ChainBomb;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.PowerSwitch;
import sk.tuke.kpi.oop.game.Reactor;

import java.util.Map;

public class TrainingGameplay extends Scenario {

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

		ChainBomb bomb1 = new ChainBomb(3f);
		ChainBomb bomb2 = new ChainBomb(3f);
		ChainBomb bomb3 = new ChainBomb(3f);
		scene.addActor(bomb1, 150, 150);
		scene.addActor(bomb2, 200, 95);
		scene.addActor(bomb3, 125, 70);
		bomb1.activate();
	}
}

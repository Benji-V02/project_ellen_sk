package sk.tuke.kpi.oop.game.spawner;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.spawner.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class Spawner extends AbstractActor {
	private final List<Observer> observers = new ArrayList<>();

	public void attach(Observer observer) {
		observers.add(observer);
	}

	public void updateObservers() {
		observers.stream()
		         .forEach((observer -> observer.update()));
	}


	public int spawnWarriors(int maxSpawn) {
		return 0;
	}
}

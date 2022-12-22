package sk.tuke.kpi.oop.game.spawner.observers;

import sk.tuke.kpi.oop.game.spawner.Spawner;

public class SpawningObserver extends Observer {

	private int remainingSpawns;

	public SpawningObserver(Spawner spawner) {
		this.spawner = spawner;
		this.spawner.attach(this);
		remainingSpawns = 10;
	}


	@Override
	public void update() {
		if (remainingSpawns <= 0) return;
		remainingSpawns -= spawner.spawnWarriors(remainingSpawns > 3 ? 3 : remainingSpawns);
		System.out.println(remainingSpawns);
	}
}

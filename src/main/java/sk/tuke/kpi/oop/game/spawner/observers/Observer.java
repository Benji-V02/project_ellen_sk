package sk.tuke.kpi.oop.game.spawner.observers;

import sk.tuke.kpi.oop.game.spawner.Spawner;

public abstract class Observer {
	protected Spawner spawner;

	public abstract void update();
}

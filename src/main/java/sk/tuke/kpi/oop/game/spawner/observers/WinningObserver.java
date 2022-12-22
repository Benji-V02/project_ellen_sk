package sk.tuke.kpi.oop.game.spawner.observers;

public class WinningObserver extends Observer {
	private int enemiesRemaining;

	public WinningObserver() {
		this.enemiesRemaining = 10;
	}

	@Override
	public void update() {
		enemiesRemaining--;
		if (enemiesRemaining == 0)
			spawner.winning();
	}
}

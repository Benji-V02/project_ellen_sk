package sk.tuke.kpi.oop.game.characters;

public class Health {

	private int value;
	private final int maxHealth;

	public Health(int initHealth, int maxHealth) {
		this.value = initHealth;
		this.maxHealth = maxHealth;
	}

	public Health(int maxHealth) {
		this(maxHealth, maxHealth);
	}

	public int getValue() {
		return value;
	}

	public void restore() {
		value = maxHealth;
	}


	public void refill(int amount) {
		if (amount < 0) return;
		value += amount;
		if (value > maxHealth) restore();
	}

	public void exhaust() {
		value = 0;
	}

	public void drain(int amount) {
		if (amount < 0) return;
		value -= amount;
		if (value < 0) exhaust();
	}

}

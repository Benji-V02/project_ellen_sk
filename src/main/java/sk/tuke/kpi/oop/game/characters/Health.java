package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {

	@FunctionalInterface
	public interface ExhaustionEffect {
		void apply();
	}


	private int value;
	private final int maxHealth;
	private final List<ExhaustionEffect> effects;

	public Health(int initHealth, int maxHealth) {
		this.value = initHealth;
		this.maxHealth = maxHealth;
		effects = new ArrayList<>();
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
		onDeath();
	}

	public void drain(int amount) {
		if (amount < 0) return;
		value -= amount;
		if (value <= 0) exhaust();
	}

	public void onExhaustion(ExhaustionEffect effect) {
		effects.add(effect);
	}

	private void onDeath() {
		for (ExhaustionEffect effect : effects) {
			effect.apply();
		}
		effects.removeAll(effects);
	}

}

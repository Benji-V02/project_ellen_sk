package sk.tuke.kpi.oop.game.spawner;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.characters.warrior.*;
import sk.tuke.kpi.oop.game.spawner.observers.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner extends AbstractActor {
	private final List<Observer> observers = new ArrayList<>();
	public static final Topic<Spawner> WIN = Topic.create("empty spawner", Spawner.class);


	public Spawner() {
		setAnimation(new Animation("sprites/lift.png"));

	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	public void updateObservers() {
		observers.stream()
		         .forEach((observer -> observer.update()));
	}


	public int spawnWarriors(int maxSpawn) {
		int newWarriors = new Random().nextInt(maxSpawn);
		for (int i = 0; i <= newWarriors; i++) {
			Warrior newWarrior = new SimpleWarrior();
			double randomNum = Math.random();
			if (randomNum < .3)
				newWarrior = new Armored(newWarrior);
			else if (randomNum < .6)
				newWarrior = new Faster(newWarrior);
			else if (randomNum < .9)
				newWarrior = new Faster(new Armored(newWarrior));
			getScene().addActor(newWarrior, getPosX(), getPosY());
		}
		return newWarriors + 1;
	}

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		updateObservers();
		scene.getMessageBus().subscribe(SimpleWarrior.WARRIOR_DIED, (Warrior) -> {
			this.updateObservers();
		});
		scene.getMessageBus().subscribe(AbstractWarrior.WARRIOR_DIED, (Warrior) -> {
			this.updateObservers();
		});
	}

	public void winning() {
		getScene().getMessageBus().publish(WIN, this);
	}
}

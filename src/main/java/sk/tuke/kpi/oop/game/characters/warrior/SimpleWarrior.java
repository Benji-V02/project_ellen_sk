package sk.tuke.kpi.oop.game.characters.warrior;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Health;
import sk.tuke.kpi.oop.game.items.Energy;

public class SimpleWarrior extends AbstractActor implements Movable, Alive, Enemy, Warrior {

	private Health health;
	private final Behaviour<? super SimpleWarrior> behaviour;
	public static final Topic<Warrior> WARRIOR_DIED = Topic.create("warrior was killed", Warrior.class);

	public SimpleWarrior() {
		setHealth();
		setAnimation(new Animation("sprites/solger/solger.png"));
		behaviour = new RandomlyMoving();
	}

	public void setHealth() {
		this.health = new Health(getHealthModifier());
	}

	@Override
	public int getSpeed() {
		return 2;
	}

	@Override
	public int getHealthModifier() {
		return 100;
	}

	@Override
	public void startedMoving(Direction direction) {
		Movable.super.startedMoving(direction);
	}

	@Override
	public Health getHealth() {
		return health;
	}

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);

		new While<>(action -> true,
			new ActionSequence<>(
				new Invoke<>(this::aliveActor),
				new Wait<>(0)
			)).scheduleFor(this);
		getHealth().onExhaustion(() -> {
			scene.getMessageBus().publish(WARRIOR_DIED, this);
			scene.addActor(new Energy(), getPosX(), getPosY());
			scene.removeActor(this);
		});
		if (behaviour != null) {
			behaviour.setUp(this);
		}

	}

	public void aliveActor() {
		Scene scene = getScene();
		for (Actor actor : scene.getActors()) {
			if (this.intersects(actor) && (actor instanceof Alive) && !(actor instanceof Enemy)) {
				((Alive) actor).getHealth().drain(1);
			}
		}
	}

}

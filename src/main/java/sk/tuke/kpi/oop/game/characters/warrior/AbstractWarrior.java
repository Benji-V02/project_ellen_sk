package sk.tuke.kpi.oop.game.characters.warrior;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Health;
import sk.tuke.kpi.oop.game.items.Ammo;

public abstract class AbstractWarrior extends AbstractActor implements Warrior, Movable, Alive, Enemy {

	protected Warrior mutation;
	private final Health health;
	private final Behaviour<? super AbstractWarrior> behaviour;

	public static final Topic<Warrior> WARRIOR_DIED = Topic.create("warrior was killed", Warrior.class);

	public AbstractWarrior(Warrior mutation) {
		behaviour = new RandomlyMoving();
		this.mutation = mutation;
		health = new Health(getHealthModifier());
	}

	@Override
	public int getSpeed() {
		return mutation.getSpeed();
	}

	public int getHealthModifier() {
		if (mutation == null) return 0;
		return mutation.getHealthModifier();
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
			scene.addActor(new Ammo(), this.getPosX(), this.getPosY());
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

	@Override
	public Health getHealth() {
		return health;
	}
}

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.spawner.Spawner;
import sk.tuke.kpi.oop.game.spawner.observers.SpawningObserver;

import java.util.Map;

public class CustomLevel implements SceneListener {

	private Ripley player;
	private int remainingSpawns;
	private Disposable moves;
	private Disposable keeping;
	private Disposable shooting;
	private boolean playerDied;

	public static class Factory implements ActorFactory {

		private final Map<String, Actor> objects;

		public Factory() {
			objects = Map.of(
				"door", new Door(),
				"vertical_door", new Door("Door", Door.Orientation.VERTICAL),
				"ellen", new Ripley()
			);
		}

		@Override
		public @Nullable Actor create(@Nullable String type, @Nullable String name) {
			switch (name) {
				case "door":
					return new Door();
				case "vertical-door":
					return new Door(name, Door.Orientation.VERTICAL);
				case "ellen":
					return new Ripley();
				case "spawner":
					Spawner spawner = new Spawner();
					new SpawningObserver(spawner);
					return spawner;
				default:
					return null;
			}
		}
	}

	public CustomLevel() {
		remainingSpawns = 2;
		playerDied = false;
	}

	@Override
	public void sceneInitialized(@NotNull Scene scene) {
		player = scene.getFirstActorByType(Ripley.class);
		//scene.addActor(new Armored(new SimpleWarrior()), player.getPosX(), player.getPosY());

		moves = scene.getInput().registerListener(new MovableController(player));
		keeping = scene.getInput().registerListener(new KeeperController(player));
		shooting = scene.getInput().registerListener(new ShooterController(player));

		scene.follow(player);
		scene.getGame().pushActorContainer(player.getBackpack());

		scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> {
			shooting.dispose();
			keeping.dispose();
			moves.dispose();
			System.out.println("GAME OVER!");
			playerDied = true;
		});
		scene.getMessageBus().subscribe(Spawner.WIN, Spawner -> {
			remainingSpawns--;

		});
	}

	public void sceneUpdating(@NotNull Scene scene) {
		assert player != null;
		player.showRipleyState();
		//scene.getFirstActorByType(Actor.class).showRipleyState();
		if (remainingSpawns == 0) {
			shooting.dispose();
			keeping.dispose();
			moves.dispose();
			scene.getOverlay().drawText("WIN!", player.getPosX(), player.getPosY());
		}
		if (playerDied)
			scene.getOverlay().drawText("GAME OVER!", player.getPosX(), player.getPosY());

	}
}

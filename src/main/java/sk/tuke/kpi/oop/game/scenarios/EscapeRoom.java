package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;

public class EscapeRoom implements SceneListener {

	private Ripley player;


	public static class Factory implements ActorFactory {


		@Override
		public @Nullable sk.tuke.kpi.gamelib.Actor create(@Nullable String type, @Nullable String name) {
			switch (name) {
				case "ellen":
					return new Ripley();
				case "energy":
					return new Energy();
				case "door":
					return new Door();
				case "alien":
					return new Alien(100, new RandomlyMoving());
				case "ammo":
					return new Ammo();
				default:
					return null;
			}
		}
	}


	@Override
	public void sceneInitialized(@NotNull Scene scene) {
		player = scene.getFirstActorByType(Ripley.class);

		scene.getInput().registerListener(new MovableController(player));
		scene.getInput().registerListener(new KeeperController(player));

		scene.follow(player);
		scene.getGame().pushActorContainer(player.getBackpack());
		player.getBackpack().add(new Hammer());
		player.getBackpack().add(new FireExtinguisher());
		player.getBackpack().add(new Wrench());
	}

	//@Override
	public void sceneUpdating(@NotNull Scene scene) {
		assert player != null;
		player.showRipleyState();
		//scene.getFirstActorByType(Actor.class).showRipleyState();

	}
}

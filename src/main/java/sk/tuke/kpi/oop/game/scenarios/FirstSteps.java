package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.characters.Actor;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.FireExtinguisher;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Wrench;

public class FirstSteps implements SceneListener {

	@Override
	public void sceneInitialized(@NotNull Scene scene) {
		SceneListener.super.sceneInitialized(scene);

		Reactor reactor = new Reactor();
		scene.addActor(reactor);

		Actor player = new Actor();

		scene.getInput().registerListener(new MovableController(player));
		scene.getInput().registerListener(new KeeperController(player));
		scene.addActor(player);

		scene.follow(player);
		scene.getGame().pushActorContainer(player.getBackpack());
		player.getBackpack().add(new Hammer());
		player.getBackpack().add(new FireExtinguisher());
		player.getBackpack().add(new Wrench());

	}
}

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.MovableController;

public class FirstSteps implements SceneListener {

	@Override
	public void sceneInitialized(@NotNull Scene scene) {
		SceneListener.super.sceneInitialized(scene);

		Reactor reactor = new Reactor();
		scene.addActor(reactor);

		Ripley player = new Ripley();

		scene.getInput().registerListener(new MovableController(player));
		scene.addActor(player);

	}
}

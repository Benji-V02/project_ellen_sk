package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;


public class ChainBomb extends TimeBomb {
	public ChainBomb(@NotNull float time) {
		super(time);
	}

	public void removedFromScene(Scene scene) {
		for (Actor bomb : scene.getActors()) {
			if (bomb.getClass() != ChainBomb.class) continue;

		}
	}
}

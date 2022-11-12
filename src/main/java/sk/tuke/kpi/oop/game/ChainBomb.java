package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

import java.awt.geom.Ellipse2D;


public class ChainBomb extends TimeBomb {

	public ChainBomb(@NotNull float time) {
		super(time);
	}

	public void removedFromScene(Scene scene) {
		Ellipse2D round = new Ellipse2D.Float(this.getPosX() + 8,
			this.getPosY() + 8, 50f, 50f);
		for (Actor bomb : scene.getActors()) {
			if (!(bomb instanceof ChainBomb)) continue;
			if (round.contains(bomb.getPosX(), bomb.getPosY(), 16, 16))
				((ChainBomb) bomb).activate();
		}
	}
}

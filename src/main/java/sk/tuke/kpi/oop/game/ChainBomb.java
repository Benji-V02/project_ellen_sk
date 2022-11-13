package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

import java.awt.geom.Ellipse2D;


public class ChainBomb extends TimeBomb {

	public ChainBomb(@NotNull float time) {
		super(time);
	}

	@Override
	public void removedFromScene(Scene scene) {
		super.removedFromScene(scene);
		Ellipse2D round = new Ellipse2D.Float(this.getPosX() - 42,
			this.getPosY() - 42, 108f, 108f);
		for (Actor bomb : scene.getActors()) {
			if (bomb instanceof ChainBomb && round.intersects(bomb.getPosX(), bomb.getPosY(), bomb.getWidth(), bomb.getHeight())) {
				//System.out.println(round.intersects(bomb.getPosX(), bomb.getPosY(), 16, 16));
				((ChainBomb) bomb).activate();
			}
		}
	}


}

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
		Ellipse2D round = new Ellipse2D.Float(this.getPosX() - 48,
			this.getPosY() - 48, 120f, 120f);
		for (Actor bomb : scene.getActors()) {
			if (bomb instanceof ChainBomb && round.intersects(bomb.getPosX(), bomb.getPosY(), bomb.getWidth() * 2, bomb.getHeight() * 2)) {
				//System.out.println(round.intersects(bomb.getPosX(), bomb.getPosY(), 16, 16));
				((ChainBomb) bomb).activate();
			}
		}
	}


}

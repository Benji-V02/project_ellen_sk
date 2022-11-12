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
		Ellipse2D round = new Ellipse2D.Float(this.getPosX() + 8,
			this.getPosY() + 8, 500f, 500f);
		for (Actor bomb : scene.getActors()) {
			if (bomb instanceof ChainBomb) {
				//System.out.println(round.intersects(bomb.getPosX(), bomb.getPosY(), 160, 160));
				if (round.intersects(bomb.getPosX(), bomb.getPosY(), 160, 160))
					((ChainBomb) bomb).activate();
			}
		}
	}
}

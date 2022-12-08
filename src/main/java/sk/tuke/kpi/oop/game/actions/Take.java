package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Take<K extends Keeper> extends AbstractAction<K> {



	@Override
	public void execute(float deltaTime) {
		if (getActor() == null) {
			setDone(true);
			return;
		}
		Scene scene = getActor().getScene();
		for (Actor object : scene.getActors()) {
			if (object instanceof Collectible && object.intersects(getActor())) {
				try {
					getActor().getBackpack().add((Collectible) object);
					scene.removeActor(object);
				} catch (IllegalStateException ex) {
					scene.getOverlay().drawText(ex.getMessage(), 2, 2).showFor(2);
				} finally {
					setDone(true);
				}
			}
		}
	}


}

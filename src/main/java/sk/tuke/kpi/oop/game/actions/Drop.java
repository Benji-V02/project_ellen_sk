package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Drop<K extends Keeper> extends AbstractAction<K> {

	public Drop() {
	}

	@Override
	public boolean isDone() {
		return true;
	}

	@Override
	public void execute(float deltaTime) {
		if (getActor() == null || getActor().getBackpack().peek() == null) return;
		getActor().getScene().addActor(
			getActor().getBackpack().peek(),
			getActor().getPosX() - (getActor().getWidth() / 2) - getActor().getBackpack().peek().getPosX() + (getActor().getBackpack().peek().getWidth() / 2),
			getActor().getPosY() - (getActor().getHeight() / 2) + getActor().getBackpack().peek().getPosY() - (getActor().getBackpack().peek().getHeight() / 2)
		);
		getActor().getBackpack().remove(getActor().getBackpack().peek());
	}
}

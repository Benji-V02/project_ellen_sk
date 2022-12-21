package sk.tuke.kpi.oop.game.characters.warrior;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class AbstractWarrior extends AbstractActor implements Mutable {

	private Mutable mutation;


	public void setMutation(Mutable mutation) {
		if (this.mutation == null)
			this.mutation = mutation;
		else
			this.mutation.setMutation(mutation);
	}


}

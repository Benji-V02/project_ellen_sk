package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;


public class Use<A extends Actor> extends AbstractAction<A> {

	private final Usable<A> usable;


	public Use(Usable<A> usable) {this.usable = usable;}


	public Disposable scheduleForIntersectingWith(Actor mediatingActor) {
		Scene scene = mediatingActor.getScene();
		if (scene == null) return null;
		Class<A> usingActorClass = usable.getUsingActorClass();  // `usable` je spominana clenska premenna

		for (Actor usable : scene) {
			if (mediatingActor.intersects(usable) && usingActorClass.isInstance(usable)) {
				return this.scheduleFor(usingActorClass.cast(usable));  // naplanovanie akcie v pripade, ze sa nasiel vhodny akter
			}
		}
		return null;
	}


	@Override
	public void execute(float deltaTime) {

	}
}

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.openables.Door;

import java.util.Map;

public class CustomLevel implements SceneListener {

	public static class Factory implements ActorFactory {

		private final Map<String, Actor> objects;

		public Factory() {
			objects = Map.of(
				"door", new Door(),
				"vertical_door", new Door("Door", Door.Orientation.VERTICAL),
				"ellen", new Ripley()
			);
		}

		@Override
		public @Nullable Actor create(@Nullable String type, @Nullable String name) {
			return objects.get(name);
		}
	}

}

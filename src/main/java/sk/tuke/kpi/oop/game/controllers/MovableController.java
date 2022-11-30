package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

public class MovableController implements KeyboardListener {

	private final Movable actor;
	private final Map<Input.Key, Direction> keyDirectionMap;
	private Move<Movable> lastMove;

	public MovableController(Movable actor) {
		this.actor = actor;
		keyDirectionMap = Map.ofEntries(
			Map.entry(Input.Key.UP, Direction.NORTH),
			Map.entry(Input.Key.W, Direction.NORTH),
			Map.entry(Input.Key.DOWN, Direction.SOUTH),
			Map.entry(Input.Key.S, Direction.SOUTH),
			Map.entry(Input.Key.LEFT, Direction.WEST),
			Map.entry(Input.Key.A, Direction.WEST),
			Map.entry(Input.Key.RIGHT, Direction.EAST),
			Map.entry(Input.Key.D, Direction.EAST)
		);

		lastMove = new Move<>(Direction.NONE);
		lastMove.setActor(this.actor);
	}


	private void newDirection(Direction newDirection, float duration) {
		lastMove.stop();
		lastMove = new Move<>(newDirection, duration);
		lastMove.setActor(actor);
		lastMove.scheduleFor(actor);
		//actor.startedMoving(newDirection);
	}


	@Override
	public void keyPressed(Input.@NotNull Key key) {
		KeyboardListener.super.keyPressed(key);
		if (keyDirectionMap.containsKey(key))
			newDirection(keyDirectionMap.get(key), Float.MAX_VALUE);
	}


	@Override
	public void keyReleased(Input.@NotNull Key key) {
		KeyboardListener.super.keyPressed(key);
		if (keyDirectionMap.containsKey(key))
			newDirection(Direction.NONE, 0f);
	}

}

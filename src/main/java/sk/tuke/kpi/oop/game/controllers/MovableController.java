package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

public class MovableController implements KeyboardListener {

	private final Movable actor;
	private final Map<Input.Key, Direction> keyDirectionMap;
	private final Map<Direction, Direction> oppositeDirection;
	private Move<Movable> lastMove;
	private Direction direction;

	public MovableController(Movable actor) {
		this.actor = actor;
		keyDirectionMap = Map.ofEntries(
			Map.entry(Input.Key.UP, Direction.NORTH),
			//Map.entry(Input.Key.W, Direction.NORTH),
			Map.entry(Input.Key.DOWN, Direction.SOUTH),
			//Map.entry(Input.Key.S, Direction.SOUTH),
			Map.entry(Input.Key.LEFT, Direction.WEST),
			//Map.entry(Input.Key.A, Direction.WEST),
			Map.entry(Input.Key.RIGHT, Direction.EAST)//,
			//Map.entry(Input.Key.D, Direction.EAST)
		);

		oppositeDirection = Map.ofEntries(
			Map.entry(Direction.NORTH, Direction.SOUTH),
			Map.entry(Direction.SOUTH, Direction.NORTH),
			Map.entry(Direction.WEST, Direction.EAST),
			Map.entry(Direction.EAST, Direction.WEST)
		);
		lastMove = new Move<>(Direction.NONE);
		lastMove.setActor(this.actor);
		direction = Direction.NONE;
	}


	private void newDirection(float duration) {
		if (actor == null) return;
		lastMove.stop();
		lastMove = new Move<>(direction, (direction == Direction.NONE) ? 0f : duration);
		lastMove.setActor(actor);
		lastMove.scheduleFor(actor);
		//actor.startedMoving(newDirection);
	}


	@Override
	public void keyPressed(Input.Key key) {
		KeyboardListener.super.keyPressed(key);

		if (keyDirectionMap.containsKey(key)) {
			direction = direction.combine(keyDirectionMap.get(key));
			newDirection(Float.MAX_VALUE);
		}
	}


	@Override
	public void keyReleased(Input.Key key) {
		KeyboardListener.super.keyPressed(key);
		if (keyDirectionMap.containsKey(key)) {
			direction = direction.combine(oppositeDirection.get(keyDirectionMap.get(key)));
			newDirection(Float.MAX_VALUE);
		}
	}

}

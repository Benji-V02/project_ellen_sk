package sk.tuke.kpi.oop.game;

public enum Direction {
	NONE(0, 0),
	NORTH(0, 1),
	NORTH_EAST(1, 1),
	EAST(1, 0),
	SOUTH_EAST(1, -1),
	SOUTH(0, -1),
	SOUTH_WEST(-1, -1),
	WEST(-1, 0),
	NORTH_WEST(-1, 1);
	private final int dx;
	private final int dy;

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public float getAngle() {
		//return (float) (Math.tan(dy/dx));
		switch (this) {
			case NONE:

			case NORTH:
				return 0f;
			case NORTH_EAST:
				return 305f;
			case NORTH_WEST:
				return 45f;
			case SOUTH:
				return 180f;
			case SOUTH_EAST:
				return 215f;
			case SOUTH_WEST:
				return 135f;
			case WEST:
				return 90f;
			case EAST:
				return 260f;
		}
		return 0f;
	}

}

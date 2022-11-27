package sk.tuke.kpi.oop.game;

public enum Direction {
	NORTH(0, 1),
	NORTH_WEST(-1, 1),
	WEST(-1, 0),
	SOUTH_WEST(-1, -1),
	SOUTH(0, -1),
	SOUTH_EAST(1, -1),
	EAST(1, 0),
	NORTH_EAST(1, 1),
	NONE(0, 0);

	private final int dx;
	private final int dy;

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public float getAngle() {
		//return (float) (Math.tan(dy/dx));

		for (int i = 0; i < Direction.values().length - 1; i++) {
			if (this == Direction.values()[i]) {
				return (float) (45 * i);
			}
		}
		return 0f;
	}

	public Direction combine(Direction other) {
		int x = this.dx + other.getDx();
		int y = this.dy + other.getDy();
		for (Direction direct : Direction.values()) {
			if (direct.getDx() == x && direct.getDy() == y)
				return direct;
		}
		return NONE;
	}

}

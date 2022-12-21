package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {

	private final Animation anime;
	private final int SPEED;
	private final Health health;
	private int ammo;
	private final Backpack backpack;

	public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);
	private Firearm firearm;

	public Ripley() {
		super("Ellen");
		anime = new Animation("sprites/player.png",
			32, 32,
			.1f, Animation.PlayMode.LOOP_PINGPONG);
		setAnimation(anime);
		anime.pause();
		SPEED = 2;
		ammo = 0;
		backpack = new Backpack("Actor's backpack", 10);
		health = new Health(100);
		health.onExhaustion(this::die);
		firearm = new Gun(500, 500);
	}


	@Override
	public int getSpeed() {
		return SPEED;
	}

	@Override
	public void startedMoving(Direction direction) {
		if (direction.equals(Direction.NONE)) return;
		anime.setRotation(direction.getAngle());
		anime.play();
	}

	@Override
	public void stoppedMoving() {
		anime.pause();
	}

	@Override
	public void collidedWithWall() {
		Movable.super.collidedWithWall();
	}


	private void die() {
		getScene().getMessageBus().publish(RIPLEY_DIED, this);
		setAnimation(new Animation("sprites/player_die.png", 32, 32, .1f, Animation.PlayMode.ONCE));
	}


	/*public void setEnergy(int energy) {
		if (energy < 0 || energy > 100) return;
		this.energy = energy;
		if (this.energy == 0) die();
	}

	public int getEnergy() {
		return health.getValue();
	}*/

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		if (ammo > 500) return;
		this.ammo = ammo;
	}

	@Override
	public Backpack getBackpack() {
		return backpack;
	}

	public void showRipleyState() {
		int windowHeight = getScene().getGame().getWindowSetup().getHeight();
		getScene().getGame().getOverlay().drawText(
			String.format("Energy: %d", health.getValue()),
			15,
			windowHeight - 2 * GameApplication.STATUS_LINE_OFFSET
		);
		getScene().getGame().getOverlay().drawText(
			String.format("Ammo: %d", firearm.getAmmo()),
			15,
			windowHeight - 3 * GameApplication.STATUS_LINE_OFFSET
		);
	}

	@Override
	public Health getHealth() {
		return health;
	}

	@Override
	public Firearm getFirearm() {
		return firearm;
	}

	@Override
	public void setFirearm(Firearm firearm) {
		this.firearm = firearm;
	}
}

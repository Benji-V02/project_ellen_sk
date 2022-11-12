package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;

public class Helicopter extends AbstractActor {

	private final Animation anime;

	public Helicopter() {
		anime = new Animation("sprites/heli.png", 64, 64, .005f);
		setAnimation(anime);
	}


	private void armageddon() {
		Player player = (Player) super.getScene().getFirstActorByName("Player");
		if (player == null) return;
		Point pos = player.getPosition();
		this.setPosition(pos.getX(), pos.getY());
		if (this.intersects(player)) player.setEnergy(player.getEnergy() - 1);

	}

	public void searchAndDestroy() {
		new Loop<>(new Invoke<>(this::armageddon)).scheduleFor(this);
	}
}

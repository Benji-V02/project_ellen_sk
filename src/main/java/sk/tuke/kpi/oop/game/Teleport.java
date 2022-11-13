package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;

public class Teleport extends AbstractActor {

	//TODO: Make reaction with Player
	private Teleport destination;
	private boolean isPorted;

	public Teleport() {
		setAnimation(new Animation("sprites/lift.png"));
		destination = null;
		isPorted = false;
	}

	private void tpTest() {
		Player player = (Player) super.getScene().getFirstActorByName("Player");
		Rectangle2D playerPos = new Rectangle2D.Float(player.getPosX() + player.getWidth() / 2 - .5f, player.getPosY() + player.getHeight() / 2 - .5f,
			1, 1);
		//System.out.println(this.intersects(player));
		Rectangle2D tp = new Rectangle2D.Float(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
		//if(this.intersects(player))
		if (tp.intersects(playerPos))
			teleportPlayer(player);
		else this.isPorted = false;
	}

	public Teleport getDestation() {
		return destination;
	}

	public void setDestination(Teleport destinationTeleport) {
		this.destination = destinationTeleport;
	}

	public void setPorted(boolean isPorted) {
		this.isPorted = isPorted;
	}

	public void teleportPlayer(Player player) {
		if (destination == null || player == null || isPorted) return;
		player.setPosition(destination.getPosX(), destination.getPosY());
		this.destination.setPorted(true);
	}

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<Teleport>(this::tpTest)).scheduleFor(this);
	}
}

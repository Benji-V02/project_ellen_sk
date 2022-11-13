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


	public Teleport(Teleport destination) {
		this.destination = destination;
		setAnimation(new Animation("sprites/lift.png"));
		isPorted = false;
	}

	public Teleport() {
		this(null);

	}

	private void tpTest() {
		Player player = (Player) super.getScene().getFirstActorByName("Player");
		Rectangle2D playerPos = new Rectangle2D.Float(player.getPosX() + player.getWidth() / 2 - .5f, player.getPosY() + player.getHeight() / 2 - .5f,
			1, 1);
		//System.out.println(this.intersects(player));
		Rectangle2D tp = new Rectangle2D.Float(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
		//if(this.intersects(player))
		if (tp.intersects(playerPos) && !isPorted)
			teleportPlayer(player);
		else if (!tp.intersects(playerPos)) this.isPorted = false;
	}

	public Teleport getDestation() {
		if (destination == null) return null;
		return destination;
	}

	public void setDestination(Teleport destinationTeleport) {
		if (destinationTeleport == null || destinationTeleport == this) return;
		this.destination = destinationTeleport;
	}

	public void setPorted(boolean isPorted) {
		this.isPorted = isPorted;
	}

	public void teleportPlayer(Player player) {
		if (destination == null || player == null || isPorted) return;
		player.setPosition(destination.getPosX() + (destination.getWidth() / 2) - player.getWidth() / 2,
			destination.getPosY() + (destination.getHeight() / 2) - player.getHeight() / 2);
		this.destination.setPorted(true);
	}

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<Teleport>(this::tpTest)).scheduleFor(this);
	}
}

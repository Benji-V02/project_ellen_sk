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
			destination.teleportPlayer(player);
		else if (!tp.intersects(playerPos)) this.isPorted = false;
	}

	public Teleport getDestination() {
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
		//System.out.println("true");
		if (player == null || isPorted) return;
		player.setPosition(this.getPosX() + (this.getWidth() / 2) - player.getWidth() / 2,
			this.getPosY() + (this.getHeight() / 2) - player.getHeight() / 2);
		this.setPorted(true);
	}

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<Teleport>(this::tpTest)).scheduleFor(this);
	}
}

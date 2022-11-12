package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {

	//TODO: Make reaction with Player
	private Teleport destination;

	public Teleport() {
		setAnimation(new Animation("sprites/lift.png"));
		destination = null;
	}


	public Teleport getDestation() {
		return destination;
	}

	public void setDestination(Teleport destinationTeleport) {
		this.destination = destinationTeleport;
	}


	public void teleportPlayer(Player player) {
		if (destination == null || player == null) return;
		if (player.getPosX() == this.getPosX() && player.getPosY() == this.getPosY()) {
			player.setPosition(destination.getPosX(), destination.getPosY());

		}

	}

}

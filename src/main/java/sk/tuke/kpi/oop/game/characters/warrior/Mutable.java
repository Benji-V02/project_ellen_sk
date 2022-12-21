package sk.tuke.kpi.oop.game.characters.warrior;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.characters.Health;

public interface Mutable extends Actor {

	int getSpeed();

	Health getHealth();

	void setMutation(Mutable mutation);
}

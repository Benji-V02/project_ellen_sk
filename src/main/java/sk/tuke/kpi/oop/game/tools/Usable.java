package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;

import javax.swing.*;

public interface Usable<A extends Actor>{

	void useWith(A actor);
}

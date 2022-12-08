package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door implements Openable, Usable {

	//private final Animation anime;

	public Door() {
		//Animation anime = new Animation("sprites/vdoor.png", 16, 32, .1f, Animation.PlayMode.ONCE);

	}

	@Override
	public void useWith(Actor actor) {

	}

	@Override
	public Class getUsingActorClass() {
		return null;
	}

	@Override
	public void open() {

	}

	@Override
	public void close() {

	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public int getPosX() {
		return 0;
	}

	@Override
	public int getPosY() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public @NotNull String getName() {
		return null;
	}

	@Override
	public @NotNull Animation getAnimation() {
		return null;
	}

	@Override
	public @Nullable Scene getScene() {
		return null;
	}

	@Override
	public @NotNull Point getPosition() {
		return null;
	}

	@Override
	public void setPosition(int posX, int posY) {

	}

	@Override
	public void addedToScene(@NotNull Scene scene) {

	}

	@Override
	public void removedFromScene(@NotNull Scene scene) {

	}

	@Override
	public boolean intersects(@NotNull Actor actor) {
		return false;
	}
}

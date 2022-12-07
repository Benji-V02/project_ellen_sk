package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;

public interface Collectible extends Actor {
	@Override
	default int getPosX() {
		return 0;
	}

	@Override
	default int getPosY() {
		return 0;
	}

	@Override
	default int getWidth() {
		return 0;
	}

	@Override
	default int getHeight() {
		return 0;
	}

	@Override
	@NotNull
	default String getName() {
		return null;
	}

	@Override
	default @NotNull Animation getAnimation() {
		return null;
	}

	@Override
	default @Nullable Scene getScene() {
		return null;
	}

	@Override
	default @NotNull Point getPosition() {
		return null;
	}

	@Override
	default void setPosition(int posX, int posY) {

	}

	@Override
	default void addedToScene(@NotNull Scene scene) {

	}

	@Override
	default void removedFromScene(@NotNull Scene scene) {

	}

	@Override
	default boolean intersects(@NotNull Actor actor) {
		return false;
	}
}

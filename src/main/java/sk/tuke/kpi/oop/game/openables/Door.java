package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Ripley> {

	public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
	public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

	private final Animation anime;
	private boolean isOpened;

	public Door() {
		anime = new Animation("sprites/vdoor.png", 16, 32, .1f, Animation.PlayMode.ONCE_REVERSED);
		setAnimation(anime);
		isOpened = false;
		//this.close();
	}

	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		close();
	}

	@Override
	public void useWith(Ripley actor) {
		if (isOpen()) close();
		else open();

	}

	@Override
	public Class<Ripley> getUsingActorClass() {return Ripley.class;}

	@Override
	public void open() {
		anime.setPlayMode(Animation.PlayMode.ONCE);
		getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
		getScene().getMap().getTile(getPosX() / 16, (getPosY() / 16) + 1).setType(MapTile.Type.CLEAR);
		isOpened = true;
		getScene().getMessageBus().publish(DOOR_OPENED, this);
	}

	@Override
	public void close() {
		anime.setPlayMode(Animation.PlayMode.ONCE_REVERSED);

		getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
		getScene().getMap().getTile(getPosX() / 16, (getPosY() / 16) + 1).setType(MapTile.Type.WALL);
		isOpened = false;
		getScene().getMessageBus().publish(DOOR_CLOSED, this);
	}

	@Override
	public boolean isOpen() {
		return isOpened;
	}
}

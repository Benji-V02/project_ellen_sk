package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {


	private final String name;
	private final int capacity;
	private final List<Collectible> items;

	public Backpack(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		items = new ArrayList<Collectible>();
	}

	@Override
	public @NotNull List<Collectible> getContent() {
		ArrayList<Collectible> newOne = new ArrayList<Collectible>();
		newOne.addAll(items);
		return newOne;
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public @NotNull String getName() {
		return name;
	}

	@Override
	public void add(@NotNull Collectible actor) {
		if (capacity == items.size()) throw new IllegalStateException(String.format("%s is full", name));
		items.add(actor);

	}

	@Override
	public void remove(@NotNull Collectible actor) {
		items.remove(actor);

	}

	@Override
	public @Nullable Collectible peek() {
		try {
			return items.get(items.size());
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public void shift() {
		items.add(items.get(0));
		items.remove(0);
	}

	@NotNull
	@Override
	public Iterator<Collectible> iterator() {
		return items.iterator();
	}
}

package sk.tuke.kpi.oop.game.behaviour;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.function.Predicate;

public class Observing<A extends Actor, T> implements Behaviour<A> {

	private final Topic<T> topic;
	private final Predicate<T> predicate;
	private final Behaviour<A> delegate;

	public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> delegate) {
		this.delegate = delegate;
		this.predicate = predicate;
		this.topic = topic;
	}

	@Override
	public void setUp(A actor) {
		actor.getScene().getMessageBus().subscribe(topic, (message) -> {
			if (predicate.test(message))
				delegate.setUp(actor);
		});

	}
}

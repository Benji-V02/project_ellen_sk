package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class DefectiveLight extends Light implements Repairable{

	private boolean isRepaired;
	private Disposable timer;

	public DefectiveLight(){
		isRepaired = false;
	}

	private void randomLight(){
		this.isRepaired = false;
			if(Math.random() > .6)
				super.toggle();
	}


	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		timer = new Loop<>(new Invoke<DefectiveLight>(this::randomLight)).scheduleFor(this);
	}

	@Override
	public boolean repair() {
		if(this.isRepaired) return false;
		this.isRepaired = true;
		super.turnOn();
		timer.dispose();
		timer = new ActionSequence<>(
			new Wait<>(10),
			new Loop<>(new Invoke<DefectiveLight>(this::randomLight))
		).scheduleFor(this);
		return true;
	}
}

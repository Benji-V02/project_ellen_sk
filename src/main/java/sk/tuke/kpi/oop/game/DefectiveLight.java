package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class DefectiveLight extends Light {

	private void randomLight(){
			if(Math.random() > .6)
				super.toggle();
	}


	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<DefectiveLight>(this::randomLight)).scheduleFor(this);
	}
}

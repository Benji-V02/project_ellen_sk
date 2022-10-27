package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light {

	private void randomLight(){
		if(super.isActiveState() && super.isElectricityFlow()){
			if(Math.random() % 20 == 0){
				updateAnimation(false);
			}else updateAnimation(true);
		}
	}


	private void updateAnimation(boolean offState){
		if(offState) setAnimation(super.getAnime(1));
		else setAnimation(super.getAnime(0));
	}


	@Override
	public void addedToScene(@NotNull Scene scene) {
		super.addedToScene(scene);
		new Loop<>(new Invoke<DefectiveLight>(this::randomLight)).scheduleFor(this);
	}
}

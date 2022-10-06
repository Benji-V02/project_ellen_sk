package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {

	private int temperature;
	private int damage;
	private float multiplier = 1f;
	private Animation normalAnimation;
	private Animation damageAnimation = new Animation("sprites/reactor_hot.png", 80, 80, .05f, Animation.PlayMode.LOOP_PINGPONG);
	private Animation destroyedAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP);


	public Reactor(){
		this.temperature = 0;
		this.damage = 0;
		normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
		setAnimation(this.normalAnimation);
	}


	public int getDamage(){
		return damage;
	}


	private void setDamage(int damage) {
		this.damage += damage;
		if(33 <= this.damage && this.damage <= 66){
			this.multiplier = 1.5f;
		}else if(this.damage > 66) this.multiplier = 2f;
		else this.multiplier = 1f;
	}


	public double getTemperature() {
		return temperature;
	}

	private void setTemperature(float temperature) {
		this.temperature += Math.ceil(temperature);
		if (this.temperature > 2000) {
			long newDamage = Math.floorDiv((long) (this.temperature - 2000.f), (long) 40.);
			if (newDamage > getDamage()) {
				this.setDamage((int) (newDamage) - this.getDamage());
			}
		}
		updateAnimation();
	}


	private void updateAnimation () {
		if(this.temperature < 4000) setAnimation(normalAnimation);
		else if (this.temperature >= 4000 && this.temperature < 6000) {
			setAnimation(damageAnimation);
		} else if (this.temperature >= 6000)
			setAnimation(destroyedAnimation);
	}


	public void increaseTemperature(int temperature){
		if(temperature < 0) return;
		setTemperature(temperature * multiplier);
	}

	public void decreaseTemperature(int temperature){
		if(temperature < 0) return;
		setTemperature(this.damage < 50 ? -temperature : this.damage == 100 ? 0 : -temperature/2);
	}

}

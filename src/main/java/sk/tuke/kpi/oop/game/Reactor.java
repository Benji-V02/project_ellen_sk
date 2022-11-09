package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable{

	private int temperature;
	private int damage;
	private float multiplier = 1f;
	private State state;
	private Animation normalAnimation;
	private Animation damageAnimation = new Animation("sprites/reactor_hot.png", 80, 80, .05f, Animation.PlayMode.LOOP_PINGPONG);
	private Animation destroyedAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP);
	private Animation animationOff;
	private Animation animeExtinguished;
	private Set<EnergyConsumer> devices;


	public Reactor(){
		this.temperature = 0;
		this.damage = 0;
		this.state = State.OFF;
		this.devices = new HashSet<>();
		this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
		this.animationOff = new Animation("sprites/reactor.png");
		this.animeExtinguished = new Animation("sprites/reactor_extinguished.png");
		setAnimation(this.animationOff);
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
		if(this.damage == 100) {
			turnOff();
			this.state = State.BROKEN;
		}
	}


	public int getTemperature() {
		return temperature;
	}

	private void setTemperature(float temperature) {
		if(this.state != State.ON) return;
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
		if(this.state == State.OFF && getDamage() < 100){
			setAnimation(animationOff);
		}
		else if(this.temperature < 4000) {
			setAnimation(normalAnimation);
			normalAnimation.setFrameDuration(.2f - .001f * this.damage);
		}
		else if (this.temperature >= 4000 && this.temperature < 6000) {
			setAnimation(damageAnimation);
			damageAnimation.setFrameDuration(.1f - .0005f * this.damage);
		} else if (this.state == State.BROKEN)
			setAnimation(destroyedAnimation);
		else if (this.state == State.EXTINGUISHED) {
			setAnimation(animeExtinguished);

		}
	}


	public void increaseTemperature(int temperature){
		if(temperature < 0) return;
		setTemperature(temperature * multiplier);
	}

	public void decreaseTemperature(int temperature){
		if(temperature < 0) return;
		setTemperature(this.damage < 50 ? -temperature : this.damage == 100 ? 0 : -temperature/2);
	}


	public boolean repair(){
		if(this.damage == 0 || this.damage >= 100) return false;
		int hDamage = this.getDamage();
		int newTemp = 6000*hDamage/100;
		if(getTemperature() > newTemp) decreaseTemperature(getTemperature() - newTemp);
		if(this.getDamage() < 50) setDamage(-getDamage());
		else setDamage(-50);
		return true;
	}

	@Override
	public void turnOn(){
		if(getDamage() == 100) return;
		this.state = State.ON;
		updateAnimation();
		for(EnergyConsumer device : this.devices) {
			device.setPowered(true);
		}
	}

	@Override
	public void turnOff(){
		this.state = State.OFF;
		updateAnimation();
		for(EnergyConsumer device : this.devices) {
			device.setPowered(false);
		}
	}


	@Override
	public boolean isOn(){
		if(state == State.ON) return true;
		return false;
	}


	public void addDevice(EnergyConsumer device){
		this.devices.add(device);
		if(this.state == State.ON) device.setPowered(true);
		else device.setPowered(false);

	}


	public void removeDevice(EnergyConsumer device){
		device.setPowered(false);
		this.devices.remove(device);
	}


	private enum State{
		ON,
		OFF,
		BROKEN,
		EXTINGUISHED,
	};


	public boolean extinguish(){
		if(this.state == State.BROKEN){
			this.state = State.EXTINGUISHED;
			decreaseTemperature(4000);
			updateAnimation();
			return true;
		}
		return false;
	}


	public void addedToScene(Scene scene){
		super.addedToScene(scene);
		scene.scheduleAction(new PerpetualReactorHeating(1), this);
	}

}




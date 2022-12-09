package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

	private int ammo;
	private final int maxAmmo;

	public Firearm(int initAmmo, int maxAmmo) {
		this.ammo = initAmmo;
		this.maxAmmo = maxAmmo;
	}

	public Firearm(int maxAmmo) {
		this(maxAmmo, maxAmmo);
	}

	public int getAmmo() {
		return ammo;
	}

	public void reload(int newAmmo) {
		ammo += newAmmo;
		if (ammo > maxAmmo) ammo = maxAmmo;

	}

}

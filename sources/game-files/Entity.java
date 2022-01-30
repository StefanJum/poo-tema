package p1;

import java.util.*;

abstract class Entity {
	private List abilities;
	protected int maxHealth, health;
	protected int maxMana, mana;
	private boolean fireProtection;
	private boolean iceProtection;
	private boolean earthProtection;

	/**
	 * Construct a new Entity object with the given health, mana and abilities
	 *
	 * @param abilities
	 * @param maxHealth
	 * @param maxMana
	 * @param fireProtection
	 * @param iceProtection
	 * @param earthProtection
	 */
	public Entity(List abilities, int maxHealth, int maxMana,
			boolean fireProtection, boolean iceProtection, boolean earthProtection) {
		this.abilities = abilities;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.maxMana = maxMana;
		this.mana = maxMana;
		this.fireProtection = fireProtection;
		this.iceProtection = iceProtection;
		this.earthProtection = earthProtection;
	}

	/**
	 * Restore the entity health.
	 * If the resulting health is greater than the maxHealth,
	 * healt = maxHealth
	 *
	 * @param health
	 * 		The amount of health restored
	 */
	public void restoreHealth(int health) {
		this.health += health;
		if (this.health > maxHealth)
			this.health = maxHealth;
	}

	/**
	 * Restore the entity mana.
	 * If the resulting mana is greater than the maxmana,
	 * healt = maxmana
	 *
	 * @param mana
	 * 		The amount of mana restored
	 */
	public void restoreMana(int mana) {
		this.mana += mana;
		if (this.mana > maxMana)
			this.mana = maxMana;
	}

	/**
	 * Uses mana
	 * If the remaining mana is less than 0,
	 * mana = 0
	 *
	 * @param mana
	 * 		The amount of mana used
	 */
	public void consumeMana(int mana) {
		this.mana -= mana;

		if (this.mana < 0)
			this.mana = 0;
	}

	public boolean useAbility(String ability) {
		return false;
	}

	/**
	 * @return Entity health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return Entity mana
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * @return Resistance to fire
	 */
	public boolean getFireRes() {
		return fireProtection;
	}

	/**
	 * @return Resistance to ice
	 */
	public boolean getIceRes() {
		return iceProtection;
	}

	/**
	 * @return Resistance to earth
	 */
	public boolean getEarthRes() {
		return earthProtection;
	}

	/**
	 * The method aplies damage to the Entity
	 *
	 * @param damage
	 * 		Damage received by the Entity
	 *
	 * @return int
	 * 		Damage taken
	 */
	abstract int receiveDamage(int damage);

	/**
	 * @return the damage given by the Entity
	 */
	abstract int getDamage();

}

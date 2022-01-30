package p1;

import java.util.*;

public class Enemy extends Entity implements CellElement {
	public char toCharacter() {
		return 't';
	}

	/**
	 * Create an Enemy object with the specified characteristics.
	 *
	 * @param int health
	 * @param int mana
	 * @param boolean fireProtection
	 * @param boolean iceProtection
	 * @param boolean earthProtection
	 */
	public Enemy(int health, int mana,
			boolean fireProtection, boolean iceProtection, boolean earthProtection) {
		super(null, health, mana,
				fireProtection, iceProtection, earthProtection);
	}

	/**
	 * Receive damage.
	 * There is a 50% chance to dodge the attack.
	 *
	 * @param int damage given
	 * @return int damage received
	 */
	public int receiveDamage(int damage) {
		Random rand = new Random();
		int avoid_chance = rand.nextInt(100);

		if (avoid_chance < 50) {
			super.health -= damage;
			return damage;
		}

		return 0;
	}

	/**
	 * Get the amount of damage the enemy does.
	 * There is a 50% chance to do double damage
	 *
	 * @return int damage given
	 */
	public int getDamage() {
		Random rand = new Random();
		int double_chance = rand.nextInt(100);

		if (double_chance < 50)
			return 10;
		return 20;
	}

	/**
	 * @return int special attack mana cost
	 */
	public int getSpecialAttackCost() {
		return 20;
	}
}

package p1;

import java.util.*;

public class Rogue extends Character {

	/**
	 * Create a Rogue object with the specified characteristics
	 *
	 * @param String name
	 * @param int experience
	 * @param int level
	 */
	public Rogue(String name, int experience, int level) {
		super(name, experience, level, false, false, true);
		super.strenght = 10;
		super.charisma = 1;
		super.dexterity = 2;
	}


	/**
	 * Get the damage received.
	 * There is a chance to dodge the attack, based on the characteristics.
	 *
	 * @return int damage received
	 */
	public int receiveDamage(int damage) {
		int abilities = strenght + ((charisma + dexterity) / 2);
		Random rand = new Random();
		int half_chance = rand.nextInt(20);

		if (half_chance < abilities) {
			super.health -= (damage / 2);
			return damage / 2;
		} else {
			super.health -= damage;
			return damage;
		}
	}


	/**
	 * Get the damage the character does.
	 *
	 * @return int damage
	 */
	public int getDamage() {
		return super.getDamage() + super.strenght / 2;
	}
}


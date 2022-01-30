package p1;

import java.util.*;

abstract class Character extends Entity {
	private String name;
	private int currentX, currentY;
	private Inventory inventory;
	private int experience;
	private int level;
	// attributes must be between 0-10
	protected int strenght, charisma, dexterity;

	/**
	 * Constructs a new Character.
	 * Calls the Entity constructor with health and mana equal to
	 * 50 * level + experience
	 *
	 * @param name
	 * @param experience
	 * @param level
	 * @param fireProtection
	 * @param iceProtection
	 * @param earthProtection
	 */
	public Character(String name, int experience, int level,
			boolean fireProtection, boolean iceProtection, boolean earthProtection) {
		super(null, (level * 50 + experience), (level * 50 + experience),
				fireProtection, iceProtection, earthProtection);

		this.name = name;
		this.experience = experience;
		this.level = level;

		this.inventory = new Inventory(30, 30);
	}

	/**
	 * Buy a potion.
	 * If there is not ehough storage in the inventory return false
	 *
	 * @param Potion p to be bought
	 *
	 * @return boolean true if the potion is bought
	 */
	public boolean buyPotion(Potion p) {
		if (inventory.getAvailableStorage() < p.getWeight() ||
				inventory.getAvailableCoins() < p.getPrice())
			return false;

		inventory.addPotion(p);
		return true;
	}

	/**
	 * Remove a potion fron the inventory
	 *
	 * @return boolean false if the potion was not found
	 */
	public boolean removePotion(Potion p) {
		return inventory.removePotion(p);
	}

	/**
	 * @return the damage given by the Character
	 */
	public int getDamage() {
		return level * 20 + (experience / 10) + strenght + ((charisma + dexterity) / 2);
	}

	/**
	 * @return mana cost of the special attack
	 */
	public int getSpecialAttackCost() {
		return level * 15;
	}

	/**
	 * Add coins to the inventory.
	 *
	 * @param int coins
	 * 		Amount of coins to be added to the inventory
	 *
	 * @return integer number of coins available
	 */
	public int addCoins(int coins) {
		inventory.addCoins(coins);
		return inventory.getAvailableCoins();
	}

	/**
	 * @param int index of the desired potion
	 *
	 * @return Potion with the given index
	 */
	public Potion getPotion(int index) {
		return inventory.getPotion(index);
	}

	/**
	 * Prints the available potions
	 */
	public void printPotions() {
		inventory.printPotions();
	}

	/**
	 * Increase character experience.
	 *
	 * @param int experience to add
	 * @return int character xp
	 */
	public int increaseXp(int experience) {
		this.experience += experience;

		return this.experience;
	}

	/**
	 * Upgrade character to a new level and xp
	 *
	 * @param int experience
	 * @param int level
	 */
	public void upgradeCharacter(int experience, int level) {
		this.experience = experience;
		this.level = level;
	}

	/**
	 * @return int level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}
}

package p1;

import java.util.*;

public class Inventory {
	private ArrayList<Potion> potions;
	private int maxWeight;
	private int coins;

	/**
	 * Construct a new Inventory object with the coins and maxWeight
	 *
	 * @param int coins
	 * @param int maxWeight
	 */
	public Inventory(int coins, int maxWeight) {
		this.maxWeight = maxWeight;
		this.coins = coins;
		potions = new ArrayList<>();
	}

	/**
	 * Add a new potion to the list
	 *
	 * @param Potion p to be added
	 */
	public void addPotion(Potion p) {
		potions.add(p);
		coins -= p.getPrice();
	}

	/**
	 * Remove a potion from the list
	 *
	 * @return boolean false if the potion was not found in the list
	 */
	public boolean removePotion(Potion p) {
		return potions.remove(p);
	}

	/**
	 * @return int the available storage
	 */
	public int getAvailableStorage() {
		int totalWeight = 0;

		for (Potion p : potions) {
			totalWeight += p.getWeight();
		}

		return maxWeight - totalWeight;
	}

	/**
	 * @return int available coins
	 */
	public int getAvailableCoins() {
		return coins;
	}

	/**
	 * Add coins to the inventory
	 *
	 * @param int coins to be added
	 */
	public void addCoins(int coins) {
		this.coins += coins;
	}

	/**
	 * Get potion with the given index
	 *
	 * @param int index
	 *
	 * @return Potion with the given index
	 */
	public Potion getPotion(int index) {
		return potions.get(index);
	}

	/**
	 * Prints the available potions
	 */
	public void printPotions() {
		int i = 0;
		for (Potion p : potions) {
			System.out.println("[" + i + "]: " + p);
			i++;
		}
	}
}

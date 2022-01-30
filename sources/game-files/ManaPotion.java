package p1;

public class ManaPotion implements Potion {
	private int price;
	private int weight;
	private int regen;

	/**
	 * Construct a new ManaPotion whith the given price, weight and regen
	 *
	 * @param int price
	 * @param int weight
	 * @param int regen
	 */
	public ManaPotion(int price, int weight, int regen) {
		this.price = price;
		this.weight = weight;
		this.regen = regen;
	}

	/**
	 * @return int the potion price
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * @return int the potion weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * @return int the potion regen
	 */
	public int getRegen() {
		return this.regen;
	}

	/**
	 * Restore character health
	 *
	 * @param Character character
	 */
	public void potionEffect(Character character) {
		character.restoreMana(regen);
	}

	/**
	 * Convert ManaPotion to String
	 *
	 * @return String
	 */
	public String toString() {
		return ("Mana potion:" +
				" price - " + price +
				", regeneration - " + regen +
				", weight - " + weight);
	}
}

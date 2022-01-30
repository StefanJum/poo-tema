package p1;

import java.util.*;

public class Shop implements CellElement {
	private ArrayList<Potion> potionList;

	/**
	 * Construct a new Shop object with 2-4 random potions
	 */
	public Shop() {
		Random rand = new Random();
		int totalPotions = rand.nextInt(2) + 2;
		int hPotions = rand.nextInt(totalPotions - 1) + 1;
		int mPotions = totalPotions - hPotions + 1;

		potionList = new ArrayList<>();
		for (int i = 0; i < hPotions; i ++) {
			potionList.add(new HealthPotion(rand.nextInt(9) + 1,
						rand.nextInt(9) + 1,
						rand.nextInt(10) * 10 + 10));
		}

		for (int i = 0; i < mPotions; i ++) {
			potionList.add(new ManaPotion(rand.nextInt(9) + 1,
						rand.nextInt(9) + 1,
						rand.nextInt(10) * 10 + 10));
		}
	}

	/**
	 * Get potion with the specified index
	 *
	 * @param int index
	 *
	 * @return Potion with the given index
	 */
	public Potion getPotion(int index) {
		if (index > potionList.size())
			return null;

		Potion p = potionList.remove(index);

		return p;
	}

	/**
	 * List available potions
	 */
	public void listPotions() {
		for (int i = 0; i < potionList.size(); i ++) {
			System.out.println("[" + i + "]: " + potionList.get(i));
		}
	}

	/**
	 * @return int Potions number
	 */
	public int getPotionNo() {
		return potionList.size();
	}

	/**
	 * @return char 'S'
	 */
	public char toCharacter() {
		return 'S';
	}
}

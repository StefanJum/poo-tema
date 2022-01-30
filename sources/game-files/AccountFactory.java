package p1;

public class AccountFactory {
	/**
	 * Creates a new Character of given type
	 *
	 * @param String type
	 * 		"Warrior", "Rogue", "Mage"
	 * @param String name
	 * @param int experience
	 * @param int level
	 *
	 * @return Character
	 */
	public static Character factory(String type, String name, int experience, int level) {
		if (type.equals(new String("Warrior"))) {
			return new Warrior(name, experience, level);
		} else if (type.equals(new String("Mage"))) {
			return new Mage(name, experience, level);
		} else if (type.equals(new String("Rogue"))) {
			return new Rogue(name, experience, level);
		}

		return null;
	}
}

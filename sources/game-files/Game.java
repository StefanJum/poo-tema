package p1;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import org.json.*;

enum CellEnum {
	EMPTY, ENEMY, SHOP, FINISH;
}

public class Game {
	private ArrayList<Account> accounts;
	private HashMap<CellEnum, ArrayList<String>> cellmap;
	private Account account;

	/**
	 * Creates a new Game object
	 * Add empty lists for all the available cell types in stories map
	 */
	public Game() {
		accounts = new ArrayList<>();
		cellmap = new HashMap<>();

		cellmap.put(CellEnum.EMPTY, new ArrayList<>());
		cellmap.put(CellEnum.ENEMY, new ArrayList<>());
		cellmap.put(CellEnum.SHOP, new ArrayList<>());
		cellmap.put(CellEnum.FINISH, new ArrayList<>());

		loadStories();
	}

	/**
	 * Run the hardcoded example
	 *
	 * @param Grid grid
	 */
	public void runHard(Grid grid) {
		loadStories();
		clearScreen();
		CellEnum currentCell;
		Scanner sc = new Scanner(System.in);

		chooseCharacter();
		Character character = grid.getCharacter();

		grid.printGrid();
		grid.goEast();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();

		grid.printGrid();
		grid.goEast();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();

		grid.printGrid();
		grid.goEast();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();

		Shop shop = new Shop();
		shop.listPotions();
		__hard__buyPotions(shop, character);

		grid.printGrid();
		grid.goEast();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();

		grid.printGrid();
		grid.goSouth();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();

		grid.printGrid();
		grid.goSouth();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();

		grid.printGrid();
		grid.goSouth();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();

		Enemy enemy = new Enemy(300, 300, false, true, false);
		__hard__fight(enemy, character);

		grid.printGrid();
		currentCell = grid.getCell();
		printStory(currentCell);
		__hard__waitForInput();
		grid.goSouth();
		System.out.println(
			"Congratulations, you have found the exit!" +
			"\nYou gained 50 Xp!");
		character.increaseXp(50);

		saveProgress(character);
		return;
	}

	/**
	 * Use the 2 potions in the inventory
	 *
	 * @param Character character
	 */
	private void __hard__usePotion(Character character) {
		int index = 0;

		Potion p = character.getPotion(index);

		System.out.println("Using potion: " + p);
		p.potionEffect(character);
		character.removePotion(p);


		System.out.println("You now have: " +
				character.getHealth() +
				" health and " +
				character.getMana() +
				" mana!");
	}

	/**
	 * Use the hardcoded fight sequence
	 *
	 * @param Enemy enemy
	 * @param Character character
	 */
	private void __hard__fight(Enemy enemy, Character character) {
		int damage;
		boolean first = true;
		while (enemy.getHealth() > 0) {
			if (character.getHealth() <= 0) {
				System.out.println("Character health reached 0\n" +
					"For testing purposes, it will be regenerated to 100");

				character.restoreHealth(100);
				__hard__waitForInput();
			}

			damage = character.getDamage();
			__hard__checkDamage(damage, 'E', character, enemy, 'I');
			if (enemy.getHealth() <= 0 && !first) break;

			damage = enemy.getDamage();
			__hard__checkDamage(damage, 'C', character, enemy, 'I');
			if (enemy.getHealth() <= 0 && !first) break;

			damage = character.getDamage();
			__hard__checkDamage(damage, 'E', character, enemy, 'F');
			if (enemy.getHealth() <= 0 && !first) break;

			damage = enemy.getDamage();
			__hard__checkDamage(damage, 'C', character, enemy, 'F');
			if (enemy.getHealth() <= 0 && !first) break;

			damage = character.getDamage();
			__hard__checkDamage(damage, 'E', character, enemy, 'E');
			if (enemy.getHealth() <= 0 && !first) break;

			damage = enemy.getDamage();
			__hard__checkDamage(damage, 'C', character, enemy, 'E');
			if (enemy.getHealth() <= 0 && !first) break;

			damage = character.getDamage();
			__hard__checkDamage(damage, 'E', character, enemy, 'N');
			if (enemy.getHealth() <= 0 && !first) break;

			damage = enemy.getDamage();
			__hard__checkDamage(damage, 'C', character, enemy, 'N');
			if (enemy.getHealth() <= 0 && !first) break;

			if (first) {
				__hard__usePotion(character);
			} else {
				__hard__checkDamage(damage, 'E', character, enemy, 'N');
			}
			//usePotion(Grid.getMap());
			__hard__checkDamage(damage, 'C', character, enemy, 'N');

			if (first) {
				__hard__usePotion(character);
			} else {
				__hard__checkDamage(damage, 'E', character, enemy, 'N');
			}
			__hard__checkDamage(damage, 'C', character, enemy, 'N');

			first = false;
		}

		System.out.println("You defeated the enemy!");
	}

	/**
	 * Do the coresponding damage for each type of attack
	 *
	 * @param int rdamage
	 * 		The base damage that should be given
	 * @param char type
	 * 		'E' if the enemy is attacked, 'C' if the character is attacked
	 *
	 * @param Character character
	 * @param Enemy enemy
	 * @param char attackType
	 * 		'I'ce, 'F'ire, 'E'arth, 'N'ormal
	 */
	private void __hard__checkDamage
		(int rdamage, char type, Character character, Enemy enemy, char attackType) {
		int damage;

		if (type == 'E' &&
				(attackType == 'I' || attackType == 'E' || attackType == 'F')) {
			if (character.getMana() < character.getSpecialAttackCost()) {
				System.out.println("Not ehough mana, using normal attack!");
				__hard__checkDamage(rdamage, 'E', character, enemy, 'N');
				return;
			}

			character.consumeMana(character.getSpecialAttackCost());
		}

		if (attackType == 'I') {
			if (type == 'E') {
				if (enemy.getIceRes()) {
					System.out.println("The enemy is imune to Ice");
					__hard__waitForInput();
					return;
				}

				damage = rdamage * 2;
			} else {
				if (character.getIceRes()) {
					System.out.println("The enemy used an Ice spell");
					__hard__waitForInput();
					return;
				}

				damage = rdamage * 2;
			}
		}

		if (attackType == 'F') {
			if (type == 'E') {
				if (enemy.getFireRes()) {
					System.out.println("The enemy is imune to Fire");
					__hard__waitForInput();
					return;
				}

				damage = rdamage * 2;
			} else {
				if (character.getFireRes()) {
					System.out.println("The enemy used a Fire spell");
					__hard__waitForInput();
					return;
				}

				damage = rdamage * 2;
			}
		}

		if (attackType == 'E') {
			if (type == 'E') {
				if (enemy.getEarthRes()) {
					System.out.println("The enemy is imune to Earth");
					__hard__waitForInput();
					return;
				}

				damage = rdamage * 2;
			} else {
				if (character.getEarthRes()) {
					System.out.println("The enemy used an Earth spell");
					__hard__waitForInput();
					return;
				}

				damage = rdamage * 2;
			}
		}

		if (type == 'E')
			damage = enemy.receiveDamage(rdamage);
		else
			damage = character.receiveDamage(rdamage);

		if (damage == 0) {
			if (type == 'E') {
				System.out.println("The enemy dodged the atatck!");
			} else {
				System.out.println("You dodged the atatck!");
			}

			__hard__waitForInput();
			return;
		}

		if (type == 'E')
			System.out.println("You did " + damage + " damage");
		else
			System.out.println("The enemy did " + damage + " damage");

		if (type == 'E')
			__hard__printAttributes(enemy, type);
		else
			__hard__printAttributes(character, type);
		__hard__waitForInput();
	}

	/**
	 * Wait to receive the letter 'P' from the terminal
	 */
	private void __hard__waitForInput() {
		Scanner sc = new Scanner(System.in);
		char ch;

		System.out.println("Press (P) to continue the game");
		ch = sc.next().charAt(0);
		while (ch != 'P') {
			ch = sc.next().charAt(0);
		}
		clearScreen();
	}

	/**
	 * Buy the hardcided wanted potions
	 *
	 * @param Shop shop
	 * @param Character character
	 */
	private void __hard__buyPotions(Shop shop, Character character) {
		int potionsNo = shop.getPotionNo();

		Potion p1 = shop.getPotion(0);
		Potion p2 = shop.getPotion(potionsNo - 2);

		character.buyPotion(p1);
		character.buyPotion(p2);

		System.out.println("Bought potion: " + p1);
		System.out.println("Bought potion: " + p2);
	}

	private void __hard__printAttributes(Entity entity, char type) {
		if (type == 'C')
			System.out.println("You have: ");
		else
			System.out.println("The enemy has: ");

		System.out.println("Health: " + entity.getHealth() +
				", Mana: " + entity.getMana());
	}

	/**
	 * Run the actual game
	 * Choose account, choose character and loop while
	 * treating all the cell types individually
	 *
	 * @param Grid game grid
	 */
	public void run(Grid grid){
		clearScreen();
		System.out.println("Choose the game mode (cli/ui)");
		CellEnum currentCell;
		Scanner sc = new Scanner(System.in);

		chooseCharacter();
		Character character = grid.getCharacter();

		while (true) {
			grid.printGrid();
			currentCell = grid.getCell();
			printStory(currentCell);

			switch (currentCell) {
				case EMPTY:
					makeMove(grid);
					break;

				case ENEMY:
					if (grid.getVisited() > 1) {
						makeMove(grid);
						break;
					}

					Enemy enemy = new Enemy(100, 100, false, true, false);
					fight(enemy, character);

					if (character.getHealth() <= 0) {
						System.out.println("You lost the game. :(");
						return;
					} else {
						System.out.println(
							"You defeated the enemy and gained 10 coins");
						System.out.println("You now have " +
								character.addCoins(10) + " coins.");
					}
					grid.printGrid();
					makeMove(grid);
					break;

				case SHOP:
					if (grid.getVisited() > 1) {
						makeMove(grid);
						break;
					}

					Shop shop = new Shop();
					System.out.println(
						"Choose a potion to buy or -1 to continue");
					shop.listPotions();

					buyPotion(shop, grid.getCharacter());

					grid.printGrid();
					makeMove(grid);
					break;

				case FINISH:
					System.out.println(
						"Congratulations, you have found the exit!" +
						"\nYou gained 50 Xp!");
					character.increaseXp(50);

					saveProgress(character);
					return;
				default:
			}
		}
	}

	public void listOptions(Grid grid){
		CellEnum currentCell = grid.getCell();

		System.out.println(currentCell);
	}

	public void story(){}

	/**
	 * Make a move, used at every character turn
	 *
	 * @param Grid game grid
	 */
	private void makeMove(Grid grid) {
		Scanner sc = new Scanner(System.in);
		System.out.println(
			 "Choose a direction to move (N/W/S/E) or choose a potion (P)");
		char direction = sc.next().charAt(0);
			clearScreen();
		switch(direction) {
			case 'N':
				grid.goNorth();
				break;
			case 'S':
				grid.goSouth();
				break;
			case 'W':
				grid.goWest();
				break;
			case 'E':
				grid.goEast();
				break;
			case 'P':
				grid.revisitCell();
				usePotion(grid);
				break;
			default:
				break;
		}
	}

	/**
	 * Fight an enemy
	 * Take turns until enemy or character health < 0
	 *
	 * @param Enemy enemy
	 * @param Character character
	 */
	private void fight(Enemy enemy, Character character) {
		while(enemy.getHealth() > 0 && character.getHealth() > 0) {
			//clearScreen();
			System.out.println("Enemy health: " + enemy.getHealth() +
					" Enemy mana: " + enemy.getMana());
			System.out.println("Your health: " + character.getHealth() +
					" Your mana: " + character.getMana() + "\n");

			attack(enemy, character);
			enemyAttack(enemy, character);
		}
	}

	/**
	 * Character atack
	 *
	 * @param Enemy enemy
	 * @param Character character
	 */
	private void attack(Enemy enemy, Character character) {
		int damage = 0;
		System.out.println("Your turn! Choose the attack");
		Scanner sc = new Scanner(System.in);
		System.out.println(
			"(N)ormal attack/(I)ce spell/(F)ire spell, (E)arth spell or use (P)otion");
		char attack = sc.next().charAt(0);
			clearScreen();

		switch(attack) {
			case 'N':
				damage = character.getDamage();
				damage = enemy.receiveDamage(damage);

				if (damage == 0)
					System.out.println("The enemy dodged the attack");
				else
					System.out.println("You did " + damage + " damage");
				break;

			case 'I':
				if (character.getMana() < character.getSpecialAttackCost()) {
					System.out.println("Not enough mana!");
					attack(enemy, character);
					return;
				}

				character.consumeMana(character.getSpecialAttackCost());
				damage = (int)(character.getDamage() * 1.5);
				if (enemy.getIceRes()) {
					System.out.println("The enemy is imune to ice");
				} else {
					damage = enemy.receiveDamage(damage);
					if (damage == 0)
						System.out.println(
							"The enemy dodged the attack");
					else
						System.out.println(
							"You did " + damage + " damage");
				}
				break;

			case 'F':
				if (character.getMana() < character.getSpecialAttackCost()) {
					System.out.println("Not enough mana!");
					attack(enemy, character);
					return;
				}

				character.consumeMana(character.getSpecialAttackCost());
				damage = (int)(character.getDamage() * 1.5);
				if (enemy.getFireRes()) {
					System.out.println("The enemy is imune to fire");
				} else {
					damage = enemy.receiveDamage(damage);
					if (damage == 0)
						System.out.println(
							"The enemy dodged the attack");
					else
						System.out.println(
							"You did " + damage + " damage");
				}
				break;

			case 'E':
				if (character.getMana() < character.getSpecialAttackCost()) {
					System.out.println("Not enough mana!");
					attack(enemy, character);
					return;
				}

				character.consumeMana(character.getSpecialAttackCost());
				damage = (int)(character.getDamage() * 1.5);
				if (enemy.getEarthRes()) {
					System.out.println("The enemy is imune to earth");
				} else {
					damage = enemy.receiveDamage(damage);
					if (damage == 0)
						System.out.println(
							"The enemy dodged the attack");
					else
						System.out.println(
							"You did " + damage + " damage");
				}
				break;

			case 'P':
				usePotion(Grid.getMap());
				break;

			default:
				break;
		}

	}

	/**
	 * Play the enemy turn
	 * The enemy will choose a random attack type, based on the mana he has
	 *
	 * @param Enemy enemy
	 * @param Character character
	 */
	private void enemyAttack(Enemy enemy, Character character) {
		int damage = 0;
		Random rand = new Random();
		int attack = rand.nextInt(20);

		if (attack < 10) {
			damage = enemy.getDamage();
			damage = character.receiveDamage(damage);

			if (damage == 0)
				System.out.println("You dodged the attack");
			else
				System.out.println("The enemy did " + damage + " damage");
		}

		if (attack >= 11 && attack < 14) {
			if (enemy.getMana() < enemy.getSpecialAttackCost()) {
				enemyAttack(enemy, character);
				return;
			}

			enemy.consumeMana(character.getSpecialAttackCost());
			damage = (int)(enemy.getDamage() * 1.5);
			if (character.getIceRes()) {
				System.out.println("The enemy tried to use an Ice spell");
			} else {
				damage = character.receiveDamage(damage);
				if (damage == 0)
					System.out.println(
						"You dodged the attack");
				else
					System.out.println(
						"You received " + damage + " damage");
			}
		}

		if (attack >= 15 && attack < 18) {
			if (enemy.getMana() < enemy.getSpecialAttackCost()) {
				enemyAttack(enemy, character);
				return;
			}

			enemy.consumeMana(character.getSpecialAttackCost());
			damage = (int)(enemy.getDamage() * 1.5);
			if (character.getFireRes()) {
				System.out.println("The enemy tried to use a Fire spell");
			} else {
				damage = character.receiveDamage(damage);
				if (damage == 0)
					System.out.println(
						"You dodged the attack");
				else
					System.out.println(
						"You received " + damage + " damage");
			}
		}

		if (attack >= 18) {
			if (enemy.getMana() < enemy.getSpecialAttackCost()) {
				enemyAttack(enemy, character);
				return;
			}

			enemy.consumeMana(character.getSpecialAttackCost());
			damage = (int)(enemy.getDamage() * 1.5);
			if (character.getEarthRes()) {
				System.out.println("The enemy tried to use an Earth spell");
			} else {
				damage = character.receiveDamage(damage);
				if (damage == 0)
					System.out.println(
						"You dodged the attack");
				else
					System.out.println(
						"You received " + damage + " damage");
			}
		}

	}

	/**
	 * Print the available characters and let the player choose one
	 */
	public void chooseCharacter() {
		try {
			Path filePath = Paths.get("./json-test-files/accounts.json");
			String stringData = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
			//System.out.println(stringData);
			JSONObject jo = new JSONObject(stringData);
			//System.out.println(jo.toString());
			JSONArray acc = jo.getJSONArray("accounts");
			for (int i = acc.length() - 1; i >= 0; i --) {
				System.out.print("[" + i + "]: ");
				System.out.println(((JSONObject)(acc.get(i))).get("name"));
			}

			System.out.println("Choose an account (0 - " + (acc.length() - 1) + ")");
			Scanner sc = new Scanner(System.in);
			int accountIndex = sc.nextInt();
			clearScreen();

			JSONObject account = (JSONObject)(acc.get(accountIndex));
			JSONArray characters = account.getJSONArray("characters");

			System.out.println("You chose: " +
					account.get("name") +
					" from " + account.get("country") +
					" having " + account.get("maps_completed") +
					" maps completed");

			this.account = new Account(
					account.getJSONObject("credentials").getString("email"),
					account.getJSONObject("credentials").getString("password"),
					null,
					Integer.parseInt(account.getString("maps_completed")));

			System.out.println("For testing purposes, the password to your account is:" +
					this.account.getPassword());
			System.out.println("This is shown only for testing.");

			Console cnsl = System.console();
			String pass = cnsl.readLine("Please type the password.");

			if (pass.equals(this.account.getPassword())) {
				System.out.println("Welcome!");
			} else {
				System.out.println("Wrong password! Bye.");
				System.exit(1);
			}

			for (int i = characters.length() - 1; i >= 0; i --) {
				System.out.print("[" + i + "]: ");
				JSONObject ch = (JSONObject)(characters.get(i));
				System.out.println(ch.get("name") +
						", profession: " +
						ch.get("profession") +
						" level: " +
						ch.get("level") +
						" experience: " +
						ch.get("experience"));
			}
			System.out.println("Choose one of the avalaible characters (0 - " +
					(characters.length() - 1) + ")");
			int chIndex = sc.nextInt();
			clearScreen();
			
			JSONObject character = (JSONObject)(characters.get(chIndex));
			System.out.println("You chose: " + character.get("name"));

			Character chosenCh;
			String warriorType = character.getString("profession");
			//                     AccountFactory 
			chosenCh = AccountFactory.factory(warriorType,
						(String)(character.get("name")),
						((Integer)(character.get("experience"))).intValue(),
						Integer.parseInt(
							(String)(character.get("level"))));

			Grid grid = Grid.getMap();
			grid.setCharacter(chosenCh);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Let the player choose a potion and try to buy it
	 *
	 * @param Shop shop
	 * @param Character character
	 */
	private void buyPotion(Shop shop, Character character) {
		System.out.println("You have: " +
				character.addCoins(0) +
				" coins, " +
				character.getHealth() +
				" health and " +
				character.getMana() +
				" mana.");
		Scanner sc = new Scanner(System.in);

		int potionIndex = sc.nextInt();
			clearScreen();

		if (potionIndex < 0)
			return;

		Potion p = shop.getPotion(potionIndex);

		if (p == null)
			return;

		boolean bought = character.buyPotion(p);
		if (bought == false) {
			System.out.println("Not enough coins");
			return;
		} else {
			System.out.println("Bought potion " + p);
			return;
		}
	}

	/**
	 * Let the player choose a potion and use it
	 * @param Grid grid
	 */
	public void usePotion(Grid grid) {
		Character character = grid.getCharacter();
		Scanner sc = new Scanner(System.in);

		character.printPotions();
		System.out.println("You have " + character.addCoins(0) + " coins");
		System.out.println("Choose the index of the potion or -1 to skip");

		int index = sc.nextInt();
			clearScreen();

		if (index < 0)
			return;

		Potion p = character.getPotion(index);

		if (p == null) {
			System.out.println("There was a problem using the potion");
			return;
		}

		p.potionEffect(character);
		character.removePotion(p);

		System.out.println("You now have: " +
				character.getHealth() +
				" health and " +
				character.getMana() +
				" mana!");
	}

	/**
	 * Load cell stories from the stories.json file
	 */
	private void loadStories() {
		try {
			Path filePath = Paths.get("./json-test-files/stories.json");
			String stringData = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

			JSONObject jo = new JSONObject(stringData);
			JSONArray stories = jo.getJSONArray("stories");

			for (int i = 0; i < stories.length(); i ++) {
				JSONObject story = stories.getJSONObject(i);
				String cellType = story.getString("type");
				String cellStory = story.getString("value");

				if (cellType.equals(new String("EMPTY"))) {
					cellmap.get(CellEnum.EMPTY).add(cellStory);
				} else if (cellType.equals(new String("ENEMY"))) {
					cellmap.get(CellEnum.ENEMY).add(cellStory);
				} else if (cellType.equals(new String("SHOP"))) {
					cellmap.get(CellEnum.SHOP).add(cellStory);
				} else if (cellType.equals(new String("FINISH"))) {
					cellmap.get(CellEnum.FINISH).add(cellStory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print a story depending on the current cell of the player
	 */
	private void printStory(CellEnum currentCell) {
		ArrayList<String> stories = null;
		int index = 0;
		Random rand = new Random();
		switch (currentCell) {
			case EMPTY:
				stories = cellmap.get(CellEnum.EMPTY);
				index = rand.nextInt(stories.size());

				System.out.println(stories.get(index));
				break;
			case ENEMY:
				stories = cellmap.get(CellEnum.ENEMY);
				index = rand.nextInt(stories.size());

				System.out.println(stories.get(index));
				break;
			case SHOP:
				stories = cellmap.get(CellEnum.SHOP);
				index = rand.nextInt(stories.size());

				System.out.println(stories.get(index));
				break;
			case FINISH:
				stories = cellmap.get(CellEnum.FINISH);
				index = rand.nextInt(stories.size());

				System.out.println(stories.get(index));
				break;
			default:
				break;
		}
	}

	/**
	 * Save progress to JSON file.
	 * Upgrade the character and replace it with the old one in the file.
	 *
	 * @param Character character
	 */
	private void saveProgress(Character character) {
		int chXp = character.increaseXp(0);
		
		if (chXp >= 100) {
			chXp = chXp % 100;
			character.upgradeCharacter(chXp, character.getLevel() + 1);
		}

		try {
			Path filePath = Paths.get("./json-test-files/accounts.json");
			String stringData = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

			JSONObject jo = new JSONObject(stringData);
			JSONArray acc = jo.getJSONArray("accounts");

			for (int i = 0; i < acc.length(); i ++) {
				JSONObject obj = acc.getJSONObject(i);
				if (obj.getJSONObject("credentials").getString("email").equals(
						this.account.getEmail()) &&
					obj.getJSONObject("credentials").getString("password").equals(
						this.account.getPassword())) {

					JSONObject newAcc = new JSONObject(obj);
					JSONArray characters = obj.getJSONArray("characters");

					for (int j = 0; j < characters.length(); j ++) {
						JSONObject currCharacter = characters.getJSONObject(j);
						if (currCharacter.getString("name").equals(
							character.getName())) {
							
							currCharacter.remove("level");
							currCharacter.remove("experience");
							currCharacter.put("level",
								Integer.toString(character.getLevel()));
							currCharacter.put("experience", chXp);

							characters.remove(j);
							characters.put(currCharacter);
							break;
						}
					}

					newAcc.remove("characters");
					newAcc.put("characters", characters);
					newAcc.remove("credentials");
					newAcc.put("credentials", obj.getJSONObject("credentials"));
					newAcc.put("name", obj.getString("name"));
					newAcc.put("country", obj.getString("country"));
					int maps = Integer.parseInt(obj.getString("maps_completed")) + 1;
					newAcc.put("maps_completed", Integer.toString(maps));
					newAcc.put("favorite_games", obj.getJSONArray("favorite_games"));

					acc.remove(i);
					acc.put(newAcc);

					jo.remove("accounts");
					jo.put("accounts", acc);
					break;
				}
			}

			File accFile = new File("./json-test-files/accounts.json");
			accFile.delete();

			File newFile = new File("./json-test-files/accounts.json");
			newFile.createNewFile();
			FileWriter fw = new FileWriter("./json-test-files/accounts.json");
			PrintWriter pw = new PrintWriter(fw);
			pw.print(jo.toString());
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clear the screen.
	 * Works in terminal, not in IDE
	 */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

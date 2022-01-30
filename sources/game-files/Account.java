package p1;

import java.util.*;

public class Account {
	private Information info;
	private ArrayList<Character> characters;
	private int gameno;

	/**
	 * Constructs an Account object, setting the avalaible characters,
	 * the completed game numbers and the Information object
	 *
	 * @param email
	 * 		The user email
	 * @param password
	 * 		The user password
	 * @param characters
	 * 		The list of avalaible characters
	 * @param gameno
	 * 		The number of completed games
	 */
	public Account(String email, String password, ArrayList<Character> characters, int gameno){
		this.info = new Information(email, password);
		this.characters = characters;
		this.gameno = gameno;
	}

	/**
	 * @return String email
	 */
	public String getEmail() {
		return info.credentials.getEmail();
	}

	/**
	 * @return String password
	 */
	public String getPassword() {
		return info.credentials.getPassword();
	}

	class Information {
		Credentials credentials;
		Set favgames;

		/**
		 * Constructs a new Information object using the email and password
		 */
		public Information(String email, String password) {
			this.credentials = new Credentials(email, password);
		}

		/**
		 * Constructs a new Information object with null email and password
		 */
		public Information() {
			this.credentials = new Credentials();
		}
	}
}

class Credentials {
	private String email;
	private String password;

	/**
	 * Constructs a new Credentials object, using the email and password
	 *
	 * @param email
	 * 		The new credentials email
	 * @param password
	 * 		The new credentials password
	 */
	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructs a new Credentials object using NULL email and password
	 */
	public Credentials() {
		this(null, null);
	}

	/**
	 * Returns the current object email
	 *
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Returns the current object password
	 *
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the current object email
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the current object password
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}

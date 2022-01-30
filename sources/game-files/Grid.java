package p1;

import java.util.*;

public class Grid extends ArrayList<CellEnum[]> {
	private int lenght, width;
	private int currentLenght, currentWidth;
	private CellEnum currentCell;
	private Character character;
	private static Grid grid;
	private int[][] visited;

	/**
	 * Private constructor, creates a grid with the specified lenght and width
	 * using a given example
	 *
	 * @param lenght
	 * 		Grid lenght
	 * @param width
	 * 		Grid width
	 */
	private Grid(int lenght, int width) {
		super(lenght);
		this.lenght = lenght;
		this.width = width;
		this.currentWidth = 0;
		this.currentLenght = 0;

		CellEnum[] row1 = { CellEnum.EMPTY, CellEnum.EMPTY, CellEnum.EMPTY,
			CellEnum.SHOP, CellEnum.EMPTY};
		CellEnum[] row2 = { CellEnum.EMPTY, CellEnum.EMPTY, CellEnum.EMPTY,
			CellEnum.SHOP, CellEnum.EMPTY};
		CellEnum[] row3 = { CellEnum.SHOP, CellEnum.EMPTY, CellEnum.EMPTY,
			CellEnum.EMPTY, CellEnum.EMPTY};
		CellEnum[] row4 = { CellEnum.EMPTY, CellEnum.EMPTY, CellEnum.EMPTY,
			CellEnum.EMPTY, CellEnum.ENEMY};
		CellEnum[] row5 = { CellEnum.EMPTY, CellEnum.EMPTY, CellEnum.EMPTY,
			CellEnum.EMPTY, CellEnum.FINISH};

		this.add(row1);
		this.add(row2);
		this.add(row3);
		this.add(row4);
		this.add(row5);

		this.setCurrentCell();
		visited = new int[lenght][width];
		visited[0][0] = 1;
	}

	/**
	 * Private constructor, creates a new Grid with lenght = width = 0
	 */
	private Grid() {
		this(0, 0);
	}

	/**
	 * Static method, if a grid already exists, return it
	 * if not, create a new grid of lenght = width = 5
	 *
	 * @return the grid object
	 */
	public static Grid getMap() {
		if (grid != null)
			return grid;
		grid = new Grid(5, 5);
		return grid;
	}

	/**
	 * Move the current cell North
	 * If already at the edge of the map, print a message and return
	 */
	public void goNorth() {
		if (this.currentWidth <= 0) {
			System.out.println("Cannot go North, character at the edge of the map.");
			return;
		}

		this.currentWidth --;
		this.setCurrentCell();
		visited[currentLenght][currentWidth] += 1;
	}

	/**
	 * Move the current cell South
	 * If already at the edge of the map, print a message and return
	 */
	public void goSouth() {
		this.currentWidth ++;
		if (this.currentWidth >= this.width) {
			this.currentWidth --;
			System.out.println("Cannot go South, character at the edge of the map.");
			return;
		}

		this.setCurrentCell();
		visited[currentLenght][currentWidth] += 1;
	}

	/**
	 * Move the current cell West
	 * If already at the edge of the map, print a message and return
	 */
	public void goWest() {
		if (this.currentLenght <= 0) {
			System.out.println("Cannot go West, character at the edge of the map.");
			return;
		}

		this.currentLenght --;
		this.setCurrentCell();
		visited[currentLenght][currentWidth] += 1;
	}

	/**
	 * Move the current cell East
	 * If already at the edge of the map, print a message and return
	 */
	public void goEast() {
		this.currentLenght ++;
		if (this.currentLenght >= this.lenght) {
			this.currentLenght --;
			System.out.println("Cannot go East, character at the edge of the map.");
			return;
		}

		this.setCurrentCell();
		visited[currentLenght][currentWidth] += 1;
	}

	/**
	 * @return the current cell type
	 */
	public CellEnum getCell() {
		return currentCell;
	}

	/**
	 * Prints the grid
	 * If a cell is not discovered, prints '?'
	 */
	public void printGrid() {
		for (int i = 0; i < this.size(); i ++) {
			CellEnum current[] = this.get(i);
			for (int j = 0; j < current.length; j ++) {
				if (visited[j][i] == 0)
					System.out.print("? ");
				else if (i == currentWidth && j == currentLenght)
					System.out.print("P ");
				else if (current[j] == CellEnum.EMPTY)
					System.out.print("N ");
				else if (current[j] == CellEnum.ENEMY)
					System.out.print("E ");
				else if (current[j] == CellEnum.SHOP)
					System.out.print("S ");
				else if (current[j] == CellEnum.FINISH)
					System.out.print("F ");
			}
			System.out.println("");
		}
	}

	/**
	 * Set the current cell type
	 */
	private void setCurrentCell() {
		currentCell = this.get(currentWidth)[currentLenght];
	}

	/**
	 * @return the current character
	 */
	public Character getCharacter() {
		return this.character;
	}

	/**
	 * Set the current character
	 *
	 * @param character
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}

	/**
	 * Checks if the current cell is visited
	 *
	 * @return
	 * 		0  - if the cell is not discovered
	 * 		positive - if the cell is discovered
	 */
	public int getVisited() {
		return visited[currentLenght][currentWidth];
	}

	/**
	 * Increase number of times the current cell was visited
	 */
	public void revisitCell() {
		visited[currentLenght][currentWidth]++;
	}
}

package p1;

import java.util.*;

// TODO - Doc
public class Cell {
	private CellEnum cellenum;
	private int ox, oy;
	private CellElement celltype;
	private boolean visited;
}

interface CellElement {
	abstract char toCharacter();
}

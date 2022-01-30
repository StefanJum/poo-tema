//package p1;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import org.json.*;
import p1.*;

public class Test {
	public static void main(String[] argv) {
		Grid grid = Grid.getMap();
		Game game = new Game();
		game.runHard(grid);
		//game.run(grid);
	}
}

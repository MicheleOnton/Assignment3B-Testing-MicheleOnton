
/**
 * Tests for the GameState class in the checkers game.
 * Author: Michele Onton
 * Date: 10-20-2025
 */

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    /**
     * Helper method that writes a temporary board file to disk.
     * @param content the 8-line string representing the board
     * @return the absolute path to the temporary file
     * @throws IOException if the file cannot be created
     */
	
    private String makeBoardFile(String content) throws IOException {
    	
        File f = File.createTempFile("board", ".txt");
        try (FileWriter w = new FileWriter(f)) { w.write(content); }
        return f.getAbsolutePath();
          
    }

    /**
     * Tests that a valid board file can be loaded successfully
     * and that it contains the correct number of cells.
     * @throws Exception if file creation or loading fails
     */
    
    @Test
    void testFromFileAndCells() throws Exception {
    	
        String board = "r-------\n".repeat(8);
        GameState s = GameState.fromFile(makeBoardFile(board), false);
        assertEquals(64, s.getCells().size());
        assertTrue(s.getCells().contains(Cell.RED));
        
    }

    /**
     * Tests that a file with invalid characters throws an IllegalArgumentException.
     * @throws Exception if file creation fails
     */
   
    @Test
    void testInvalidBoardFile() throws Exception {
    	
        String bad = "x-------\n".repeat(8);
        assertThrows(IllegalArgumentException.class,
                () -> GameState.fromFile(makeBoardFile(bad), true));
        
    }

    /**
     * Tests that nextMovesFromCell executes safely even if there are no valid moves.
     * This ensures no crashes or null returns from the method.
     * @throws Exception if file creation or loading fails
     */
    
    @Test
    void testNextMovesFromCellSafe() throws Exception {
    	
        String board =
                "--------\n" +
                "--------\n" +
                "--------\n" +
                "---r----\n" + // red in the middle (D4)
                "--------\n" +
                "--------\n" +
                "--------\n" +
                "--------\n";
        GameState s = GameState.fromFile(makeBoardFile(board), false);
        var moves = s.nextMovesFromCell("D4");
        assertNotNull(moves);
        
    }

    /**
     * Tests that the toString method formats the board correctly
     * and includes expected headers such as column labels.
     * @throws Exception if file creation or loading fails
     */
    
    @Test
    void testBoardToStringFormat() throws Exception {
    	
        String board = "r-------\n".repeat(8);
        GameState s = GameState.fromFile(makeBoardFile(board), true);
        String str = s.toString();
        assertTrue(str.contains("A B C D E F G H"));
        
    }
}



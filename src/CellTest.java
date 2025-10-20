
/**
 * Tests for the Cell enum in the checkers game.
 * Author: Michele Onton
 * Date: 10-20-2025
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    /**
     * Tests that red and black pieces are correctly identified as their respective colors,
     * and that empty cells return false for both color checks.
     */
	
    @Test
    void testColorChecks() {
    	
        assertTrue(Cell.RED.isRedPiece());
        assertTrue(Cell.BLACK.isBlackPiece());
        assertFalse(Cell.EMPTY.isRedPiece());
        assertFalse(Cell.EMPTY.isBlackPiece());
        
    }

    /**
     * Tests that red and black (including kings) are identified as opponents
     * and that same-color cells are not considered opponents.
     */
    
    @Test
    void testOpponentLogic() {
    	
    	assertTrue(Cell.RED.isOpponent(Cell.BLACK));
        assertTrue(Cell.RED_KING.isOpponent(Cell.BLACK_KING));
        assertFalse(Cell.RED.isOpponent(Cell.RED));
        assertFalse(Cell.BLACK.isOpponent(Cell.BLACK_KING));
        		
    }

    /**
     * Tests that promotion occurs when red or black pieces reach their respective end rows,
     * and that no promotion occurs for pieces in the middle of the board.
     */
    
    @Test
    void testPromotion() {
      
    	assertEquals(Cell.RED_KING, Cell.RED.promoteIfReachedEnd(56));  
        assertEquals(Cell.BLACK_KING, Cell.BLACK.promoteIfReachedEnd(0)); 
        assertEquals(Cell.RED, Cell.RED.promoteIfReachedEnd(20));
                
    }

    /**
     * Empty cells are correctly identified,
     * and verifies symbol representations for red and black pieces.
     */
    
    @Test
    void testIsEmptyAndToString() {
    	
        assertTrue(Cell.EMPTY.isEmpty());
        assertEquals("●", Cell.RED.toString());
        
    }
}


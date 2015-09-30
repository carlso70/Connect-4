import java.util.*;

public class Connect4 {

	public static final char NONE = ' ';
	public static final char RED = 'O';
	public static final char YELLOW = 'X';

        char[][] board;

       /**
	 * Initializes the instance variables.
	 */
	public Connect4() {
        board = new char[6][7];

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = NONE;

	}

       /**
	 * Returns a copy of the current board
	 * @return a char matrix
	 */
	public char[][] getBoard() {
		char [][] boardCopy = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				boardCopy[i][j] = board[i][j];
		return boardCopy;
	}

	/**
	 * Put a piece of the given color in the given column
         * The function returns the row where the piece have been
         * put. If the column is full it return -1.
	 * 
	 * @param column a column of the board
	 * @param color RED or YELLOW
	 */
	public int putPiece(int column, char color) {
        if (board[0][column] == RED || board[0][column] == YELLOW)
                return -1;

        int i = board.length - 1;
        while (board[i][column] != NONE) { //goes up column and checks till row where there is a piece
            i --;
        }
		board[i][column] = color;

        return i;  //returns the row above the piece found
	}

	/**
	 * Print the screen in the standard output
	 */
	public void printScreen() {
               // Make the header of the board
		System.out.printf("\n ");
		for (int i = 0; i < board[0].length; ++i)
			System.out.printf("   %d", i);
		System.out.println();

		System.out.printf("  ");
		for (int i = 0; i < board[0].length; ++i)
			System.out.printf("----");
		System.out.println("-");

		// Print the board contents
		for (int i = 0; i < board.length; ++i) {
			System.out.printf("%c ", 'A' + i);
			for (int k = 0; k < board[0].length; ++k)
				System.out.printf("| %c ", board[i][k]);
			System.out.println("|");

			// print the line between each row
			System.out.printf("  ");
			for (int k = 0; k < board[0].length; ++k)
				System.out.printf("----");
			System.out.println("-");
		}
	}

	/**
	 * Check if an alignment has been made using the given tile
	 * 
	 * @param row
	 * @param column
	 * @return the color if there is an alignment, NONE otherwise.
	 */
	public char checkAlignment(int row, int column) {
		char winner = NONE;
		int count = 0;

		for (int i = 0; i < board[0].length - 1; i++) { //checks horizontal
			if (board[row][column] == board[row][i+1])
				count++;
			else {
				count = 0;
			}
			if (count >= 3) {
				return board[row][column];
			}

		}

		for (int j = 0; j < board.length - 1; j++) { //checks vertically
			if(board[row][column] == board[j + 1][column]) {
				count++;
			}
			else {
				count = 0;
			}
			if (count >= 3) {
				return board[row][column];
			}
		}

		int sum = row + column;


		for (int i = 0; i < board[0].length - 1 && i < board.length - 1; i++)
			if (board[row][column] == board[sum - i][i]) {
				count++;
			}
			else {
				count = 0;
			}
			if (count >= 4) {
				return board[row][column];
			}

		return winner;


	}
	
	/**
	 * Launch the game for one game.
	 */
	public void play() {
		char currentPlayer = RED;

		// Begin playing the game
		Scanner in = new Scanner(System.in);
		int col = -1;
		int row = -1;
		
		do {
			currentPlayer = currentPlayer == RED ? YELLOW : RED;
			this.printScreen();
			System.out.printf("Current player: '%c'\n", currentPlayer);

			// read and validate the input
			col = -1;
			row = -1;
			
			do {
				System.out.printf("Choose a column: ");
				String line = in.nextLine();
				

				if (line == null || line.length() != 1) {
                                        // Invalid input, reask for one.
					continue;
				}

				col = line.charAt(0) - '0';
				row = this.putPiece(col, currentPlayer);

			} while (row < 0);

		} while (this.checkAlignment(row, col) == NONE);

		this.printScreen();
		System.out.printf("\n!!! Winner is Player '%c' !!!\n", currentPlayer);
		in.close();

	}
}
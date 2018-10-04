public class TicTacToeModel {

	public int GRIDSIZE = 3; //Size of Grid.

	public boolean gameOver = false;

	public Boolean gameBoard[][] = new Boolean[GRIDSIZE][GRIDSIZE]; // Map of the grid. True = X, False = O.
/**
 * Sets all cell of map to null when constructed.
 */
	public TicTacToeModel() {
		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				gameBoard[row][column] = null;
			}
		}
	}
/**
 * Updates the map with boolean isCross at location [row][column]. This info comes from View.
 * @param row - integer of the row of the button that was pressed.
 * @param column - integer of the column of the button that was pressed.
 * @param isCross - boolean of the symbol, true = X, false = O.
 */
	public void updateBoard(int row, int column, boolean isCross) {
		gameBoard[row][column] = isCross;
		isGameOver(); // Checks if someone won.
	}
/**
 * Goes through the map to check if there's three in a row according to the rules of tic tac toe.
 */
	public void isGameOver() {
		boolean isTheSameSymbol = false;
		boolean isDiagonal = false;
		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < (GRIDSIZE - 1); column++) {
				if (gameBoard[row][column] == gameBoard[row][column + 1] && gameBoard[row][column] != null) {
					isTheSameSymbol = true;
				} else if (gameBoard[column][row] == gameBoard[column + 1][row] && gameBoard[column][row] != null) {
					isTheSameSymbol = true;
				} else {
					isTheSameSymbol = false;
					break;
				}
			}
			if (isTheSameSymbol) {
				gameOver = true;
				break;
			}
		}
		//Checks the diagonal of grid
		for (int i = 0; i < (GRIDSIZE - 1); i++) {
			if (gameBoard[i][i] == gameBoard[i + 1][i + 1] && gameBoard[i][i] != null) {
				isDiagonal = true;
			} else if (gameBoard[GRIDSIZE - 1 - i][i] == gameBoard[GRIDSIZE - 2 - i][i + 1]
					&& gameBoard[GRIDSIZE - 1 - i][i] != null) {
				isDiagonal = true;
			} else {
				isDiagonal = false;
				break;
			}
		}
		if (isDiagonal) {
			gameOver = true;
		}
	}
}
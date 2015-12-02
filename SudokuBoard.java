/**
*	Brendan Raimann
*	12/2/15
*	This program solves any solvable Sudoku Board
*	Version 1.0
*/
public class SudokuBoard
{
	/**	An array to store the values of the board*/
	private int[][] gameBoard;
	
	/**
	*	Constructor with the file to be copied as a parameter
	*	@param fileName The csv file to be read and copied
	*/
	public SudokuBoard(String fileName)
	{
		gameBoard = new int[9][9];
		ReadCSV Reader = new ReadCSV(fileName);
		String[] temp = Reader.get();
		for (int i = 0; i < 9; i++)
		{
			for (int x = 0; x < 9; x++)
			{
				if (Character.isDigit(temp[(i * 9) + x].charAt(0))) //checks if the char is a digit
					gameBoard[i][x] = Integer.parseInt(temp[(i * 9) + x]);
				else
					gameBoard[i][x] = 0;
			}
		}
	}
	
	/**
	*	Copy Constructor
	*	@param b Another SudokuBoard to be copied
	*/
	public SudokuBoard(SudokuBoard b)
	{
		gameBoard = new int[9][9];
		for (int i = 0; i < 9; i++)
			for (int x = 0; x < 9; x++)
				gameBoard[i][x] = b.get(i,x);
	}
	
	/**
	*	Places a value at a specified location
	*	@param r The row for the value to placed
	*	@param c The column for the value to placed
	*	@param n The value to be placed on the board
	*/
	public void place(int r, int c, int n)
	{
		gameBoard[r][c] = n;
	}
	
	/**
	*	Prints the board
	*/
	public void print()
	{
		System.out.print(toString());
	}
	
	/**
	*	Returns the value at a specified location on the board
	*	@param r The row of the value
	*	@param c The column of the value
	*	@return Returns the value at a specified location on the board
	*/
	public int get(int r, int c)
	{
		return gameBoard[r][c];
	}
	
	/**
	*	Removes a value at a specified location on the board
	*	@param r The row of the value to be removed
	*	@param c The column of the value to be removed
	*/
	public void remove(int r,int c)
	{
		gameBoard[r][c] = 0;
	}
	
	/**
	*	Returns true if the given value can be placed at a specified location on the board
	*	@param r The row for the value to be potentially placed
	*	@param c The column for the value to be potentially placed
	*	@param n The value
	*	@returns Returns whether or not n could possibly be placed at the row and column
	*/
	public boolean canPlace(int r, int c, int n)
	{
		if (gameBoard[r][c] != 0)
				return false;
		for (int i = 0; i < 9; i++)
			if (gameBoard[r][i] == n || gameBoard[i][c] == n)
				return false;
		for (int i = 0; i < 3; i++)
			for (int x = 0; x < 3; x++)
				if (gameBoard[i+((r/3)*3)][x+((c/3)*3)] == n) //e.g. the location {8,7} would become {6,6}, then adding i and x checks the rest of the section 
					return false;
		return true;
	}
	
	/**
	*	Returns a String representation of the board
	*	@return Returns a String representation of the board
	*/
	public String toString()
	{
		String output = "-------------  -------------  -------------";
		for (int i = 0; i < 9; i++)
		{
			if (i != 0 && i % 3 == 0)
				output += "\n-------------  -------------  -------------";
			output += "\n|";
			for (int x = 0; x < 9; x++)
			{
				if (x != 0 && x % 3 == 0)
					output += "  |";
				output += " " + gameBoard[i][x] + " |";
			}
			output += "\n-------------  -------------  -------------";
		}
		return output + "\n";
	}
	
	/**
	*	Returns true if the board is solved
	*	@return Returns whether or not the board is solved
	*/
	public boolean solved()
	{
		for (int i = 0; i < 9; i++)
			for (int x = 0; x < 9; x++)
				if (gameBoard[i][x] == 0)
					return false;
		return true;
	}
	
	/**
	*	Returns the coordinates of the most constrained spot on the board with an int array
	*	The first value of the array is the row, the second value is the column
	*	@returns Returns the location of the most constrained spot on the board
	*/
	public int[] mostConstrained()
	{
		int[] temp = {0,0};   //row, col
		int best = 9;       //amount of possible values
		int count = 0;			//temporary summation of possibilities
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				count = 0;
				for (int n = 1; n <= 9 && gameBoard[row][col] == 0; n++)  //num
				{
					if (canPlace(row, col, n))
						count++;
				}
				if (count > 0 && count < best)
				{
					best = count;
					temp[0] = row;
					temp[1] = col;
				}
			}
		}
		return temp;
	}
}
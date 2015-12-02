/**
*	Brendan Raimann
*	12/2/15
*	This program solves any solvable Sudoku Board
*	Version 1.0
*/
public class Solver
{
	public static void main (String [] args)
	{
		//HOW TO CALL DIFFERENT FILES
		//With no arguments, the default file is Puzzle1.csv
		//If there is an argument, the file is the arg + .csv (if arg[0] is 'Test', the file will be Test.csv
		
		String fileName = "Puzzle1.csv";
		if (args.length > 0)
			fileName  = args[0] + ".csv";
		
		SudokuBoard board = new SudokuBoard(fileName);
		Stack<SudokuBoard> stack = new LinkedList<SudokuBoard>();
		stack.push(board);
		SudokuBoard temp;
		while(stack.isEmpty() == false && stack.peek().solved() == false)
		{
			temp = stack.pop();
			int[] loc = temp.mostConstrained();  //{row, col}
			for (int n = 1; n <= 9; n++)
			{
				if (temp.canPlace(loc[0],loc[1],n))
				{
					temp.place(loc[0],loc[1],n);
					stack.push(temp);
					temp = new SudokuBoard(temp);
					temp.remove(loc[0],loc[1]);
				}
				
			}
		}
		if (stack.isEmpty())
			System.out.println("This puzzle is unsolvable");
		else
		{
			stack.peek().print();
			System.out.println("Successfully solved!");
		}
	}
}
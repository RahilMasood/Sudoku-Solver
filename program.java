class program
{
    public static void print(int board[][])
    {
        //Printing the board seperating the board into 9 boxes
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                System.out.print(board[i][j]+"  ");
                if((j+1) % 3 == 0 && j < 6)
                    System.out.print("| ");
            }
            System.out.println();
            if((i+1) % 3 == 0 && i < 6)
                System.out.println("---------+----------+---------");
        }
    }


    public static boolean solve(int board[][])
    {
        int r = -1, c  = -1;
        boolean ch = true;
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                //Checking for empty spaces in the board
                if (board[i][j] == 0) 
                {
                    r = i;
                    c = j;
                    ch = false;
                    break;
                }
            }
        }

        //Returning true if Case solved
        if (ch)
            return true;
        
        for (int n = 1; n <= 9; n++)
        {
            if (check(board, r, c, n))
            {
                board[r][c] = n;
                //backtracking algorithm
                if (solve(board))
                    return true;
                else 
                    board[r][c] = 0;
            }
        }
        
        //In case an unsolvable puzzle given
        return false;
    }


    public static boolean check(int board[][], int r, int c, int n)
    {
        for (int x = 0; x < 9; x++) 
        {
            //Checking for same number in the row and column
            if (board[r][x] == n)
                return false;
            if (board[x][c] == n)
                return false;
        }

        //getting starting index of the box
        int r1 = r - r % 3;
        int c1 = c - c % 3;
        for (int i = r1; i < r1 + 3; i++) 
        {
            for (int j = c1; j < c1 + 3; j++) 
                //Checking if same number present in the box
                if (board[i][j] == n) 
                    return false;
        }
        return true;
    }


    public static void main(String args[])
    {
        //Taking Arto Inkala's board, considered the hardest solveable sudoku puzzle in the world.
        int board[][] = new int[][]
        {
            {8, 0, 0,      0, 0, 0,        0, 0, 0},
            {0, 0, 3,      6, 0, 0,        0, 0, 0},
            {0, 7, 0,      0, 9, 0,        2, 0, 0},
            
            {0, 5, 0,      0, 0, 7,        0, 0, 0},
            {0, 0, 0,      0, 4, 5,        7, 0, 0},
            {0, 0, 0,      1, 0, 0,        0, 3, 0},
            
            {0, 0, 1,      0, 0, 0,        0, 6, 8},
            {0, 0, 8,      5, 0, 0,        0, 1, 0},
            {0, 9, 0,      0, 0, 0,        4, 0, 0}
        };
        if (solve(board)) 
            print(board);
        else
            System.out.println("No solution");
    }
}

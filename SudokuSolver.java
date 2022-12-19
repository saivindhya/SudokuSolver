import java.util.Scanner;
import java.lang.System;

public class SudokuSolver {
	
	public static boolean isSafe(int[][] board, int row,int col,int number) {
		for(int i=0;i<9;i++) {
			if(board[i][col] == number)
				return false;
		}
		for(int i=0;i<9;i++) {
			if(board[row][i]==number)
				return false;
		}
		int srow = (row/3)*3;
		int scol = (col/3)*3;
		for(int i=srow;i<srow+3;i++) {
			for(int j=scol;j<scol+3;j++) {
				if(board[i][j]==number) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean sudokuBacktracking(int[][] board,int row,int col) {
		if(row ==board.length) {
			return true;
		}
		
		int nrow = 0;
		int ncol = 0;
		if(col != board.length-1) {
			nrow = row;
			ncol = col+1;
		}
		else {
			nrow = row+1;
			ncol = 0;
		}
		
		if(board[row][col] != 0) {
			if(sudokuBacktracking(board, nrow,ncol)) {
				return true;
			}
		}
		else {
				for(int i=1;i<=9;i++) {
					if(isSafe(board,row,col,i)) {
						board[row][col] = i;
						if(sudokuBacktracking(board, nrow,ncol)) {
							return true;
						}
						else {
							board[row][col] = 0;
						}
					}
				}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner ob = new Scanner(System.in);
		System.out.println("Welcome to Sudoku! Enter your grid and get the answer");
		while(true) {
			System.out.println("\nPress Y if you wanna play sudoku. \nPress N if you want to exit from the game.\n");
			char choice = ob.next().charAt(0);
			if(choice=='Y' || choice == 'y') {
				int[][] board = new int[9][9];
				for(int i=0;i<9;i++) {
					System.out.println("Enter row"+(i+1)+" numbers, if there is empty cell enter 0");
					for(int j=0;j<9;j++) {
						board[i][j] = ob.nextInt();
					}
				}
				System.out.println("Before playing");
				for(int i=0;i<9;i++) {
					if(i%3==0 && i!=0) {
						System.out.println("---------------------");
					}
					for(int j=0;j<9;j++) {
						if(j%3==0 && j!=0)
							System.out.print("|"+" ");
						System.out.print(board[i][j]+" ");
					}
					System.out.println();	
				}
				System.out.println("\nAfter playing");
				sudokuBacktracking(board,0,0);
				for(int i=0;i<9;i++) {
					if(i%3==0 && i!=0) {
						System.out.println("---------------------");
					}
					for(int j=0;j<9;j++) {
						if(j%3==0 && j!=0)
							System.out.print("|"+" ");
						System.out.print(board[i][j]+" ");
					}
					System.out.println();	
				}
			}
			else if(choice == 'N' || choice == 'n'){
				System.exit(0);
			}
			else {
				System.out.println("Press only either Y or N");
			}
		}
		
	}
}

import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.*;

class Minesweeper{
	public static void main(String[] args){
		Board board = new Board();
	}
}

class Board{

	int height;
	int width;
	int bombs;
	int[][] board;
	
	public Board(){
		initVals();
		initBoard();
		printBoard();
	}
	
	private void initVals(){
		Scanner scan = new Scanner(System.in);
		boolean valid = true;
		System.out.println("How many rows?");
		height = getIntSafe(scan);
		System.out.println("How many cols?");
		width = getIntSafe(scan);
		System.out.println("How many bombs?");
		do{
		bombs = getIntSafe(scan);
		if (bombs > height * width){
			System.out.println("Please input a number less than " + (height * width));
		}
		}while(bombs > height * width);
	}
	
	private int getIntSafe(Scanner scan){
		boolean valid = true;
		int val = 0;
		do{
			valid = true;
			try{
				val = Integer.parseInt(scan.nextLine());
			}catch(Exception e){
				System.out.println("Please input an integer");
				valid = false;
			}
		}while(valid == false);
		return val;
	}
	
	private void initBoard(){
		Random rand = new Random();
		board = new int[height][width];
		for (int i = 0; i < bombs; i ++){
			//populating the board with mines
			//not efficient when bombs is close to boardsize
			int mine_y = 0;
			int mine_x = 0;
			do{
				mine_y = rand.nextInt(height);
				mine_x = rand.nextInt(width);
			}while (board[mine_y][mine_x] == 1);
			board[mine_y][mine_x] = 1;
		}
	}
	
	private void printBoard(){
		for (int i = 0; i < height; i ++){
			for (int j = 0; j < width; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}

//class GameFrame extends Frame{}
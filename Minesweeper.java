import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

/*
BOARD VALUES
0 no mine
1 mine
2 no mine exposed
3 flagged
*/

class Minesweeper{
	public static void main(String[] args){
		Game game = new Game();
	}
}

class Game{

	int height;
	int width;
	int bombs;
	int[][] board, boardstate;
	GameFrame gameFrame;
	
	public Game(){
		initVals();
		initBoard();
		initGameBoard();
		
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
		boardstate = new int[height][width];
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
	
	private void initGameBoard(){
		gameFrame = new GameFrame(this, board);
	}
	
	public void spaceClicked(int y, int x, int mouseButton){
		if (mouseButton == 1)
			checkSpace(y, x);
		if (mouseButton == 3)
			flagSpace(y, x);
	}
	
	private void checkSpace(int y, int x){
		switch (board[y][x]){
			case 0:
				System.out.println("no bomb");
				break;
			case 1:
				System.out.println("bomb");
				break;
			default:
				System.out.println("something else");
		}
		return;
	}
	
	private void flagSpace(int y, int x){
		
	}
}

class GameFrame extends Frame{
	Game game;
	Button[][] spaces;
	int height, width;
	int[][] board;
	
	public GameFrame(Game game, int board[][]){
		height = board.length;
		width = board[0].length;
		this.board = board;
		this.game = game;
		
		initFrame();
		initButtons();
	}
	
	private void initFrame(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
				System.exit(0);
			}
		});
		
		setSize(100 + 25 * height, 100 + 25 * width);
		setLayout(null);
		setVisible(true);
	}
	
	private void initButtons(){
		spaces = new Button[height][width];
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				final int y_coord = i;
				final int x_coord = j;
				spaces[i][j] = new Button();
				spaces[i][j].setSize(23, 23);
				spaces[i][j].setLocation(50 + i * 25, 50 + j * 25);
				
				spaces[i][j].addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						game.spaceClicked(y_coord, x_coord, e.getButton());
					}
				});
				add(spaces[i][j]);
			}
		}
		validate();
	}
}
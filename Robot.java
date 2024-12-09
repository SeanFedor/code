import java.io.*;


public class Robot {
	public enum Command{
	MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN
}
	
	//init robot board coordinates & load board
	private int x;
	private int y;
	private boolean[][] board;
	private GenericQueue<Command> commandQueue;
	
	public Robot(int boardW, int boardH) {
		this.board = new boolean[boardH][boardW];
		this.commandQueue = new GenericQueue<>();
		
		if (!board[0][0]) {
			this.x = 0;
			this.y = 0;
		}
	}
	
	public void loadBoard(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;
		int row = 0;
		
		while ((line = reader.readLine()) != null && row < board.length) {
			for (int col = 0; col < line.length() && col < board[row].length; col++) {
				board[row][col] = (line.charAt(col) == 'X'); //obstacle marker
			}
			row++;
		}
		reader.close();
	}
	//cmd file load, if-else ladder for each direction, throwing unknown at final else
	public void loadCommands(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String command;
		
		while ((command = reader.readLine()) != null) {
			command = command.trim().toUpperCase();
			if (command.equals("MOVE LEFT")) {
				commandQueue.enqueue(Command.MOVE_LEFT);
			} else if (command.equals("MOVE RIGHT")) {
				commandQueue.enqueue(Command.MOVE_RIGHT);
			} else if (command.equals("MOVE UP")) {
				commandQueue.enqueue(Command.MOVE_UP);
			} else if (command.equals("MOVE DOWN")) {
				commandQueue.enqueue(Command.MOVE_DOWN);
			} else {
				System.out.println("Unknown command: " + command);
			}
		}
		reader.close();
	}
	private void printBoard()	{
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[row].length; col++) {
					if (row == y && col == x) {
						System.out.print("O"); //robot
					} else if (board[row][col]) {
							System.out.print("X"); //obstacle
					} else {
						System.out.print("_"); //empty space
						}
					}
				System.out.println();
				}
			System.out.println();
			
		}

	public void executeCMD() {
		while (!commandQueue.empty()) {
			Command command = commandQueue.dequeue();
			//debug command to show current position before move
			 //System.out.println("Current position before move: (" + x + ", " + y + ")");
			 
		switch (command) {
			case MOVE_LEFT -> x -= 1;
			case MOVE_RIGHT -> x += 1;
			case MOVE_UP -> y -= 1;
			case MOVE_DOWN -> y += 1;
		}
		//debug commands to show new position on board
		//System.out.println("New position after move: (" + x + ", " + y + ")");
		
		if (x < 0 || x >= board[0].length || y < 0 || y >= board.length || board[y][x]) {
			System.out.println("CRASH");
			System.exit(1);
		} else {
			printBoard();
		}
	}
}		
}
		
	







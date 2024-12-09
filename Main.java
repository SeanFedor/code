import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		boolean keepgoing = true;
		
		
		while (keepgoing) {
		//using scanner, read user's commands.txt and board.txt file
		System.out.println("Welcome to the robot minefield evader 3000");
		System.out.println("Please enter a file path (or current directory file) initializing the board");
		String boardFilePath = scanner.nextLine();
		
		System.out.println("Now enter a file path (or current directory file) giving the minefield evader commands");
		String cmdsFile = scanner.nextLine();
		
		Robot robot = new Robot(10, 10);
		
		robot.loadBoard(boardFilePath);
		robot.loadCommands(cmdsFile);
		
		robot.executeCMD();
		
		System.out.println("Would you like to enter another board and command set for the minefield evader? (y/n)?: ");
		String response = scanner.nextLine();
		if (!response.equals("y")) {
			keepgoing = false;
		}
		
		
	}
		System.out.println("Thanks for using the minefield evader 3000. Give us a 5-star review on yelp!");
		scanner.close();
		System.exit(0);
	}
}

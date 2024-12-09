//SF
import java.io.*;
import java.util.*;

public class Showcase {

	//main, store file contents into array for prizes and act as frontend
	public static void main(String[] args) {
		String file = "prizes.txt";
		
		String[] prizeNames = new String[100];
		double[] prizePrices = new double[100];
		int prizeCount = readPrizesFromFile(file, prizeNames, prizePrices);
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			//set prizes
			String[] showcaseNames = new String[5];
			double[] showcasePrices = new double[5];
			double totalValue = selectShowcasePrizes(prizeNames, prizePrices, prizeCount, showcaseNames, showcasePrices);
			
			//Display names and determine input
			System.out.println("\nShowcase Prizes:");
			for (int i = 0; i < showcaseNames.length; i++)	{
				System.out.println("- " + showcaseNames[i]);
			}
			
			System.out.println("\nGuess total showcase value: $");
			double guess = Double.parseDouble(scanner.nextLine());
			
			if (guess >= totalValue - 1300 && guess <= totalValue)	{
				System.out.println("Correct! Total value was $" +totalValue);
			} else {
				System.out.println("Wrong. Total value was $" + totalValue);
			}
			
			System.out.print("\nplay again (yes/no): ");
			String response = scanner.nextLine();
			if (!response.equals("yes")) {
				System.out.println("Thanks for playing!");
				break;
			}
		}
		
		scanner.close();
	}
	
	//read from file and store
	private static int readPrizesFromFile(String file, String[] prizeNames, double[] prizePrices) {
		int count = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\t");
				if (parts.length == 2) {
					String name = parts[0];
					String priceString = parts[1];
					prizeNames[count] = name;
					prizePrices[count] = Double.parseDouble(priceString);
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("prize file not found");
		} catch (IOException e) {
			System.out.println("Cannot read file");
		}
		return count;
	}
	
	//method selecting prizes from file
	private static double selectShowcasePrizes(String[] prizeNames, double[] prizePrices, int prizeCount, String[] showcaseNames, double[] showcasePrices) {
		Random random = new Random();
		boolean[] selected = new boolean[prizeCount];
		double totalValue = 0;
		
		for (int i = 0; i < 5; i ++) {
			int index;
			do {
				index = random.nextInt(prizeCount);
			} while (selected[index]);
			selected[index] = true;
			showcaseNames[i] = prizeNames[index];
			showcasePrices[i] = prizePrices[index];
			totalValue += showcasePrices[i];
		}
		
		return totalValue;
	}
}









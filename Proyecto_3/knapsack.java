// Proyecto 3
// Arthur Alves Araujo Ferreira
// A01022593

import java.io.*;
import java.util.*;

class knapsack {
	static int maxWeight, nitems;
	static int[] weights, values;

	static int maximumOf(int left, int right) {
		if (left > right)
			return left;
		else
			return right;
	}

	// Recursive function that calculates the maximum value that can be acquired with this function.
	static int recursiveKnapsack(int maxW, int n) {
		// Base case (the bag can't hold any more items, or there are no more items to put in the bag)
		if (n == 0 || maxW == 0) { return 0; }

		// The last item can't be taken if its weight is higher than the bags capacity
		if (weights[n - 1] > maxW) {
			return recursiveKnapsack (maxW, n - 1);
		}

		// Decide if the item at position n-1 is worth taking or not. Returns the option with higher outcome
		return maximumOf(values [n - 1] + recursiveKnapsack(maxW - weights[n - 1], n - 1),
			recursiveKnapsack(maxW, n - 1));
	}

	// Dynamic function, calculates the maximum value that can be carried in a bag with 'maxW' capacity and 'n' items.
	static int dynamicKnapsack(int maxW, int n) {
		int dTable[][] = new int [maxW+1][n+1];

		// Initializing the max values for 0 items and 0 capacity as 0
		for (int j = 0; j <= n; j++)
			dTable[0][j] = 0;
		for (int w = 0; w <= maxW; w++)
			dTable[w][0] = 0;

		// Make a table of max values with the previous max values (memoization)
		for (int j = 1; j <= n; j++) {
			for (int w = 1; w <= maxW; w++) {
				// The item in position 'j' can't be taken if its weight is higher than the bags capacity
				if (weights[j - 1] > w) {
					dTable[w][j] = dTable[w][j - 1];
				} else {
					// Decide if the item at position n-1 is worth taking or not. Returns the option with higher outcome
					dTable[w][j] = maximumOf(values[j - 1] + dTable[w - weights[j - 1]][j - 1], dTable[w][j - 1]);
				}
			}
		}

		// Return the maximum value that can be obtained in the bag
		return dTable[maxW][n];
	}

	public static void main(String args[]) {  
		if (args.length >= 2) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(args[1]));
				
				String[] sizes = in.readLine().split(" ");
				maxWeight = Integer.parseInt(sizes[0]);
				nitems = Integer.parseInt(sizes[1]);

				values = new int[nitems];
				weights = new int[nitems];

				String[] lineElement;
				for (int i = 0; i < nitems; i++) {
					lineElement = in.readLine().split(" ");
					values[i] = Integer.parseInt(lineElement[0]);
					weights[i] = Integer.parseInt(lineElement[1]);
				}

				in.close();
			} catch (Exception e) {
				System.err.println(e);
				System.out.println("\nFile in wrong format. Refer to instructions.");
				return;
			}

			long startTime = System.nanoTime();
			// Run algorithms
			switch (args[0]) {
			case "recursive":
				// Recursive solution
				System.out.println("The maximum value that can be carried is: " + recursiveKnapsack(maxWeight, nitems));
				break;
			case "dynamic":
				// Dynamic solution
				System.out.println("The maximum value that can be carried is: " + dynamicKnapsack(maxWeight, nitems));
				break;
			default:
				// Unspecified error
				System.err.println("\"" + args[0] + "\" is not a valid option.");
				break;
			}
			long endTime = System.nanoTime();

			System.out.println("This program ran in " + ((endTime - startTime)/1000000) + " milliseconds");
		} else {
			System.err.println("No implementation of the program takes " + args.length + " arguments. The program always takes 2 arguments.\nRefer to instructions.");
		}
	}
}
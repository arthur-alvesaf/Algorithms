// Proyecto 3
// Arthur Alves Araujo Ferreira
// A01022593

import java.io.*;
import java.util.*;

class knapsack {
	static int wsize, nitems;
	static int value[][];
	public static void main(String args[]) {  
		if (args.length >= 2) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(args[1]));
				
				// Make graph
				String[] sizes = in.readLine().split(" ");
				wsize = Integer.parseInt(sizes[0]);
				nitems = Integer.parseInt(sizes[1]);

				String[] lineElement;
				for (int i = 0; i < nitems; i++) {
					lineElement = in.readLine().split(" ");
					// save graph information
				}

				in.close();
			} catch (Exception e) {
				System.err.println(e);
				System.out.println("\nFile in wrong format. Refer to instructions.");
				return;
			}

			long startTime = System.nanoTime();
			// Run algorithm
			switch (args[0]) {
			// Prim direct
			case "recursive":
				for (int j = 0; j < nitems; j++) {
					value[0, j] = 0;
				}

				for (int i = 0; i < wsize; i++) {
					value[i, 0] = 0;
				}

				for (int i = 1; i < nitems; i++) {
					for (int w = 1; w < wsize; w++) {
						value[w][i] = value[w][i - 1];
					}
				}
				break;
			case "dynamic":
				System.out.println("dynamic");
				break;
			default:
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
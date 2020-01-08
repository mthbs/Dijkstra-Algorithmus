package de.dijkstra.main;

import java.util.Scanner;

import de.dijkstra.algorithm.ShortestPath;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {

		int graph [][] = new int [][] {{0,7,0,2},
									   {7,0,1,3},
									   {0,1,0,0},
									   {2,3,0,0},
									   };
		
		scanner = new Scanner(System.in);
		System.out.println("Startnode: ");
		int startnode = scanner.nextInt();
		System.out.println("Targetnode: ");
		int targetnode = scanner.nextInt();
		
									   
		ShortestPath p = new ShortestPath();
		p.dijkstra(graph, startnode, targetnode);
		}

}

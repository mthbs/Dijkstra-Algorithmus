package cna;

import java.util.ArrayList;

public class Dijkstra {
	
private int NodesL;
	
	public Dijkstra(String[] nodesL) {
		 this.NodesL = nodesL.length; 
		   }
	
	public int minNode(int dis[], boolean vis[]) {
		int min = Integer.MAX_VALUE, value = 0;
		for(int k = 0; k < NodesL; k++) {
			if(dis[k] < min && !vis[k]) {
				min = dis[k];
				value = k;
			}
		}
		return value;
	}
	
	public void algorithm(int graph[][], int start, int target) {
		
		int distance[] = new int[NodesL];
		boolean visited[] = new boolean[NodesL];
		int path[] = new int[NodesL];
		ArrayList<Integer> path2 = new ArrayList<Integer>();
				
		for(int i = 0; i < NodesL; i++) {
			distance[i] = Integer.MAX_VALUE; 
			visited[i] = false;		
		}
		
		distance[start] = 0;	
		visited[start] = true;
		int j = 0;
		
		while(j<NodesL) {
			if(graph[start][j] != 0 && graph[start][j] < distance[j]) {
				distance[j] = graph[start][j];
			}
			j++;														
		}
		
		for(int m = 0; m < NodesL-1; m++) {
			
			int newNode = minNode(distance, visited);
			visited[newNode] = true;
			
			for(int n = 0; n < NodesL; n++) {
				if(distance[newNode] + graph[newNode][n] < distance[n] && !visited[n] && graph[newNode][n] != 0) {  
					distance[n] = distance[newNode] + graph[newNode][n];
					path2.add(newNode);
				}
			}
		}
		
		System.out.println("The shortest path from node "+ start + " to node " + target + " is " + distance[target]);
		System.out.println(path2);
	}
}
package de.dijkstra.algorithm;

public class ShortestPath {
	
	public int minNode(int dis[], boolean vis[]) {
		int min = Integer.MAX_VALUE, value = 0;
		for(int k = 0; k < 4; k++) {
			if(dis[k] < min && !vis[k]) {
				min = dis[k];
				value = k;
			}
		}
		return value;
	}
	
	public void dijkstra(int graph[][], int start, int target) {
		
		int distance[] = new int[4];
		boolean visited[] = new boolean[4];
		int path[] = new int[4];
				
		for(int i = 0; i < 4; i++) {
			distance[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		
		distance[start] = 0;
		visited[start] = true;
		path[0] = start;
		
		for(int j = 0; j < 4; j++) {
			if(graph[start][j] != 0 && graph[start][j] < distance[j]) {
				distance[j] = distance[start] + graph[start][j];
			}
			
		}
		
		for(int m = 0; m < 3; m++) {
			
			int newNode = minNode(distance, visited);
			visited[newNode] = true;
			
			for(int n = 0; n < 3; n++) {
				if(distance[newNode] + graph[newNode][n] < distance[n] && !visited[n] && graph[newNode][n] != 0) {
					distance[n] = distance[newNode] + graph[newNode][n];
				}
			}
			
		}
		
		System.out.println("The shortest path from node "+ start + " to node " + target + " is " + distance[target]);
		System.out.println("Which path has been taken: ");
		for(int p = 0; p < 4; p++) {
			System.out.print("->" + path[p]);
		}
	}
}
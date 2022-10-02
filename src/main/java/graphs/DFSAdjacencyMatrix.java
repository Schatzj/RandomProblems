package graphs;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DFSAdjacencyMatrix {

	int[][] adjMatrix;
	
	public DFSAdjacencyMatrix(int size) {
		adjMatrix = new int[size][size];
	}
	
	public void addEdge(int startNode, int endingNode) {
		adjMatrix[startNode][endingNode] = 1;
		adjMatrix[endingNode][startNode] = 1; //undirected graph. 
	}
	
	public List<Integer> search(int startNode){
		boolean[] visited = new boolean[adjMatrix.length];
		List<Integer> result = new ArrayList<Integer>();
		visited[startNode] = true;
		result.add(startNode);
		dfsSearch(startNode, visited, result);	
		return result;
	}
	
	public List<Integer> dfsSearch(int startNode, boolean[] visited, List<Integer> result) {
		int[] adjacencies = adjMatrix[startNode];
		for(int i = 0; i<adjacencies.length; i++) {
			if(adjacencies[i] == 1 && visited[i] == false) {
				visited[i] = true;
				result.add(i);
				dfsSearch(i, visited, result);
			}			
		}			    
		return result;
	}
}

package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * AdjacencyGraph that handles complex objects. 
 * Note the first vertex in every vertex's adjacency list should be itself. 
 * @author luthi
 *
 * @param <T>
 */
public class DFSAdjacencyList<T> {

	private int size; 
	private List<LinkedList<T>> adjacencyList;

	public DFSAdjacencyList(int size) {
		this.size = size;
		adjacencyList = new ArrayList<LinkedList<T>>();
		for (int i = 0; i < size; ++i) {
			adjacencyList.add(i, new LinkedList()); 
		}
	}
	
	public void addEdge(int index, T value) {
		if(index >= adjacencyList.size()) {
			adjacencyList.add(size, new LinkedList());
			size++;
		}
		adjacencyList.get(index).add(value);
	}
	
	public List<T> search(int startingPoint){
		List<T> result = new ArrayList<T>();
		
		boolean[] visited = new boolean[size]; //mark all vertices as not visited. 
		
		for(int i = 0; i<size; i++) {
			if (visited[i] == false)
				depthFirstSearch(i, visited, result);
		}
		
		return result;
	}
	
	private void depthFirstSearch(int index, boolean[] visited, List<T> result) {
		visited[index] = true;
		LinkedList<T> adj = adjacencyList.get(index);
		result.add(adj.getFirst());
		
		for(int i = 1; i < adj.size(); i++) {
			if (!visited[i])
				depthFirstSearch(i, visited, result);
		}
	}

}

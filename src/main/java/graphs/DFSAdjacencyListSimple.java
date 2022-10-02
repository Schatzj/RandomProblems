package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DFSAdjacencyListSimple {

	private int size; 
	private List<LinkedList<Integer>> adjacencyList;
	
	public DFSAdjacencyListSimple(int size) {
		this.size = size;
		adjacencyList = new ArrayList<LinkedList<Integer>>(size);
		for(int i = 0 ; i<size; i++) {
			adjacencyList.add(new LinkedList<Integer>());
		}
	}
	
	public void addEdge(int startNode, int endNode) {
		if(startNode < size) {
			adjacencyList.get(startNode).add(endNode);
		}else {
			size++;
			adjacencyList.add(new LinkedList<Integer>());
			adjacencyList.get(startNode).add(endNode);
		}
	}

	public List<Integer> search(int startNode){
		boolean[] visited = new boolean[size];
		List<Integer> result = new ArrayList<Integer>();
		
		result.add(startNode);
		dfsSearch(startNode, visited, result);
		
		return result;
	}
	
	public List<Integer> dfsSearch(int startNode, boolean[] visited, List<Integer> result) {
		visited[startNode] = true;
		
		LinkedList<Integer> adjacencies = adjacencyList.get(startNode);
		Iterator<Integer> iterator = adjacencies.iterator();
		while(iterator.hasNext()) {
			Integer next = iterator.next();
			if(visited[next] == false) {
				visited[next] = true;
				result.add(next);
				dfsSearch(next, visited, result);
			}
		}
		return result;
	}
}

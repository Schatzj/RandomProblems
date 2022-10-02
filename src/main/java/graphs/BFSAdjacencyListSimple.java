package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BFSAdjacencyListSimple {

	private int size; 
	private List<LinkedList<Integer>> adjacencyList;
	
	public BFSAdjacencyListSimple(int size) {
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
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		while(!queue.isEmpty()) {
			Integer currNode = queue.pop();
			if(!visited[currNode]) {
				visited[currNode] = true;
				result.add(currNode);
				LinkedList<Integer> adjacencies = adjacencyList.get(currNode);
				Iterator<Integer> iterator = adjacencies.iterator();
				while(iterator.hasNext()) {
					Integer next = iterator.next();
					if(!visited[next]) {
						queue.add(next);
					}
				}
			}			
		}
		
		return result;
	}
}

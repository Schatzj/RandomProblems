package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdjacencyListGraph<T> {
	List<T> data = new ArrayList<T>();
	List<LinkedList<T>> graph = new ArrayList<LinkedList<T>>();
	Map<String, T> existingData = new HashMap<String, T>();

	public AdjacencyListGraph() {}
	public AdjacencyListGraph(List<T> data) {
		this.data = data;
	}
	
	public void printGraph() {
		int index = 0;
		for(LinkedList<T> list : graph) {
			System.out.print(data.get(index++) + " -> ");
			for(int i = 0; i<list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println("");
		}
	}
	
	public void addVertex(String name, T node) {
		String key;
		if(name == null) {
			if(existingData.containsKey(node.toString())) {
				return;
			}
			key = node.toString();
		}else {
			if(existingData.containsKey(name)) {
				return;
			}
			key = name;
		}
		
		data.add(node);
		existingData.put(key, node);
		LinkedList<T> newVertex = new LinkedList<T>();
		graph.add(newVertex);
	}
	
	public T getVertex(String name) {
		if(!(existingData.containsKey(name))) {
			return null;
		}
		
		return existingData.get(name);
	}
	
	public void addEdge(String startName, String destinationName) {
		T startNode = existingData.get(startName);
		T endNode = existingData.get(destinationName);
		
		if(startNode == null || endNode == null) {
			return;
		}
		
		int startIndex = -1;
		int endIndex = -1;
		for(int i = 0; i<data.size(); i++) {
			T object = data.get(i);
			if(object == startNode) {
				startIndex = i;
			}
			if(endNode == object) {
				endIndex = i;
			}
		}
		
		if(startIndex < 0 || endIndex < 0) {
			return; 
		}
		
		graph.get(startIndex).add(data.get(endIndex));
	}
	
	public void addEdge(T startNode, T destinationNode) {
		T startVertex = existingData.get(startNode.toString());
		T endVertex = existingData.get(destinationNode.toString());
		
		if(startVertex == null || endVertex == null) {
			return;
		}
		
		int startIndex = -1;
		int endIndex = -1;
		for(int i = 0; i<data.size(); i++) {
			T object = data.get(i);
			if(object == startNode) {
				startIndex = i;
			}
			if(endVertex == object) {
				endIndex = i;
			}
		}
		
		if(startIndex < 0 || endIndex < 0) {
			return; 
		}
		
		graph.get(startIndex).add(data.get(endIndex));
	}
	
	public static void main(String[] args) {
		AdjacencyListGraph<Character> graph = new AdjacencyListGraph<Character>();		
		graph.addVertex(null, 'a');
		graph.addVertex(null, 'b');
		graph.addVertex(null, 'c');
		graph.addVertex(null, 'd');
		graph.addVertex(null, 'e');
		graph.addVertex(null, 'f');
		
		graph.addEdge('d', 'a');
		graph.addEdge('b', 'f');
		graph.addEdge('d', 'b');
		graph.addEdge('a', 'f');
		graph.addEdge('c', 'd');
		graph.printGraph();
	}
	
}

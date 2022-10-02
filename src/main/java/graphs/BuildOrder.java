package graphs;

import java.util.HashMap;
import java.util.HashSet;

/*
 * Given an array of projects {a, b, c, d, e}
 * and an array of dependencies {{a, b}, {a, e}, {d, b}}
 * where the second project is dependent on the first. 
 * Print a viable build order if one exists. 
 */
public class BuildOrder {
	
	public static String[][] createGraph(String[]allProjects, String[][] dependencies) {
		String[][] graph = new String[allProjects.length][allProjects.length];
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i<allProjects.length; i++) {
			map.put(allProjects[i], i);
		}
		for(String[] dependencyPair : dependencies) {
			String project = dependencyPair[1];
			String dependency = dependencyPair[0];
			
			int keyOfProjectWithDepencies = map.get(project);
			int keyOfDependency = map.get(dependency);
			graph[keyOfProjectWithDepencies][keyOfDependency] = dependency;
		}
		
		return graph;
	}
	
	public static void printGraph(String[] projects, String[][] graph) {
		System.out.print("  ");
		for(int i = 0; i<projects.length; i++) {
			System.out.print(projects[i] + "  ");
		}
		System.out.println("");
		int index = 0;
		for(String[] data : graph) {
			System.out.print(projects[index++] + " ");
			for(int i = 0; i<data.length; i++) {
				String value = data[i];
				value = (value == null)? "  " : value;
				System.out.print(value + " ");
			}
			System.out.println("");
		}
	}
	
	public static int[] findBuildOrder(String[][] graph, int numbOfProjects) {
		int[] result = new int[numbOfProjects];
		for(int i = 0; i<result.length; i++) {
			result[i] = -1;
		}
		
		HashSet<Integer> builtProjects = new HashSet<Integer>();
		for(int i = 0; i<result.length; i++) {
			int value = findBuildableProject(graph, builtProjects);
			//if value is ever < 0, we have no project which can be built. (circular dependencies). 
			if(value < 0) {
				return null;
			}
			result[i] = value;
			builtProjects.add(value);
		}
				
		return result;
	}
	
	private static int findBuildableProject(String[][] graph, HashSet<Integer> exclude) {
		int index = -1;
		int result = -1;
		for(String[] data : graph) {
			index+=1;
			//check to see if we have already built this project. 
			if(exclude.contains(index)) {
				continue;
			}
			//the project has not been built. check to see if it has any dependencies. 
			boolean hasDependencies = false;
			for(int i = 0; i< data.length; i++) {
				if(!(data[i] == null)) {
					hasDependencies = true;
					break;
				}
			}
			//if it has no dependencies, remove it is a a dependency from all other projects, and build it. 
			if(hasDependencies == false) {
				result = index;
				removeEdge(graph, index);
				return result;
			}
		}
		return result;
	}

	/*
	 * Removes an edge. 
	 * As the project is set to be built, projects which depend on this project
	 * no longer need to list this project as a dependency. 
	 */
	private static void removeEdge(String[][] graph, int index) {
		for(String[] data : graph) {
			data[index] = null;
		}
	}

	public static void main(String[] args) {
		String[] projects = {"a", "b", "c", "d", "e", "f"};
		String[][] dependencies = {
				{"a", "d"},
				{"f", "b"},
				{"b", "d"},
				{"f", "a"},
				{"d", "c"}
		};
		
		String[][] graph = createGraph(projects, dependencies);
		printGraph(projects, graph);
		System.out.println("------------------------------------");
		int[] buildOrder = findBuildOrder(graph, projects.length);
		if(buildOrder != null) {
			for(int projectNumber : buildOrder) {
				System.out.print(projects[projectNumber] + ", ");
			}
		}
	}
}

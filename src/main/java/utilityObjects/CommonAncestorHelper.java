package utilityObjects;

import BinarySearchTree.BSTree;

public class CommonAncestorHelper {
		private BSTree node = null;
		private int nodesFound = 0;
		
		public CommonAncestorHelper() {
			node = null;
			nodesFound = 0;
		}

		public BSTree getNode() {
			return node;
		}

		public void setNode(BSTree node) {
			this.node = node;
		}

		public int getNodesFound() {
			return nodesFound;
		}

		public void setNodesFound(int nodesFound) {
			this.nodesFound = nodesFound;
		}
		
		public void addNodeFound() {
			nodesFound++;
		}
		
		public void addToNodesFound(int value) {
			nodesFound += value;
		}
}

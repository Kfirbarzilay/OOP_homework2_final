package homework2;
import java.util.ArrayList;

 /**
  * this class is a wrapper to the nodes that will be used in the graph.
  * we would like to keep the parents and children of each node in the graph
  * and this class will enable it.
  */
public class GraphNode<E> implements Comparable<GraphNode<E>>, homework2.Childrenable<GraphNode<E>>
{

	// Abstraction Function:
	// 		GraphNode is a node in a graph.
	// 		It consists of the element that is the node And the edges that it has with the other nodes in the graph.
	// 		It contains a list of the parents and children it has.

	// E is a node of any kind
	// String name - name of the elements
	// ArrayList<GraphNode<E>> children - children nodes of the node
	// ArrayList<GraphNode<E>> parents - parent nodes of the node

	// Representation Invariant:
	// 		1. the name of the node isn't empty
	// 		2. there are no multiple directional edges from nodeA to nodeB.
	// 		(all these are checked in the checkRep in Graph.java so there is no need to check this again here.
	//

	private final String name;
	private final E node;
	private ArrayList<GraphNode<E>> children;
	private ArrayList<GraphNode<E>> parents;

	public GraphNode(E node,String nodeName){
		this.name = nodeName;
		this.node = node;
		this.children = new ArrayList<>();
		this.parents = new ArrayList<>();
	}
	
	/**
	 * return the node
	 */
	public E getNode() {
		return node;
	}	
	
	/**
	 * @return the name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * @return the parents
	 */
	public ArrayList<GraphNode<E>> getParents(){
		return parents;
	}
	 
	/**
	 * @return the children
	 */
	public ArrayList<GraphNode<E>> getChildren(){
		return children;
	}
	
	/**
	 * @modifies this
	 * @effects adds a new node to the parents list
	 */
	public void addParent(GraphNode<E> parent){
		this.parents.add(parent);
	}
	
	/**
	 * @modifies this
	 * @effects adds a new node to the children list
	 */
	public void addChild(GraphNode<E> child){
		this.children.add(child);
	}
	
	@Override
	/**
	 * compares this node to another node
	 * @returns a positive integer, zero or a negative integer if this node is bigger, equal or smaller
	 * than g, correspondingly.
	 */
	public int compareTo(GraphNode<E> g) {
		return this.name.compareTo(g.name);
	}
	
}

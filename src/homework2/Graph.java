package homework2;
import java.util.*;

import homework2.Utils.Result;

/**
 * This class is an ADT that implements a directed weighted graph. Id does not permits null values.
 * Nodes hold weights yet edges do not. A node can be of any type parameter, E, with the demand it implememts the {@link Comparable} interface.
 * It builds a graph in O(1) and it's operations of adding a node or an edge is done in a constant time. Returning the
 * list of nodes, E, in Graph or the children's list of a parent node is done in a linear time.
 */
public class Graph<E> {
    
	
	private final String graphName; 
	private HashMap<String,GraphNode<E>> nodes;

	// Representation Invariant:
	// 1. this.graphName != null and !this.graphName.isEmpty().
	// 2. this.nodes:
	//               2.1 this.nodes != null
	//               2.2 First argument, key (String), upholds key!= null and !key.isEmpty() and there exists only one value for that key, meaning all nodes are unique.
	//               2.3 Second argument, value (E), upholds that value != null and value implements the Comparable interface.
	// 3. this.edges:
	//               3.1 this.edges != null
	//               3.2 First argument, key (String), upholds key!= null and !key.isEmpty() and there exists only one value for that key.
	//               3.3 Second argument, value (HashSet<String>), upholds that value != null
	//				 3.4 There are no 2 edges between the same parent-child couple

	// Abstraction function:
	//
	// String graphName - name of the graph
	// ArrayList<GraphNode<E>> nodes - list of the nodes in the graph
	//
	// ArrayList of String nodesNames - list of the names of the nodes of the graph (in the same order as they appear
	//                                 in the list nodes)


	/**
	 * @requires graphName != null.
	 * @effects Creates a new Graph with the name graphName.
	 */
	public Graph(String graphName) {
    	assert graphName != null && graphName.length() > 0;
		this.graphName = graphName;
		this.nodes = new HashMap<>();
		checkRep();
	}
    
	/**
	 *Returns the name of the graph. Does not modify this.
	 *@return the name of the graph
	*/
    public String getName() {
    	checkRep();
    	return this.graphName;
    }
    
    /**
	 *@effect Returns the num of nodes in the graph
	*/
	  public int getNumOfNodes() {
	  	checkRep();
	  	return this.nodes.size();
	  }


	/**
	 *@effect Returns a node in the graph by its name
	*/
	  public GraphNode getNodeByName(String s) {
		checkRep();
		return this.nodes.get(s);
	}
	    
    
    /**
     * @effects  Adds a new node to this graph. Adds its name to nodesNamesList in o(1).
     */
   public Result addNode(E node,String nodeName) {
	    checkRep();
    	assert node != null && nodeName != null && nodeName.length() > 0;
    	if(this.nodes.containsKey(nodeName)){
    		// Name is not unique
    		checkRep();
    		return Result.itemAlreadyExists;
    	}
    	
    	// Add to graph. Name is unique
    	GraphNode<E> graphNode = new GraphNode<>(node,nodeName);
    	this.nodes.put(nodeName,graphNode);
    	checkRep();
    	return Result.Success;
    }
    
    /**
     * @effects  this by Adding a new edge from parent to child to this graph.
     */
    public Result addEdge(String parentNodeStr, String childNodeStr) {
    	checkRep();
    	assert parentNodeStr != null && childNodeStr != null;
        //check that we are not adding self edges
    	if(parentNodeStr.equals(childNodeStr)){
    	    //return Result.SelfLoop;
    	}		
    	// check if these nodes exist in our graph
    	if(!this.nodes.containsKey(parentNodeStr) || !this.nodes.containsKey(childNodeStr)){
    		checkRep();
    		return Result.itemDoesntExist;
    	}
    	
    	// get the nodes
    	GraphNode<E> parentNode = this.nodes.get(parentNodeStr);
    	GraphNode<E> childNode = this.nodes.get(childNodeStr);
    	
    	// see if there is'nt already an edge in this direction between the nodes
    	if(parentNode.getChildren().contains(childNode) || childNode.getParents().contains(parentNode)){
    		// assert that both of those exist
    		assert parentNode.getChildren().contains(childNode) && childNode.getParents().contains(parentNode);
    		checkRep();
    		return Result.itemAlreadyExists;
    	}
    	
    	// add the edge. i.e add a child to parentNode and a parent to childNode
    	parentNode.addChild(childNode);
    	childNode.addParent(parentNode);
    	checkRep();
    	return Result.Success;
    }

	public Iterator<GraphNode<E>> getNodes()
	{
		return this.nodes.values().iterator();
	}

	/**
	 * @effect returns a list of children of a parent node.
	 * Check if node exists in the graph, otherwise return null.
	 */
    public ArrayList<GraphNode<E>> getChildren(String parentNodeStr){
    	checkRep();
    	assert parentNodeStr != null;
    	// Check if node exists
    	if(!this.nodes.containsKey(parentNodeStr)){
    		checkRep();
    		return null;
    	}

    	// Get node
    	GraphNode<E> node = this.nodes.get(parentNodeStr);
    	// Get the list of the children
    	ArrayList<GraphNode<E>> children = node.getChildren();
    	checkRep();
    	return children;
    }
    
    /** returns the parents list of the node.
     * @effect Check if node exists in the graph, otherwise return null.
     */
    public ArrayList<GraphNode<E>> getParents(String childNodeStr){
    	checkRep();
    	assert childNodeStr != null;
    	// check that the node exists it the graph
    	if(!this.nodes.containsKey(childNodeStr)){
    		checkRep();
    		return null;
    	}

    	// Get node
    	GraphNode<E> node = this.nodes.get(childNodeStr);
		// Get node parents
    	ArrayList<GraphNode<E>> parents = node.getParents();
    	checkRep();
    	return parents;
    }

    /**
     * @effect Prints out the list of the nodes in the graph by order of their names.
     */
    public String getNodesString() {
    	checkRep();
    	ArrayList<String> arrayList = new ArrayList<>(this.nodes.keySet());
    	Collections.sort(arrayList);

    	// Print graph
    	String str = this.graphName +" contains:";
    	for(String name : arrayList){
    		str += " " + name;
    	}
    	checkRep();
    	return str;
    }


    /**
     * @effect Prints a list of children of a parent node in the graph by alphabetical order of their names.
     * Check if node exists in the graph, otherwise return null.
     */
    public String getChildrenString(String parentNodeStr) {
    	checkRep();
    	assert parentNodeStr != null;
    	// Get children of the parent
    	ArrayList<GraphNode<E>> children = this.getChildren(parentNodeStr);
    	if(children == null){
    		// Doesn't exist
    		checkRep();
    		return null;
    	}
    	
    	// Create ne array and sort it
    	ArrayList<GraphNode<E>> childrenSorted = new ArrayList<GraphNode<E>>(children);
    	Collections.sort(childrenSorted);
    	
    	// Print all the children in the right order
    	String s = String.format("the children of %s in %s are:",parentNodeStr,this.graphName);
    	for(GraphNode<E> child : childrenSorted){
    		s += " " + child.getName();
    	}
    	checkRep();
    	return s;
    }  
    
    /**
     * @effect Prints a list of the parents of a child node in the graph by alphabetical order of their names.
	 * Check if node exists in the graph, otherwise return null.     *
     */
    public String getParentsString(String childNodeName) {
    	checkRep();
    	assert childNodeName != null;
    	// Get parents of the node
    	ArrayList<GraphNode<E>> parents = this.getParents(childNodeName);
    	if(parents == null){
    		// Doesn't exist
    		checkRep();
    		return null;
    	}
    	
    	// create a new ArrayList and sort it
    	ArrayList<GraphNode<E>> parentsSorted = new ArrayList<>(parents);
    	Collections.sort(parentsSorted);
    	
    	// print all the children in an alphabetical order
    	String s = String.format("the parents of %s in %s are:",childNodeName,this.graphName);
    	for(GraphNode<E> parent : parentsSorted){
    		s += " " + parent.getName();
    	}
    	checkRep();
    	return s;
    } 
    /**
     * @effect Returns nodes from their names given
     */
    
    public ArrayList<GraphNode<E>> getNodesFromNames(List<String> sourceArgs){
    	ArrayList<GraphNode<E>> nodes = new ArrayList<>();
    	for(String name : sourceArgs){
    		GraphNode<E> node = this.nodes.get(name);
    		nodes.add(node);
    	}
    	return nodes;
    	
    }
    
    /** 
     * @effect checks that the instance maintains the RI
     */
    private void checkRep(){
    	assert this.graphName.length() > 0;
    	for(GraphNode<E> node : this.nodes.values()){
    		for(GraphNode<E> child : node.getChildren()){
    			assert node.getChildren().indexOf(child) == node.getChildren().lastIndexOf(child);
    		}
    		
    		for(GraphNode<E> parent : node.getParents()){
    			assert node.getParents().indexOf(parent) == node.getParents().lastIndexOf(parent);
    		}
    	}
    	for(GraphNode<E> node : this.nodes.values()){
    		for(GraphNode<E> child : node.getChildren()){
    			assert child.getParents().contains(node);
    		}
    		
    		for(GraphNode<E> parent : node.getParents()){
    			assert parent.getChildren().contains(node);
    		}
    	}
    	
    }
    
}

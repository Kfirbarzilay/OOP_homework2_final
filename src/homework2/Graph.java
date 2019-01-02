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
	 *Returns the num of nodes in the graph
	 *
	 *@return the num of nodes in the graph
	*/
	  public int getNumOfNodes() {
	  	checkRep();
	  	return this.nodes.size();
	  }


	/**
	 *Returns a node in the graph by its name
	 *
	 *@return a node in the graph
	*/
	  public GraphNode getNodeByName(String s) {
		checkRep();
		return this.nodes.get(s);
	}
	    
    
    /**
     * Adds a node to the graph. Nodes can be of any type.
     * Newly added nodes must have a unique name that is not already present in the graph.
     * When a nodeName that is already present in the graph is given then the method will fail
     * and return itemAlreadyExists result(see Utils class for more details).
     *
     * @requires node != null , nodeName != all previous names
     * 
     * @modifies this: nodes, nodeNames
     * 
     * @effects  Adds a new node to this graph. Adds its name to nodesNamesList in o(1).
     * 
     * @return itemAlreadyExists if the node already exists in the graph.
     * 		   Success otherwise.
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
     * Adds an edge to the graph. This is a directed weighted (nodes have weight not edges) graph thus edges have a direction Parent --> Child.
     * if one of the nodes doesn't exist, or the edge already exists, the method will fail.
     * No self edges addition allowed.
     * @requires parentNodeName,childNodeName != null,
     *            no self edges: parentNodeName != childNodeName
	 *
     * @modifies this: nodesNamesList ,childrenList
     * 
     * @effects  this by Adding a new edge from parent to child to this graph.
     * 
     * @return itemDoesntExist if one of the nodes doesn't exist in the graph.
     *         itemAlreadyExists if the edge already exists. 
     *         Success otherwise.
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
	 * returns the children list of the node.
	 * if the node doesn't exist in the graph, the method will return null.
	 * param parentNodeName: the parent node
	 * 
	 * @return  ArrayList of child nodes
	 * 			null if parentNodeName == null
	 */
    public ArrayList<GraphNode<E>> getChildren(String parentNodeName){
    	checkRep();
    	assert parentNodeName != null;
    	// Check if node exists
    	if(!this.nodes.containsKey(parentNodeName)){
    		checkRep();
    		return null;
    	}

    	// Get node
    	GraphNode<E> node = this.nodes.get(parentNodeName);
    	// Get the list of the children
    	ArrayList<GraphNode<E>> children = node.getChildren();
    	checkRep();
    	return children;
    }
    
    /** returns the parents list of the node.
     * if the node doesn't exist in the graph, the method will return null.
     *
     * @return ArrayList of parent nodes
     * 		   null if childNodeName == null
     */
    public ArrayList<GraphNode<E>> getParents(String childNodeName){
    	checkRep();
    	assert childNodeName != null;
    	// check that the node exists it the graph
    	if(!this.nodes.containsKey(childNodeName)){
    		checkRep();
    		return null;
    	}

    	// Get node
    	GraphNode<E> node = this.nodes.get(childNodeName);
		// Get node parents
    	ArrayList<GraphNode<E>> parents = node.getParents();
    	checkRep();
    	return parents;
    }

    /**
     * Prints out the list of the nodes in the graph by alphabetical order of their names.
     */
    public String getNodesString() {
    	checkRep();
    	ArrayList<String> list = new ArrayList<>(this.nodes.keySet());
    	Collections.sort(list);

    	// Print graph
    	String s = this.graphName +" contains:";
    	for(String name : list){
    		s += " " + name; 
    	}
    	checkRep();
    	return s;  	
    }


    /**
     * Prints out the list of the children of a node named parentNodeName in the graph by alphabetical order of their names.
     * if the node doesn't exist in the graph, the method will fail.
     * 
     * @return null if the node doesnt exist.
     * 		   String describing its children otherwise.
     */
    public String getChildrenString(String parentNodeName) {
    	checkRep();
    	assert parentNodeName != null;
    	// Get children of the parent
    	ArrayList<GraphNode<E>> children = this.getChildren(parentNodeName);
    	if(children == null){
    		// Doesn't exist
    		checkRep();
    		return null;
    	}
    	
    	// Create ne array and sort it
    	ArrayList<GraphNode<E>> childrenSorted = new ArrayList<GraphNode<E>>(children);
    	Collections.sort(childrenSorted);
    	
    	// Print all the children in the right order
    	String s = String.format("the children of %s in %s are:",parentNodeName,this.graphName);
    	for(GraphNode<E> child : childrenSorted){
    		s += " " + child.getName();
    	}
    	checkRep();
    	return s;
    }  
    
    /**
     * Prints out the list of the parents of a node named childNodeName in the graph by alphabetical order of their names.
     * if the node doesn't exist in the graph, the method will fail.
     * 
     * @return null if the node doesnt exist.
     * 		   String describing its parents otherwise.
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
     * This method returns nodes from their names given
     * 
     * @requires sourceArgs != null valid node names in graph
     * 
     * @effects list of nodes corresponding to the list of names recieved
     * */
    
    public ArrayList<GraphNode<E>> getNodesFromNames(List<String> sourceArgs){
    	ArrayList<GraphNode<E>> nodes = new ArrayList<>();
    	for(String name : sourceArgs){
    		GraphNode<E> node = this.nodes.get(name);
    		nodes.add(node);
    	}
    	return nodes;
    	
    }
    
    /** 
     * checks that the instance maintains the represantation invariant
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

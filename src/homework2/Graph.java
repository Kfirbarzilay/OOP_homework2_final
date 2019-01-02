package homework2;
import java.util.*;

import homework2.Utils.Result;

/**
 * <p>Abstraction Function:
 * 
 * Graph class is an ADT that implements a directed weighted graph. 
 * In This ADT nodes have weight and edges don't have weight.
 * The nodes of the graph are represented by GraphNode class
 * Each node will keep lists of his parent and children.
 * Node names in Graph must be unique.
 *
 * <p>Graph ADT limitations:
 * 
 * No self edges addition allowed.
 * 
 * String graphName - name of the graph
 * 
 * ArrayList<GraphNode<E>> nodes - list of the nodes in the graph
 * 
 * ArrayList of String nodesNames - list of the names of the nodes of the graph (in the same order as they appear
 *                                in the list nodes)
 * 
 * <p>Representation Invariant:
 * 
 *    1) the name of the graph isn't empty
 *    
 *    2) all the names of the nodes in the graph are unique
 *    
 *    3) there are no 2 edges between the same parent-child couple
 *    
 *    4) if a node appears in one node's list as a child, that node appears at the first node's list as a parent.
 */
//,P extends Path<GraphNode<E>,P>
public class Graph<E> {
    
	
	private final String graphName; 
	private HashMap<String,GraphNode<E>> nodes;
	
    /**
     * <p>Creates a new instance of a Graph. Initially without nodes or edges.
     * 
     * <p>requires: name != null && name.length  > 0 
     * 
     * <p>effects:  Creates a new graph called name and returns a reference to the object.
     * 
     * <p>return a new graph object
     */
	public Graph(String name) {
    	assert name != null && name.length() > 0;
		this.graphName = name;
		this.nodes = new HashMap<String,GraphNode<E>>();
		checkRep();
	}
    
	/**
	  <p>Returns the name of the graph. Does not modify this.
	  
	  @return the name of the graph
	*/
    public String getName() {
    	checkRep();
    	return this.graphName;
    }
    
    /**
	  <p>Returns the num of nodes in the graph
	  
	  @return the num of nodes in the graph
	*/
	  public int getNumOfNodes() {
	  	checkRep();
	  	return this.nodes.size();
	  }


	/**
	  <p>Returns a node in the graph by its name

	  @return a node in the graph
	*/
	  public GraphNode getNodeByName(String s) {
		checkRep();
		return this.nodes.get(s);
	}
	    
    
    /**
     * <p>Adds a node to the graph. Nodes can be of any type.
     * Newly added nodes must have a unique name that is not already present in the graph.
     * When a nodeName that is already present in the graph is given then the method will fail
     * and return itemAlreadyExists result(see Utils class for more details).
     *
     * <p>requires: node != null , nodeName != all previous names
     * 
     * param elem the element to be added as a node to the graph 
     * 
     * param nodeName the name of the node
     * 
     * modifies: this: nodes, nodeNames
     * 
     * effects:  Adds a new node to this graph. Adds its name to nodesNamesList in o(1).
     * 
     * return itemAlreadyExists if the node already exists in the graph. 
     * 		   Success otherwise.
     */
   public Result addNode(E elem,String nodeName) {
	    checkRep();
    	assert elem != null && nodeName != null && nodeName.length() > 0;
    	// check that the name is unique. if it isn't - return false.
    	if(this.nodes.containsKey(nodeName)){
    		checkRep();
    		return Result.itemAlreadyExists;
    	}
    	
    	// name is unique, add to the graph
    	GraphNode<E> gNode = new GraphNode<E>(elem,nodeName);
    	this.nodes.put(nodeName,gNode);
    	checkRep();
    	return Result.Success;
    }
    
    /**
     * <p>Adds an edge to the graph. This is a directed weighted (nodes have weight not edges) graph thus edges have a direction Parent --> Child.
     * if one of the nodes doesn't exist, or the edge already exists, the method will fail.
     * No self edges addition allowed.
     * <p>requires: parentNodeName,childNodeName != null, 
     *            no self edges: parentNodeName != childNodeName  
     * 
     * param parentNodeName name of parent node
     * 
     * param childNodeName name of child node
     * 
     * modifies: this: nodesNamesList ,childrenList
     * 
     * effects:  this by Adding a new edge from parent to child to this graph.
     * 
     * return itemDoesntExist if one of the nodes doesn't exist in the graph.
     *         itemAlreadyExists if the edge already exists. 
     *         Success otherwise.
     */
    public Result addEdge(String parentNodeName, String childNodeName) {
    	checkRep();
    	assert parentNodeName != null && childNodeName != null;
        //check that we are not adding self edges
    	if(parentNodeName.equals(childNodeName)){
    	    //return Result.SelfLoop;
    	}		
    	// check if these nodes exist in our graph
    	if(!this.nodes.containsKey(parentNodeName) || !this.nodes.containsKey(childNodeName)){
    		checkRep();
    		return Result.itemDoesntExist;
    	}
    	
    	// get the nodes
    	GraphNode<E> parentNode = this.nodes.get(parentNodeName);
    	GraphNode<E> childNode = this.nodes.get(childNodeName);
    	
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
	 * <p>returns the children list of the node.
	 * if the node doesn't exist in the graph, the method will return null.
	 * <p>param parentNodeName: the parent node
	 * 
	 * return ArrayList of child nodes
	 * 		   null if parentNodeName == null
	 */
    public ArrayList<GraphNode<E>> getChildren(String parentNodeName){
    	checkRep();
    	assert parentNodeName != null;
    	// check that the node exists it the graph
    	if(!this.nodes.containsKey(parentNodeName)){
    		checkRep();
    		return null;
    	}

    	// get the node
    	GraphNode<E> node = this.nodes.get(parentNodeName);
    	// get the list of the node's children
    	ArrayList<GraphNode<E>> children = node.getChildren();
    	checkRep();
    	return children;
    }
    
    /** <p>returns the parents list of the node.
     * if the node doesn't exist in the graph, the method will return null.
     * 
     * <p>param childNodeName: the child node
     * 
     * <p>return ArrayList of parent nodes
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

    	// get the node
    	GraphNode<E> node = this.nodes.get(childNodeName);
    	// get the list of the node's children
    	ArrayList<GraphNode<E>> parents = node.getParents();
    	checkRep();
    	return parents;
    }

    /**
     * <p>Prints out the list of the nodes in the graph by alphabetical order of their names.
     */
    public String getNodesString() {
    	checkRep();
    	ArrayList<String> list = new ArrayList<String>(this.nodes.keySet());
    	Collections.sort(list);

    	// now print the name of the graph and the nodes names
    	String s = this.graphName +" contains:";
    	for(String name : list){
    		s += " " + name; 
    	}
    	checkRep();
    	return s;  	
    }


    /**
     * <p>Prints out the list of the children of a node named parentNodeName in the graph by alphabetical order of their names.
     * if the node doesn't exist in the graph, the method will fail.
     * 
     * <p>return null if the node doesnt exist.
     * 		   String describing its children otherwise.
     */
    public String getChildrenString(String parentNodeName) {
    	checkRep();
    	assert parentNodeName != null;
    	// get the children's list of the parent node
    	ArrayList<GraphNode<E>> children = this.getChildren(parentNodeName);
    	if(children == null){
    		// the node doesn't exist
    		checkRep();
    		return null;
    	}
    	
    	// create a new ArrayList and sort it
    	ArrayList<GraphNode<E>> childrenSorted = new ArrayList<GraphNode<E>>(children);
    	Collections.sort(childrenSorted);
    	
    	// print all the children in an alphabetical order.
    	String s = String.format("the children of %s in %s are:",parentNodeName,this.graphName);
    	for(GraphNode<E> child : childrenSorted){
    		s += " " + child.getName();
    	}
    	checkRep();
    	return s;
    }  
    
    /**
     * <p>Prints out the list of the parents of a node named childNodeName in the graph by alphabetical order of their names.
     * if the node doesn't exist in the graph, the method will fail.
     * 
     * <p>return null if the node doesnt exist.
     * 		   String describing its parents otherwise.
     */
    public String getParentsString(String childNodeName) {
    	checkRep();
    	assert childNodeName != null;
    	// get the children's list of the parent node
    	ArrayList<GraphNode<E>> parents = this.getParents(childNodeName);
    	if(parents == null){
    		// the node doesn't exist
    		checkRep();
    		return null;
    	}
    	
    	// create a new ArrayList and sort it
    	ArrayList<GraphNode<E>> parentsSorted = new ArrayList<GraphNode<E>>(parents);
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
     * <p>This method returns nodes from their names given
     * 
     * <p>requires: sourceArgs != null valid node names in graph
     * 
     * effects: list of nodes corresponding to the list of names recieved
     * */
    
    public ArrayList<GraphNode<E>> getNodesFromNames(List<String> sourceArgs){
    	// eliminate duplicates
    	
    	// get the start and end nodes corresponding to the names
    	ArrayList<GraphNode<E>> nodes = new ArrayList<GraphNode<E>>();
    	for(String name : sourceArgs){
    		GraphNode<E> node = this.nodes.get(name);
    		nodes.add(node);
    	}
    	return nodes;
    	
    }
    
    /** 
     * <p>checks that the instance maintains the represantation invariant
     */
    private void checkRep(){
		// 1)
    	assert this.graphName.length() > 0;
    	
    	// 2)
	    	// this represantation is surely met because the names of the nodes are the keys for the HashMap,
	    	// and there can't be 2 identical keys.
    	
    	// 3)
    	for(GraphNode<E> node : this.nodes.values()){
    		// go over all his children and parents and check for doubles
    		for(GraphNode<E> child : node.getChildren()){
    			assert node.getChildren().indexOf(child) == node.getChildren().lastIndexOf(child);
    		}
    		
    		for(GraphNode<E> parent : node.getParents()){
    			assert node.getParents().indexOf(parent) == node.getParents().lastIndexOf(parent);
    		}
    	}
    	
    	// 4)
    	for(GraphNode<E> node : this.nodes.values()){
    		// go over all his children and parents and check for doubles
    		for(GraphNode<E> child : node.getChildren()){
    			assert child.getParents().contains(node);
    		}
    		
    		for(GraphNode<E> parent : node.getParents()){
    			assert parent.getChildren().contains(node);
    		}
    	}
    	
    }
    
}

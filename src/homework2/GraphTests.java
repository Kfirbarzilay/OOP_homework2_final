package homework2;
import homework2.Utils.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends homework2.ScriptFileTests
{

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}
 
	/***************************************************** WHITEBOX *******************************************/
	
	Graph<WeightedNode> empty;
	Graph<WeightedNode> g1;
	Graph<WeightedNode> twoFathersTwoSons;
	Graph<WeightedNode> line;
	Graph<WeightedNode> line2ways;
	Graph<WeightedNode> fcComponents; // fully connected components
	Graph<WeightedNode> findTestGraph;
	WeightedNode n1;
	WeightedNode n2; 
	WeightedNode n3;
	WeightedNode n4; 
	WeightedNode n5; 
	WeightedNode n6; 

	ArrayList<List<String>> srcList;
	ArrayList<List<String>> destList;
	@Before 
	public void init(){
		empty = new Graph<WeightedNode>("empty");
		g1 = new Graph<WeightedNode>("g1");
		twoFathersTwoSons = new Graph<WeightedNode>("twoFathersTwoSons");
		line = new Graph<WeightedNode>("line");
		line2ways = new Graph<WeightedNode>("line2ways");
		fcComponents = new Graph<WeightedNode>("fcComponents"); // fully connected components
		
		findTestGraph = new Graph<WeightedNode>("findTestGraph");
		
		srcList    = new ArrayList<List<String>>();
		destList   = new ArrayList<List<String>>();  
		
		for(int i=1; i<5; i++) {
			srcList.add(new ArrayList<String>());
			destList.add(new ArrayList<String>());
		}
		    
		
		
		n1 = new WeightedNode("n1",1);
		n2 = new WeightedNode("n2",2);
		n3 = new WeightedNode("n3",3);
		n4 = new WeightedNode("n4",4);
		n5 = new WeightedNode("n5",5);
		n6 = new WeightedNode("n6",6);

		 
	    g1.addNode(n1, "n1");
        g1.addNode(n2, "n2");
        g1.addEdge("n1", "n2");
        g1.addNode(n3, "n3");
        g1.addEdge("n2", "n3");
        g1.addNode(n4, "n4");
        g1.addEdge("n3", "n4");
        g1.addNode(n5, "n5");
        g1.addNode(n6, "n6");
        g1.addEdge("n4", "n5");
        g1.addEdge("n4", "n6");
        
        twoFathersTwoSons.addNode(n1, "n1");
        twoFathersTwoSons.addNode(n2, "n2");
        twoFathersTwoSons.addNode(n3, "n3");
        twoFathersTwoSons.addEdge("n1", "n2");
        twoFathersTwoSons.addEdge("n1", "n3");
        twoFathersTwoSons.addEdge("n2", "n3");
		
	    line.addNode(n1, "n1");
	    line.addNode(n2, "n2");
	    line.addNode(n3, "n3");
	    line.addEdge("n1", "n2");
	    line.addEdge("n2", "n3");
	    
	    line2ways.addNode(n1, "n1");
	    line2ways.addNode(n2, "n2");
	    line2ways.addNode(n3, "n3");
	    line2ways.addEdge("n1", "n2");
	    line2ways.addEdge("n2", "n1");
	    line2ways.addEdge("n2", "n3");
	    line2ways.addEdge("n3", "n2");
	    
	    fcComponents.addNode(n1,"n1");
	    fcComponents.addNode(n2,"n2");
	    fcComponents.addNode(n3,"n3");
	    fcComponents.addNode(n4,"n4");
	    fcComponents.addNode(n5,"n5");
	    fcComponents.addNode(n6,"n6");
	    fcComponents.addEdge("n1", "n2");
	    fcComponents.addEdge("n2", "n3");
	    fcComponents.addEdge("n3", "n1");
	    fcComponents.addEdge("n4", "n5");
	    fcComponents.addEdge("n5", "n6");
	    fcComponents.addEdge("n6", "n1");
	   
	    findTestGraph.addNode(n1,"n1");
	    findTestGraph.addNode(n2,"n2");
	    findTestGraph.addNode(n3,"n3");
	    findTestGraph.addNode(n4,"n4");
	    findTestGraph.addNode(n5,"n5");
	    findTestGraph.addNode(n6,"n6");
	    findTestGraph.addEdge("n1", "n2");
	    findTestGraph.addEdge("n2", "n3");
	    findTestGraph.addEdge("n2", "n4");
	    findTestGraph.addEdge("n3", "n5");
	    findTestGraph.addEdge("n4", "n5");
	    findTestGraph.addEdge("n5", "n6");
	    findTestGraph.addEdge("n6", "n1");
	    
	    //srcList
	    srcList.get(0).add("n2");  
	    destList.get(0).add("n5");    
	    srcList.get(1).add("n3");
	    srcList.get(1).add("n2");
	    destList.get(1).add("n5");    
	    srcList.get(2).add("n4");  
	    srcList.get(2).add("n2");
	    destList.get(2).add("n1");    
	    srcList.get(3).add("n2");  
	    destList.get(3).add("n5");    
	    destList.get(3).add("n1");         
   }
	
	@Test
	public void createGraphTest(){
		assertEquals(line.getName(),"line");
		assertEquals(line2ways.getName(),"line2ways");
		assertEquals(fcComponents.getName(),"fcComponents");
		
		assertEquals(empty.getName(),"empty");
		assertEquals(empty.getNumOfNodes(),0);
	}
	
	@Test
	public void addNodeTest(){
		assertEquals(empty.addNode(n1, "n1"),Result.Success);
		assertEquals(empty.getNumOfNodes(),1);
		assertEquals(empty.getNodesString(),String.format("%s contains: %s", "empty","n1"));
		// check that the node still isn't connected to anything
		assertEquals(empty.getChildrenString("n1"),String.format("the children of %s in %s are:", "n1","empty"));
		assertEquals(empty.getParentsString("n1"),String.format("the parents of %s in %s are:", "n1","empty"));
		
		// fails
		assertEquals(empty.addNode(n1, "n1"),Result.itemAlreadyExists);
		assertEquals(empty.addNode(n2, "n1"),Result.itemAlreadyExists);
		assertEquals(empty.getNumOfNodes(),1);
		// check that the node still isn't connected to anything
		assertEquals(empty.getChildrenString("n1"),String.format("the children of %s in %s are:", "n1","empty"));
		assertEquals(empty.getParentsString("n1"),String.format("the parents of %s in %s are:", "n1","empty"));
	}
	
	@Test
	public void addEdgeTest(){
		empty.addNode(n1, "n1");
		empty.addNode(n2, "n2");
		empty.addNode(n3, "n3");
		// try one directional edges
		assertEquals(empty.addEdge("n1", "n2"),Result.Success);
		assertEquals(empty.addEdge("n1", "n3"),Result.Success);
		assertEquals(empty.addEdge("n2", "n3"),Result.Success);
		// check if it worked
		assertEquals(empty.getChildrenString("n1"),String.format("the children of %s in %s are: %s %s", "n1","empty","n2","n3"));
		assertEquals(empty.getChildrenString("n2"),String.format("the children of %s in %s are: %s", "n2","empty","n3"));
		assertEquals(empty.getChildrenString("n3"),String.format("the children of %s in %s are:", "n3","empty"));
		assertEquals(empty.getParentsString("n1"),String.format("the parents of %s in %s are:", "n1","empty"));
		assertEquals(empty.getParentsString("n2"),String.format("the parents of %s in %s are: %s", "n2","empty","n1"));
		assertEquals(empty.getParentsString("n3"),String.format("the parents of %s in %s are: %s %s", "n3","empty","n1","n2"));
		// try a two directional edge
		assertEquals(empty.addEdge("n2", "n1"),Result.Success);
		// check if it worked
		assertEquals(empty.getChildrenString("n1"),String.format("the children of %s in %s are: %s %s", "n1","empty","n2","n3"));
		assertEquals(empty.getChildrenString("n2"),String.format("the children of %s in %s are: %s %s", "n2","empty","n1","n3"));
		assertEquals(empty.getChildrenString("n3"),String.format("the children of %s in %s are:", "n3","empty"));
		assertEquals(empty.getParentsString("n1"),String.format("the parents of %s in %s are: %s", "n1","empty","n2"));
		assertEquals(empty.getParentsString("n2"),String.format("the parents of %s in %s are: %s", "n2","empty","n1"));
		assertEquals(empty.getParentsString("n3"),String.format("the parents of %s in %s are: %s %s", "n3","empty","n1","n2"));
		
	}
	
	@Test
	public void getNodesString(){
		// success 
		// for - 0 runs
		assertEquals(empty.getNodesString(),String.format("%s contains:","empty"));
		// for - 1 runs
		empty.addNode(n1, "n1");
		assertEquals(empty.getNodesString(),String.format("%s contains: %s","empty","n1"));
		// for - 2 runs
		empty.addNode(n2, "n2");
		assertEquals(empty.getNodesString(),String.format("%s contains: %s %s","empty","n1","n2"));
	}
	
	@Test
	public void getChildrenStringTest(){
		// first if - node doesnt exist in graph
		assertEquals(line.getChildrenString("n4"),null);
		// success
		// for - 0 runs
		assertEquals(line.getChildrenString("n3"), String.format("the children of %s in %s are:", "n3","line"));
		// for - 1 runs
		assertEquals(line.getChildrenString("n2"), String.format("the children of %s in %s are: %s", "n2","line","n3"));
		// for - 2 runs
		assertEquals(line.getChildrenString("n1"), String.format("the children of %s in %s are: %s", "n1","line","n2"));
	}
	
	@Test
	public void getParentsStringTest(){
		// first if - node doesnt exist in graph
		assertEquals(twoFathersTwoSons.getParentsString("n4"),null);
		// success
		// for - 0 runs
		assertEquals(twoFathersTwoSons.getParentsString("n1"), String.format("the parents of %s in %s are:", "n1","twoFathersTwoSons"));
		// for - 1 runs
		assertEquals(twoFathersTwoSons.getParentsString("n2"), String.format("the parents of %s in %s are: %s", "n2","twoFathersTwoSons","n1"));
		// for - 2 runs
		assertEquals(twoFathersTwoSons.getParentsString("n3"), String.format("the parents of %s in %s are: %s %s", "n3","twoFathersTwoSons","n1","n2"));
	}
	

	
}
	



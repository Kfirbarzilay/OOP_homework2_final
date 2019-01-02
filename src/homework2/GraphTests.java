package homework2;
import homework2.Utils.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

	ArrayList<List<String>> sourceList;
	ArrayList<List<String>> destinationList;

	WeightedNode n1;
	WeightedNode n2;
	WeightedNode n3;
	WeightedNode n4;
	WeightedNode n5;
	WeightedNode n6;


	Graph<WeightedNode> fathers2Sons2;
	Graph<WeightedNode> splitEnd;
	Graph<WeightedNode> oneAfterAnother;
	Graph<WeightedNode> graphTesting;
	Graph<WeightedNode> clique;
	Graph<WeightedNode> oneAfterAnother2Ways;
	Graph<WeightedNode> noNodes;


	@Before 
	public void init(){
		sourceList = new ArrayList<List<String>>();
		destinationList = new ArrayList<List<String>>();

		fathers2Sons2 = new Graph<WeightedNode>("fathers2Sons2");
		splitEnd = new Graph<WeightedNode>("splitEnd");
		oneAfterAnother = new Graph<WeightedNode>("oneAfterAnother");
		graphTesting = new Graph<WeightedNode>("graphTesting");
		clique = new Graph<WeightedNode>("clique");
		oneAfterAnother2Ways = new Graph<WeightedNode>("oneAfterAnother2Ways");
		noNodes = new Graph<WeightedNode>("noNodes");


		for(int i=1; i<5; i++) {
			sourceList.add(new ArrayList<String>());
			destinationList.add(new ArrayList<String>());
		}


		n1 = new WeightedNode("n1",1);
		n2 = new WeightedNode("n2",2);
		n3 = new WeightedNode("n3",3);
		n4 = new WeightedNode("n4",4);
		n5 = new WeightedNode("n5",5);
		n6 = new WeightedNode("n6",6);

		fathers2Sons2.addNode(n1, "n1");
		fathers2Sons2.addNode(n2, "n2");
		fathers2Sons2.addNode(n3, "n3");
		fathers2Sons2.addEdge("n1", "n2");
		fathers2Sons2.addEdge("n1", "n3");
		fathers2Sons2.addEdge("n2", "n3");

		splitEnd.addNode(n1, "n1");
		splitEnd.addNode(n2, "n2");
		splitEnd.addNode(n3, "n3");
		splitEnd.addNode(n4, "n4");
		splitEnd.addNode(n5, "n5");
		splitEnd.addNode(n6, "n6");
		splitEnd.addEdge("n1", "n2");
		splitEnd.addEdge("n2", "n3");
		splitEnd.addEdge("n3", "n4");
		splitEnd.addEdge("n4", "n5");
		splitEnd.addEdge("n4", "n6");


	    oneAfterAnother.addNode(n1, "n1");
	    oneAfterAnother.addNode(n2, "n2");
	    oneAfterAnother.addNode(n3, "n3");
	    oneAfterAnother.addEdge("n1", "n2");
	    oneAfterAnother.addEdge("n2", "n3");


		graphTesting.addNode(n1,"n1");
		graphTesting.addNode(n2,"n2");
		graphTesting.addEdge("n1", "n2");
		graphTesting.addNode(n3,"n3");
		graphTesting.addEdge("n2", "n3");
		graphTesting.addNode(n4,"n4");
		graphTesting.addEdge("n2", "n4");
		graphTesting.addNode(n5,"n5");
		graphTesting.addEdge("n3", "n5");
		graphTesting.addNode(n6,"n6");
		graphTesting.addEdge("n4", "n5");
		graphTesting.addEdge("n5", "n6");
		graphTesting.addEdge("n6", "n1");

		clique.addNode(n1,"n1");
		clique.addNode(n2,"n2");
		clique.addEdge("n1", "n2");
		clique.addNode(n3,"n3");
		clique.addEdge("n2", "n3");
		clique.addEdge("n3", "n1");
		clique.addNode(n4,"n4");
		clique.addNode(n5,"n5");
		clique.addEdge("n4", "n5");
		clique.addNode(n6,"n6");
		clique.addEdge("n5", "n6");
		clique.addEdge("n6", "n1");

		oneAfterAnother2Ways.addNode(n1, "n1");
		oneAfterAnother2Ways.addNode(n2, "n2");
		oneAfterAnother2Ways.addEdge("n1", "n2");
		oneAfterAnother2Ways.addEdge("n2", "n1");
		oneAfterAnother2Ways.addNode(n3, "n3");
		oneAfterAnother2Ways.addEdge("n2", "n3");
		oneAfterAnother2Ways.addEdge("n3", "n2");

		// List of source
		sourceList.get(0).add("n2");
		destinationList.get(0).add("n5");
	    sourceList.get(1).add("n3");
	    sourceList.get(1).add("n2");
	    destinationList.get(1).add("n5");
	    sourceList.get(2).add("n4");
	    sourceList.get(2).add("n2");
	    destinationList.get(2).add("n1");
	    sourceList.get(3).add("n2");
	    destinationList.get(3).add("n5");
	    destinationList.get(3).add("n1");
   }
	
	@Test
	public void createGraphTest(){
		assertEquals(oneAfterAnother.getName(),"oneAfterAnother");
		assertEquals(clique.getName(),"clique");
		assertEquals(oneAfterAnother2Ways.getName(),"oneAfterAnother2Ways");
		assertEquals(noNodes.getNumOfNodes(),0);
		assertEquals(noNodes.getName(),"noNodes");
	}
	
	@Test
	public void addNodeTest(){
		assertEquals(noNodes.addNode(n1, "n1"),Result.Success);
		assertEquals(noNodes.getNumOfNodes(),1);
		assertEquals(noNodes.getNodesString(),String.format("%s contains: %s", "noNodes","n1"));
		// check that the node still isn't connected to anything
		assertEquals(noNodes.getParentsString("n1"),String.format("the parents of %s in %s are:", "n1","noNodes"));
		assertEquals(noNodes.getChildrenString("n1"),String.format("the children of %s in %s are:", "n1","noNodes"));

		// fails
		assertEquals(noNodes.getNumOfNodes(),1);
		assertEquals(noNodes.addNode(n2, "n1"),Result.itemAlreadyExists);
		assertEquals(noNodes.addNode(n1, "n1"),Result.itemAlreadyExists);
		// check that the node still isn't connected to anything
		assertEquals(noNodes.getParentsString("n1"),String.format("the parents of %s in %s are:", "n1","noNodes"));
		assertEquals(noNodes.getChildrenString("n1"),String.format("the children of %s in %s are:", "n1","noNodes"));
	}
	
	@Test
	public void addEdgeTest(){
		noNodes.addNode(n1, "n1");
		noNodes.addNode(n2, "n2");
		noNodes.addNode(n3, "n3");

		assertEquals(noNodes.addEdge("n2", "n3"),Result.Success);
		assertEquals(noNodes.addEdge("n1", "n3"),Result.Success);
		assertEquals(noNodes.addEdge("n1", "n2"),Result.Success);

		assertEquals(noNodes.getChildrenString("n1"),String.format("the children of %s in %s are: %s %s", "n1","noNodes","n2","n3"));
		assertEquals(noNodes.getChildrenString("n2"),String.format("the children of %s in %s are: %s", "n2","noNodes","n3"));
		assertEquals(noNodes.getChildrenString("n3"),String.format("the children of %s in %s are:", "n3","noNodes"));
		assertEquals(noNodes.getParentsString("n1"),String.format("the parents of %s in %s are:", "n1","noNodes"));
		assertEquals(noNodes.getParentsString("n2"),String.format("the parents of %s in %s are: %s", "n2","noNodes","n1"));
		assertEquals(noNodes.getParentsString("n3"),String.format("the parents of %s in %s are: %s %s", "n3","noNodes","n1","n2"));

		assertEquals(noNodes.addEdge("n2", "n1"),Result.Success);

		assertEquals(noNodes.getChildrenString("n3"),String.format("the children of %s in %s are:", "n3","noNodes"));
		assertEquals(noNodes.getChildrenString("n1"),String.format("the children of %s in %s are: %s %s", "n1","noNodes","n2","n3"));
		assertEquals(noNodes.getChildrenString("n2"),String.format("the children of %s in %s are: %s %s", "n2","noNodes","n1","n3"));
		assertEquals(noNodes.getParentsString("n2"),String.format("the parents of %s in %s are: %s", "n2","noNodes","n1"));
		assertEquals(noNodes.getParentsString("n1"),String.format("the parents of %s in %s are: %s", "n1","noNodes","n2"));
		assertEquals(noNodes.getParentsString("n3"),String.format("the parents of %s in %s are: %s %s", "n3","noNodes","n1","n2"));

	}
	
	@Test
	public void getNodesString(){
		// success 
		// for - 0 runs
		assertEquals(noNodes.getNodesString(),String.format("%s contains:","noNodes"));
		// for - 1 runs
		noNodes.addNode(n1, "n1");
		assertEquals(noNodes.getNodesString(),String.format("%s contains: %s","noNodes","n1"));
		// for - 2 runs
		noNodes.addNode(n2, "n2");
		assertEquals(noNodes.getNodesString(),String.format("%s contains: %s %s","noNodes","n1","n2"));
	}
	
	@Test
	public void getChildrenStringTest(){
		assertEquals(oneAfterAnother.getChildrenString("n4"),null);
		assertEquals(oneAfterAnother.getChildrenString("n3"), String.format("the children of %s in %s are:", "n3","oneAfterAnother"));
		assertEquals(oneAfterAnother.getChildrenString("n1"), String.format("the children of %s in %s are: %s", "n1","oneAfterAnother","n2"));
		assertEquals(oneAfterAnother.getChildrenString("n2"), String.format("the children of %s in %s are: %s", "n2","oneAfterAnother","n3"));
	}
	
	@Test
	public void getParentsStringTest(){
		assertEquals(fathers2Sons2.getParentsString("n4"),null);
		assertEquals(fathers2Sons2.getParentsString("n3"), String.format("the parents of %s in %s are: %s %s", "n3","fathers2Sons2","n1","n2"));
		assertEquals(fathers2Sons2.getParentsString("n1"), String.format("the parents of %s in %s are:", "n1","fathers2Sons2"));
		assertEquals(fathers2Sons2.getParentsString("n2"), String.format("the parents of %s in %s are: %s", "n2","fathers2Sons2","n1"));
	}
	

	
}
	



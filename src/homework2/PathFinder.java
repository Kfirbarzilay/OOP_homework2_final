package homework2;
import java.util.*;

/* <p>G = GraphNode<N>
//  <p>N = node type
   <p>P = Path<N,P>
*/ 
public class PathFinder<G extends Childrenable<G>,N,P extends Path<N,P>>
{

	/**
	 * <p>@param startPaths
	 * <p>@param endNodes
	 * <p>@return shortest path
	 */
	public NodeCountingPath findShortestPath(Graph<N> graph, List<String> startNodeNames, List<String> endNodes)
	{
		// Add to some PathArray (PA) all the MxN paths
		PriorityQueue<NodeCountingPath> allPaths = new PriorityQueue<>();

		// for each path in PA run DFS( s(i) -> t(j) ) ***(After DFS has done each node in the path has it's cost inside, including the cost of back edges)
		for (String startingNode : startNodeNames)
		{
			for (String endNode : endNodes)
			{
				DfsAlgorithm dfs = new DfsAlgorithm((Graph<WeightedNode>) graph, startingNode);
				LinkedList<String> pathList = dfs.callDfs(endNode);
				if (null != pathList) // create path and add to all paths
				{
					Iterator<String> iter = pathList.iterator();

					// Construct the path by the the first node in the list.
					WeightedNode firstNode = (WeightedNode) graph.getNodeByName(iter.next()).getNode();
					NodeCountingPath currPath = new NodeCountingPath(firstNode);
					// Color nodes in white again and reset costs of back edges.
					firstNode.setColor("White");
					firstNode.initNumofBackEdges();
					// Add other nodes if they exist.
					while (iter.hasNext())
					{
						WeightedNode currNode = (WeightedNode) graph.getNodeByName(iter.next()).getNode();
						currPath = currPath.extend(currNode);
						// Color nodes in white again and reset costs of back edges.
						currNode.setColor("White");
						currNode.initNumofBackEdges();
					}

					allPaths.add(currPath); // unchecked Path warning
				}

			}

		}

		return allPaths.poll();
	}

}

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

		// startPaths contains paths, such that each path contains at first the starting nodes

		// endNodes is an array that consist of weighted nodes

		// let s_1, s_2, s_3, ..., s_n be the source nodes we get

		// let t_1, t_2, t_3, ..., t_m be the target nodes we get

		// paths gonna consist of the following: s_1 -> t_1, s_1 -> t_2, s_1 -> t_3, ... s_n -> t_1, ..., s_n -> t_m

		// run DFS for each path (starting node)

		// List<String> mylist = DFS();

		// for all obj in myList
		//graph.getNodeByName(mtList.get(0)).getNode();

		// TODO:
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
				// listOfNodesStr = DFS( s(i) -> t(j) )
				// for each strNode in this list
						// add the node to the current path(not including the first one)
						// color nodes in white again
						// reset costs
				// add path to PQ
		// minimum path is PQ.poll() ***(PQ uses the compareTo of nodeCouningPath)







//
//
//		// first - enter all the startPaths into the active list
//		//         and the paths to allPaths.
//		// get the nodes and put them in the activeNodes list
//		for(int i=0; i<startPaths.size(); i++){
//			N startNode = startPaths.get(i).getEnd();
//			String startNodeName = startNodeNames.get(i);
//			activeNodes.put(startNode, startNodeName);
//		}










//
//
//		// initialize the arguments
//		HashMap<N,String> activeNodes = new HashMap<N,String>();
//		HashMap<N,String> finishedNodes = new HashMap<N,String>();
//
//
//
//		// start the iterations
//		Path<N,P> minimalPath;
//		N endNode;
//		ArrayList<GraphNode<N>> childrenGraphNodes;
//		while(!activeNodes.isEmpty()){
//
//			// for each iteration - get the path with the lowest cost from the active queue
//			minimalPath = allPaths.poll();
//			// get it's end node
//			endNode = minimalPath.getEnd();
//			// if the end node is one of the requested end nodes, it is a minimal path from source to requested end node
//			// so we need to return the path.
//			if(endNodes.contains(endNode)){
//				return minimalPath;
//			}
//
//			// we haven't reached an end node yet, so continue.
//			// get all it's children
//			// to do this we will need node's name and the graph's data
//			String endNodeName = activeNodes.get(endNode);
//			childrenGraphNodes = graph.getChildren(endNodeName);
//			// remove the endNode from the active list
//			activeNodes.remove(endNode);
//
//			// create paths using the children. use only children that aren't in the active or finished list
//			for (GraphNode<N> childGraphNode : childrenGraphNodes){
//				N child = childGraphNode.getNode();
//				String childName = childGraphNode.getName();
//				if(!activeNodes.keySet().contains(child) && !finishedNodes.keySet().contains(child)){
//					// create a new path and add it to the active paths queue
//					Path<N,P> newPath = minimalPath.extend(child);
//					allPaths.add(newPath);
//					// add the node to the active nodes list
//					activeNodes.put(child,childName);
//				}
//			}
//
//			// we have finished with the current node, add it to the finished list
//			finishedNodes.put(endNode,endNodeName);
//			// reiterate with the next minimal path
//		}
//
//		// we reached here only if startNodes was empty. in this case - return null path
//		return null;
//	}
//
}

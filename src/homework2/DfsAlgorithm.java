package homework2;

import java.util.*;


/**
 * This class implements a DFS algorith run on a graph. Id does not permits null values.
 * DFS runs on weighted nodes.
 * List holds the node names of the graph walk. The DFS walk the graph by giving it the graph and starting node
 * and could get an end node so if it get the destination so the graph walk will end at the moment the algorithm find it.
 */
public class DfsAlgorithm {

    // List holds the path and will be printed at the end as the path from source to target (if target exists)
    private LinkedList<String> list;

    private Graph<WeightedNode> graph;

    private String startNodeStr;

    // Representation Invariant:
    // Assume the parameters are correct
    // 1. graph != null
    // 2. startNodStr != null

    // Abstraction function:
    // String graph - The graph to run DFS on
    // LinkedList<String> list - List of the nodes name by the order the DRS runs
    // startNodeStr - The name of the starting node that we start to run the algorithm on



    public DfsAlgorithm (Graph<WeightedNode> graph, String startNodeStr)
    {
        this.list = new LinkedList<>();
        this.graph = graph;
        this.startNodeStr = startNodeStr;
    }

    public LinkedList<String> callDfs()
    {
        Dfs(this.graph, this.startNodeStr);
        return list;
    }

    public LinkedList<String> callDfs(String targetNodeStr)
    {
        if ( Dfs(this.graph, this.startNodeStr, targetNodeStr) )
        {
            return list;
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    private void Dfs(Graph<WeightedNode> graph, String startNodeStr)
    {
        // Create priority queue
        PriorityQueue<WeightedNode> priorityQueue = new PriorityQueue<>();

        // Get all children
        ArrayList<GraphNode<WeightedNode>> currChildren = graph.getChildren(startNodeStr);
        // Add all children to priority queue
        Iterator<GraphNode<WeightedNode>> iter = currChildren.iterator();
        while (iter.hasNext())
        {
            WeightedNode node = iter.next().getNode();
            priorityQueue.add(node);
        }

        // Add current node to the "list"
        this.list.add(startNodeStr);

        // Color current node in grey
        ((GraphNode<WeightedNode>)graph.getNodeByName(startNodeStr)).getNode().setColor("Grey");

        // For every child, if it's not white colored, increase back edge counter
        for (GraphNode<WeightedNode> child : currChildren)
        {
            // If the child was already visited, increase current node back edge counter
            if ( child.getNode().getColor().equals("Grey") )
            {
                ((WeightedNode)graph.getNodeByName(startNodeStr).getNode()).increaseBackEdges();
            }
        }

        // For each child of current node
        int pqSize = priorityQueue.size();
        for(int i = 0; i < pqSize; ++i)
        {
            WeightedNode currChild = priorityQueue.poll();
            // If child grey/not visited (?)
            if ( !list.contains(currChild.getName()) )
            {
                Dfs(graph, currChild.getName());
            }

        }
        // Color current node in black
        ((GraphNode<WeightedNode>)graph.getNodeByName(startNodeStr)).getNode().setColor("Black");

        return;
    }


    @SuppressWarnings("Duplicates")
    public boolean Dfs(Graph<WeightedNode> graph, String startNodeStr, String endNodeStr)
    {
        // Create priority queue
        PriorityQueue<WeightedNode> priorityQueue = new PriorityQueue<>();
        // Get all children
        ArrayList<GraphNode<WeightedNode>> currChildren = graph.getChildren(startNodeStr);
        // Add all children to priority queue
        Iterator<GraphNode<WeightedNode>> iter = currChildren.iterator();
        while (iter.hasNext())
        {
            WeightedNode node = iter.next().getNode();
            priorityQueue.add(node);
        }

        // Add current node to the "list"
        this.list.add(startNodeStr);

        // Color current node in grey
        ((GraphNode<WeightedNode>)graph.getNodeByName(startNodeStr)).getNode().setColor("Grey");

        // For every child, if it's not white colored, increase back edge counter
        for (GraphNode<WeightedNode> child : currChildren)
        {
            // If the child was already visited, increase current node back edge counter
            if ( child.getNode().getColor().equals("Grey") )
            {
                ((WeightedNode)graph.getNodeByName(startNodeStr).getNode()).increaseBackEdges();
            }
        }

        // If current node is the target
        if(startNodeStr.equals(endNodeStr))
        {
            // DFS is over successfully
            return true;
        }

        // For each child of current node
        int pqSize = priorityQueue.size();
        for(int i = 0; i < pqSize; ++i)
        {
            WeightedNode currChild = priorityQueue.poll();
            // If child grey/not visited (?)
            if ( !list.contains(currChild.getName()) )
            {
                if ( Dfs(graph, currChild.getName(), endNodeStr) )
                {
                    return true;
                }
            }

        }
        // Color current node in black
        ((GraphNode<WeightedNode>)graph.getNodeByName(startNodeStr)).getNode().setColor("Black");

        return false;
    }
}

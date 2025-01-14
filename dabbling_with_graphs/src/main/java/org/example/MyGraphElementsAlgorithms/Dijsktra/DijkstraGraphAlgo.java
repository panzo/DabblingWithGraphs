package org.example.MyGraphElementsAlgorithms.Dijsktra;

import java.util.*;
import java.util.stream.Stream;

/**
 * Class that holds the Dijsktra shortest path algorithm.
 *
 * @author David Nistor
 */
public class DijkstraGraphAlgo {
    /**
     * Calculate the shortest path of each {@code NodeForDijkstra} node of the connected graph that includes
     * {@code NodeForDijkstra} sourceNode.
     *
     * @param sourceNode the source {@code NodeForDijkstra} node
     */
    public void calculateShortestPath(NodeForDijkstra sourceNode) {
        sourceNode.setDistance(0);
        Set<NodeForDijkstra> settledNodes = new HashSet<>();
        Queue<NodeForDijkstra> unsettledNodes = new PriorityQueue<>(Collections.singleton(sourceNode));

        while(!unsettledNodes.isEmpty()) {
            NodeForDijkstra currentNode = unsettledNodes.poll();

            currentNode.getAdjacentNodes().entrySet().stream().filter(nodeIntegerEntry ->
                    !settledNodes.contains(nodeIntegerEntry.getKey()))
                    .forEach(nodeIntegerEntry -> {
                        evaluateDistanceAndPath(nodeIntegerEntry.getKey(), nodeIntegerEntry.getValue(), currentNode);
                        unsettledNodes.add(nodeIntegerEntry.getKey());
                    });

            settledNodes.add(currentNode);
        }
    }

    /**
     * Decides whether to include an edge in the shortest path to a node.
     *
     * @param adjacentNode the target {@code NodeForDijkstra} adjacentNode
     * @param edgeWeight the weight between the {@code sourceNode} and {@code adjacentNode}
     * @param sourceNode the source {@code NodeForDijkstra} node
     */
    private void evaluateDistanceAndPath(NodeForDijkstra adjacentNode, Integer edgeWeight, NodeForDijkstra sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;

        if(newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPathToNode(Stream.concat(sourceNode.getShortestPathToNode().stream(), Stream.of(sourceNode)).toList());
        }
    }
}


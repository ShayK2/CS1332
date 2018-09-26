import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
        Graph<T> graph) throws IllegalArgumentException {
        if (start == null || graph == null
                || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex and graph "
                + "cannot be null, and start vertex must be in the graph.");
        }

        List<Vertex<T>> visitedList = new ArrayList<Vertex<T>>();
        Set<Vertex<T>> visitedSet = new HashSet<Vertex<T>>();
        Queue<Edge<T>> edgeQueue = new LinkedList<Edge<T>>();
        Map<Vertex<T>, List<Edge<T>>> adjList = graph.getAdjList();

        visitedList.add(start);
        visitedSet.add(start);
        edgeQueue.addAll(adjList.get(start));

        while (!edgeQueue.isEmpty()) {
            Edge<T> next = edgeQueue.remove();
            if (!visitedSet.contains(next.getV())) {
                visitedList.add(next.getV());
                visitedSet.add(next.getV());
                for (Edge<T> edge : adjList.get(next.getV())) {
                    if (!visitedSet.contains(edge.getV())) {
                        edgeQueue.add(edge);
                    }
                }
            }
        }

        return visitedList;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
        Graph<T> graph) throws IllegalArgumentException {
        if (start == null || graph == null
                || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex and graph "
                    + "cannot be null, and start vertex must be in the graph.");
        }

        return dfs(start, graph, new ArrayList<Vertex<T>>(),
                new HashSet<Vertex<T>>());
    }

    /**
     * Given a starting node, graph to search and list of
     * visited vertices, continues the DFS process. Adds
     * the node to the visited list, then goes to each
     * unvisited neighbor and adds to the DFS from that vertex.
     * Visiting the neighbors in order using recursion
     * simulates a stack that adds a visited node's neighbors
     * to the top during each iteration.
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param visitedList the list of visited vertices
     * @param visitedSet the set of visited vertices (for use of contains())
     * @return DFS starting from the given vertex, excluding visited vertices
     */
    private static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph,
        List<Vertex<T>> visitedList, HashSet<Vertex<T>> visitedSet) {
        visitedList.add(start);
        visitedSet.add(start);
        List<Edge<T>> neighbors = graph.getAdjList().get(start);
        for (Edge<T> edge: neighbors) {
            if (!visitedSet.contains(edge.getV())) {
                visitedList = dfs(edge.getV(), graph, visitedList, visitedSet);
            }
        }

        return visitedList;
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
        Graph<T> graph) throws IllegalArgumentException {
        if (start == null || graph == null
                || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex and graph "
                    + "cannot be null, and start vertex must be in the graph.");
        }

        Map<Vertex<T>, Integer> distances = new HashMap<Vertex<T>, Integer>();
        for (Vertex<T> v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        Set<Vertex<T>> visitedSet = new HashSet<Vertex<T>>();
        PriorityQueue<Edge<T>> edgeQueue = new PriorityQueue<Edge<T>>();
        Map<Vertex<T>, List<Edge<T>>> adjList = graph.getAdjList();

        edgeQueue.addAll(adjList.get(start));
        visitedSet.add(start);

        while (!edgeQueue.isEmpty()
                && visitedSet.size() < graph.getVertices().size()) {
            Edge<T> next = edgeQueue.remove();
            if (!visitedSet.contains(next.getU())) {
                visitedSet.add(next.getU());
            }

            if (distances.get(next.getV()) > next.getWeight()) {
                distances.put(next.getV(), next.getWeight());
            }

            for (Edge<T> edge : adjList.get(next.getV())) {
                if (!visitedSet.contains(edge.getV())) {
                    edgeQueue.add(new Edge<T>(edge.getU(), edge.getV(),
                            edge.getWeight() + distances.get(next.getV())));
                }
            }
        }

        return distances;
    }

    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph)
        throws IllegalArgumentException {
        if (start == null || graph == null
                || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex and graph "
                    + "cannot be null, and start vertex must be in the graph.");
        }

        List<Vertex<T>> visitedList = new ArrayList<Vertex<T>>();
        Set<Vertex<T>> visitedSet = new HashSet<Vertex<T>>();
        PriorityQueue<Edge<T>> edgeQueue = new PriorityQueue<Edge<T>>();
        Set<Edge<T>> mst = new HashSet<Edge<T>>();
        Map<Vertex<T>, List<Edge<T>>> adjList = graph.getAdjList();

        visitedList.add(start);
        visitedSet.add(start);
        edgeQueue.addAll(adjList.get(start));

        while (!edgeQueue.isEmpty()) {
            Edge<T> next = edgeQueue.remove();
            if (!visitedSet.contains(next.getV())) {
                mst.add(next);
                mst.add(new Edge<T>(next.getV(), next.getU(),
                        next.getWeight()));
                visitedList.add(next.getV());
                visitedSet.add(next.getV());
                for (Edge<T> edge : adjList.get(next.getV())) {
                    if (!visitedSet.contains(edge.getV())) {
                        edgeQueue.add(edge);
                    }
                }
            }
        }

        if (mst.size() < graph.getVertices().size() - 1) {
            return null;
        }

        return mst;
    }
}
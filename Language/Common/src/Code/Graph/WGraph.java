package Code.Graph;

import java.lang.reflect.Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * This class implements data structures and algorithms for weighted graphs.
 *
 * @author Ashok Rajpurohit (ashok1113@gmail.com)
 * @see Graph
 */
public class WGraph {
    private int[] edgeOut, edgeIn;
    private int nodeCount, edgeCount;
    private final int capacity;
    private LinkedList<Edge>[] list;
    public final boolean directed;
    private final static LinkedList<Edge> listRef = new LinkedList<Edge>();

    private WGraph() {
        // do nothing
        capacity = 0;
        directed = false;
    }

    public WGraph(int nodes) {
        directed = false;
        capacity = nodes;
        edgeIn = new int[nodes];
        edgeOut = new int[nodes];
        LinkedList<Edge> temp = new LinkedList<Edge>();
        list =
(LinkedList<WGraph.Edge>[])Array.newInstance(listRef.getClass(), nodes);
    }

    public WGraph(int nodes, boolean directed) {
        this.directed = directed;
        capacity = nodes;
        edgeIn = new int[nodes];
        edgeOut = new int[nodes];
        LinkedList<Edge> temp = new LinkedList<Edge>();
        list =
(LinkedList<WGraph.Edge>[])Array.newInstance(listRef.getClass(), nodes);
    }

    public int nodeCount() {
        return nodeCount;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public int size() {
        return capacity;
    }

    public void addEdge(int from, int to) {
        valid(from);
        valid(to);
        if (from == to)
            return;

        if (directed) {
            add(from, to, 1);
            return;
        }

        if (from < to)
            add(from, to, 1);
        else
            add(to, from, 1);
    }

    public void addEdge(int from, int to, int weight) {
        valid(from);
        valid(to);

        if (from == to)
            return;

        if (directed) {
            add(from, to, weight);
            return;
        }

        if (from < to)
            add(from, to, weight);
        else
            add(to, from, weight);
    }

    public void add(int from, int to, int weight) {
        if (list[from] == null)
            list[from] = new LinkedList<Edge>();

        Edge edge = new Edge(from, to, weight);
        if (list[from].contains(edge))
            return;

        list[from].add(edge);
        edgeOut[from]++;
        edgeIn[to]++;
        edgeCount++;

        if (edgeOut[from] + edgeIn[from] == 1)
            nodeCount++;

        if (edgeOut[to] + edgeIn[to] == 1)
            nodeCount++;
    }

    public void valid(int v) {
        if (v < 0 || v >= capacity)
            throw new RuntimeException("Invalid node value: " + v);
    }

    public LinkedList<Edge> edgeList() {
        LinkedList<Edge> edgeList = new LinkedList<Edge>();
        for (int i = 0; i < capacity; i++) {
            if (list[i] != null)
                for (Edge e : list[i])
                    edgeList.add(e);
        }

        return edgeList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++)
            if (list[i] != null) {
                for (Edge e : list[i])
                    sb.append(e).append('\n');
            }

        return sb.toString();
    }

    public final static EdgeWeightCompare edgeWeightComparator =
        new EdgeWeightCompare();

    final static class EdgeWeightCompare implements Comparator<Edge> {

        public int compare(WGraph.Edge o1, WGraph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    final static class EdgeSourceCompare implements Comparator<Edge> {

        public int compare(WGraph.Edge o1, WGraph.Edge o2) {
            return o1.from - o2.from;
        }
    }

    final static class EdgeDestinationCompare implements Comparator<Edge> {

        public int compare(WGraph.Edge o1, WGraph.Edge o2) {
            return o1.to - o2.to;
        }
    }

    final static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int source, int dest, int w) {
            from = source;
            to = dest;
            weight = w;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;

            if (!(o instanceof Edge))
                return false;

            Edge temp = (Edge)o;
            return from == temp.from && to == temp.to && weight == temp.weight;
        }

        public Edge clone() {
            Edge e = new Edge(from, to, weight);
            return e;
        }

        public String toString() {
            return from + " --> " + to + " | " + weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}

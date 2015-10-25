package Code.Graph;

import Code.DSA.List;

import java.util.Iterator;

/**
 * Graph using Adjacency list.
 * @author Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class GraphList {
    private List<Node>[] nodes;

    public GraphList(int verticeCount) {
        nodes = new List[verticeCount];
    }

    public void addEdge(int from, int to) {
        add(nodes[from], to);
    }

    public void addEdge(int[] from, int[] to) {
        for (int i = 0; i < to.length; i++)
            add(nodes[from[i]], to[i]);
    }

    private static void add(List list, int to) {
        if (list == null)
            list = new List(to);
        else if (!list.contains(to))
            list.add(to);
    }

    public void removeEdge(int from, int to) {
        Iterator iter = nodes[from].iterator();
    }

    final static class Node {
        boolean visited = false;
        int value;

        Node(int d) {
            value = d;
        }

        public boolean equals(Object obj) {
            return true; // later
        }
    }
}

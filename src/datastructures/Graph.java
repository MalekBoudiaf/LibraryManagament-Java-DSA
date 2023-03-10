package datastructures;


public class Graph {
    public class Node implements Comparable {
        private Comparable info;
        private Vector edges;
        private Double d; // a distance/weight variable used for the shortest path algorithm
        private Node p; // a variable used to store the Vertex that lead to the current vertex when finding the shortest path
        private boolean visited;

        public Node(Comparable label) {
            info = label;
            // double implementation of infinity
            d=Double.POSITIVE_INFINITY;
            // previous shortest path node initialized to null
            p=null;
            edges = new Vector();
            visited=false;
        }

        public void addEdge(Edge e) {
            edges.addLast(e);
        }

        public boolean isVisited(){return visited;}

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public int compareTo(Object o) {
            // two nodes are equal if they have the same label
            Node n = (Node) o;
            return n.info.compareTo(info);
        }

        public Comparable getLabel() {
            return info;
        }

        @Override
        public String toString() {
            return info.toString();
        }
    }

    private class Edge implements Comparable {
        private Node toNode;
        private double weight;

        public Edge(Node to, double weight) {
            toNode = to;
            this.weight = weight;
        }

        public int compareTo(Object o) {
            // two edges are equal if they point
            // to the same node.
            // this assumes that the edges are
            // starting from the same node !!!
            Edge n = (Edge) o;
            return n.toNode.compareTo(toNode);
        }

        @Override
        public String toString() {
            return "-->"+toNode.toString()+" (weight="+weight+")";
        }
    }

    private Vector nodes;

    public Graph() {
        nodes = new Vector();
    }

    public void addNode(Comparable label) {
        nodes.addLast(new Node(label));
    }

    public void nodesUnvisited(){
        for (int i=0;i<nodes.size();i++){
            ((Node)nodes.get(i)).setVisited(false);
        }
    }

    private Node findNode(Comparable nodeLabel) {
        Node res = null;
        for (int i = 0; i < nodes.size(); i++) {
            Node n = (Node) nodes.get(i);
            if (n.getLabel() == nodeLabel) {
                res = n;
                break;
            }
        }
        return res;
    }

    public void addEdge(Comparable nodeLabel1,
                        Comparable nodeLabel2, double weight) {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        n1.addEdge(new Edge(n2, weight));
    }

    public boolean findPath(Comparable nodeLabel1,
                            Comparable nodeLabel2) {
        nodesUnvisited();
        // Vector visitedList=new Vector();
        Node startState = findNode(nodeLabel1);
        Node endState = findNode(nodeLabel2);
        Stack toDoList = new Stack();
        toDoList.push(startState);
        while (!toDoList.empty()) {
            Node current = (Node) toDoList.pop();
            current.setVisited(true);
            // visitedList.addLast(current);
            if (current == endState)
                return true;
            else {
                    for (int i = 0; i < current.edges.size(); i++) {
                        Edge e = (Edge) current.edges.get(i);
                        if (! e.toNode.isVisited()){
                        toDoList.push(e.toNode);
                        }
                    }
                }
        }
        return false;
    }
    public Stack findPathRecursive(Comparable startLabel, Comparable endLabel){
        Node startNode=findNode(startLabel);
        Node endNode=findNode(endLabel);
        Stack path=new Stack();
        if (isReachable(startNode,endNode,path)){
            return path;
        }
        return null;
    }

    private Boolean isReachable(Node currentNode,Node endNode,Stack path){
        path.push(currentNode);
        if (currentNode==endNode){
            return true;
        }

        if (currentNode.edges.size()!=0){
            for (int i=0;i<currentNode.edges.size();i++){
                Edge edge=(Edge) currentNode.edges.get(i);
                Node nextNode=edge.toNode;
                if(isReachable(nextNode,endNode,path)){return true;}
            }
        }
        path.pop();
        return false;
    }
    public LinkedList getShortestPath(Comparable sourceLabel,Comparable destinationLabel){
        Node s=findNode(sourceLabel);
        Node d=findNode(destinationLabel);
        bellmanFord(s);
        LinkedList shortestPath=new LinkedList();
        Node currentNode=d;
        while(currentNode!=s){
            // if the node is reachable add it to the path
           if(currentNode.d!=Double.POSITIVE_INFINITY) {
               shortestPath.addFirst(currentNode.info);
               // back-track to the previous source vertex
               currentNode=currentNode.p;
           }
           // else path is not reachable and path doesn't exist in the graph return null or empty path
           else{
              return null;
           }
        }
        // add the source vertex to the path
        shortestPath.addFirst(s.info);
       return shortestPath;
    }

    private void bellmanFord(Node sourceNode){
        // reset all nodes (n.d=infinity and n.p=null)
        resetNodes();
        // get the node object for the source node (vertex)
        // Node sourceNode=findNode(sourceLabel);
        // set distance for source vertex to 0
        sourceNode.d=0.0;
        // do V-1 iterations over the graph
        for (int i=0;i<nodes.size()-1;i++){
            // a Boolean flag to identify when no changes are being made and shortest paths are found
            Boolean changesFlag=false;
            // going through all the edges in the graph for the current iteration
            for (int nodeIndex=0;nodeIndex<nodes.size()-1;nodeIndex++){
                // get the current vertex
                Node u=(Node)nodes.get(nodeIndex);
                // if we know how to reach the current vertex ie: vertex.d != infinity
                // then relax all its edges
                if (u.d!=Double.POSITIVE_INFINITY){
                    // relaxing all edges of current Vertex
                    for (int edgeIndex=0;edgeIndex<u.edges.size();edgeIndex++){
                        Edge e=(Edge)u.edges.get(edgeIndex);
                        Node v= e.toNode;
                        Double w=e.weight;
                        // if a relaxation was performed relax() will return true
                        if (relax(u,v,w)){
                            changesFlag=true;
                        }
                    }
                }
            }
            // at the end of each iteration through all the edges check if no changes were made
            // using the changesFlag ie: no changes= (changesFlag=false)
            // then if no changes return iterating and return (the shortest paths are found)
            if (!changesFlag){
                return;
            }
        }

    }
    private Boolean relax(Node u,Node v,Double weight){
        // if the shortest path can be improved by edge n1-->n2 then update the n2.d and p
        if (v.d>(u.d+weight)){
            v.d=u.d+weight;
            v.p=u;
            // if a relaxation was performed return true to be used as a flag for shortestPath()
            return true;
        }
        // if no relaxation was performed return false
        return false;
    }

    private void resetNodes(){
        for (int i=0;i<nodes.size();i++){
            Node n=(Node)nodes.get(i);
            n.d=Double.POSITIVE_INFINITY;
            n.p=null;
        }
    }

    @Override
    public String toString() {
        String nodesStr="Nodes:\n";
        String edgesStr="Edges:\n";
        for(int i=0;i<this.nodes.size();i++){
            Node currentNode=(Node) nodes.get(i);
            nodesStr+=currentNode.toString();
            for(int j=0;j<currentNode.edges.size();j++){
                edgesStr+=currentNode.toString();
                Edge currentEdge=(Edge) currentNode.edges.get(j);
                edgesStr+=currentEdge.toString();
                edgesStr+="\n";
            }
            nodesStr+="\n";
        }
        return nodesStr+edgesStr;
    }
}

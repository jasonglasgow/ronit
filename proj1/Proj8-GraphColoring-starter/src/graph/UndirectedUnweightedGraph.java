package graph;
import java.util.ArrayList;
/**
 * This class implements general operations on a graph as specified by UndirectedGraphADT.
 * It implements a graph where data is contained in Vertex class instances.
 * Edges between verticies are unweighted and undirected.
 * A graph coloring algorithm determines the chromatic number. 
 * Colors are represented by integers. 
 * The maximum number of vertices and colors must be specified when the graph is instantiated.
 * You may implement the graph in the manner you choose. See instructions and course material for background.
 */
 
 public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T> {
   // private class variables here.
   
   private int MAX_VERTICES;
   private int MAX_COLORS;
    // TODO: Declare class variables here.
  private boolean[][] edgeMap;
  private ArrayList<Vertex> vertices;
  int numVerts;

   
   /**
    * Initialize all class variables and data structures. 
   */   
   public UndirectedUnweightedGraph (int maxVertices, int maxColors){
      MAX_VERTICES = maxVertices;
      MAX_COLORS = maxColors; 
     // TODO: Implement the rest of this method.
     edgeMap =new boolean[MAX_VERTICES][MAX_VERTICES];
     vertices = new ArrayList<Vertex>(MAX_VERTICES);
     numVerts = 0;

   }

   /**
    * Add a vertex containing this data to the graph.
    * Throws Exception if trying to add more than the max number of vertices.
   */
   public void addVertex(T data) throws Exception {
    if (numVerts >= MAX_VERTICES) {
      throw new Exception();
    }
    vertices.add(new Vertex(data));
    ++numVerts;

   }
   
   // returns -1 if data is not in graph
   private int getIndexOf(T data) {
    for (int i = 0; i < numVerts; ++i) {
      if (vertices.get(i).getData().equals(data)) {
        return i;
      }
    }
    return -1;
   }
   /**
    * Return true if the graph contains a vertex with this data, false otherwise.
   */
   public boolean hasVertex(T data){
      if (getIndexOf(data) == -1) {
        return false;
      }
      return true;
   } 

   /**
    * Add an edge between the vertices that contain these data.
    * Throws Exception if one or both vertices do not exist.
   */   
   public void addEdge(T data1, T data2) throws Exception{    
    int ind1 = getIndexOf(data1);
    int ind2 = getIndexOf(data2);
    if (ind1 == -1 || ind2 == -1) {
      throw new Exception();
    }
    edgeMap[ind1][ind2] = true;
    edgeMap[ind2][ind1] = true;

   }

   /**
    * Get an ArrayList of the data contained in all vertices adjacent to the vertex that
    * contains the data passed in. Returns an ArrayList of zero length if no adjacencies exist in the graph.
    * Throws Exception if a vertex containing the data passed in does not exist.
   */   
   public ArrayList<T> getAdjacentData(T data) throws Exception{
    ArrayList<T> result = new ArrayList<T>();
    int ind = getIndexOf(data);
    if (ind == -1) {throw new Exception();}
    for (int i = 0; i < numVerts; ++i) {
      if (edgeMap[ind][i]) {
        result.add((T)vertices.get(i).getData());
      }
    }
    return result;
   }
   
   /**
    * Returns the total number of vertices in the graph.
   */   
   public int getNumVertices(){
    return numVerts;
   }

   /**
    * Returns the total number of edges in the graph.
   */   
   public int getNumEdges(){
    int numEdges = 0;
    for (int i = 0; i < numVerts; ++i) {
      for (int j = i; j < numVerts; ++j) {
        if (edgeMap[i][j]) {
          ++numEdges;
        }
      }
    }
    return numEdges;
   }

    private int getLowestUnusedColor(ArrayList<Vertex> neighbors) throws Exception{
      // int[] colorsUsed = new int[MAX_COLORS];
      ArrayList<Integer> colorsUsed = new ArrayList<Integer>();
      for (Vertex vert : neighbors) {
        if (!colorsUsed.contains(vert.getColor())) {
        colorsUsed.add(vert.getColor());
        }
      }
      int min = 0;
      while (colorsUsed.contains(min)) {
        ++min;
        if (min >= MAX_COLORS) {
          throw new Exception("too many colors needed");
        }
      }
      return min;

   }

   private int getChromaticNumber(int vertex) {
     return -1;
   }


   private ArrayList<Vertex> getAdjacent(Vertex vert){
    ArrayList<Vertex> adjVerts = new ArrayList<Vertex>();
    int i = vertices.indexOf(vert);
    for (int j = 0; j< numVerts; ++j) {
      if (edgeMap[i][j]) {
        adjVerts.add(vertices.get(j));
      }

    }
    return adjVerts;
   }

   /**
    * Returns the minimum number of colors required for this graph as 
    * determined by a graph coloring algorithm.
    * Throws an Exception if more than the maximum number of colors are required
    * to color this graph.
   */   
   public int getChromaticNumber() throws Exception{
    // int maxColorsYet = 0;
    int colorToUse = 0;
    int highestColor = -1;
    Vertex curVert;
    for (int i = 0; i < numVerts; ++i) {
      curVert = vertices.get(i);
      if (curVert.getColor() == -1) { 
        ArrayList<Vertex> adjVerts = getAdjacent(curVert);
        curVert.setVisited(true);
        colorToUse = getLowestUnusedColor(adjVerts);
        curVert.setColor(colorToUse);  
      }
      highestColor = Math.max(highestColor, colorToUse);
    }   
    return highestColor + 1;
    // return maxColorsYet;
   }
   
private void resetVertices() {
  for (Vertex vert : vertices) {
    vert.setColor(-1);
    vert.setVisited(false);
  }
}
   
}
import java.io.*;
import java.util.*;

public class P4 {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] t;
    
    Graph d = new Graph ();
    
    for (int n = 1; n <= N; n++) {
      t = in.readLine ().split (" ");
      int K = Integer.parseInt (t [0]);
      
      for (int k = 1; k <= K; k++) {
        d.addEdge (new Vertex (n), new Vertex (Integer.parseInt (t [k])));
      }
    }
    
    System.out.println (d);

    d.fix ();

    System.out.println (d);
    
    d.trav (new Vertex (1));
  }
}

class Vertex implements Comparable <Vertex> {
  int V;
  
  public int compareTo (Vertex v) {
    return V - v.V;
  }
  
  public String toString () {
    return Integer.toString (V);
  }
  
  public int hashCode () {
    return V;
  }
  
  public boolean equals (Object o) {
    Vertex v = ((Vertex)o);
    
    return v.V == V;
  }
  
  public Vertex (int V) {
    this.V = V;
  }
}

class Graph {
  
  NavigableMap <Vertex, NavigableSet <Vertex>> map = new TreeMap <Vertex, NavigableSet <Vertex>> ();//parent to child
  NavigableMap <Vertex, Vertex> map2 = new TreeMap <Vertex, Vertex> ();//child to parent
  
  public void fix () {
    Vertex v = rule (new Vertex (1), 1);
    
    if (v != null) {
      Vertex p = map2.get (v);
      //map.get (p).remove (v);
      map.remove (p);
      map.remove (v);
      map2.remove (v);
      
      map.put (v, new TreeSet <Vertex> (Collections.reverseOrder ()));
      map.get (v).add (p);
      
      map2.put (p, v);
    }
  }
  
  public Vertex rule (Vertex curr, int last) {
    //System.out.println (curr.V);
    
    for (Vertex i : map.get (curr)) {
      if (i.V <= last) {
        return i;
      }
      
      Vertex res = rule (i, i.V);
      
      if (res != null) {
        return res;
      }
    }
    
    return null;
  }
  
  public String toString () {
    StringBuilder b = new StringBuilder ();
    
    for (Vertex i : map.keySet ()) {
      b.append (i.toString () + " - " + map.get (i) + "\n");
    }
    
    return b.toString ();
  }
  
  public void trav (Vertex c) {
    System.out.print (c + " ");
    
    for (Vertex i : map.get (c)) {
      if (map.containsKey (i))
      trav (i);
    }
  }
  
  public void addVertex (Vertex n) {
    if (!map.containsKey (n)) {
      map.put (n, new TreeSet <Vertex> (Collections.reverseOrder ()));
    }
  }
  
  public void addEdge (Vertex start, Vertex end) {
    addVertex (start);
    addVertex (end);
    
    map.get (start).add (end);
    map2.put (end, start);
  }
  
  public Graph () {}
}
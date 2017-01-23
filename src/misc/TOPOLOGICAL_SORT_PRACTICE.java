import java.io.*;

public class TOPOLOGICAL_SORT_PRACTICE {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    Graph g = new Graph (N);
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      g.addEdge (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
    }
    
    g.topo ();
  }

  private static class Graph {
    static boolean [][] matrix;
    static int [] indegree;

    public void topo () {
      int successor = 0;

      for (int i = 0; i < matrix.length; i++) {//finds next vertex with 0 indegree, and returns it. if no more are present before for loop ends this implies there is a cycle, and impossible to topologically sort the rest
        successor = next ();

        if (successor == -1) {
          System.out.println ("cycle");
          break;
        }
        else {
          System.out.print (successor + " -> ");
        }
      }
    }

    public int next () {//finds vertex with indegree of 0, and disconnects it from the rest of the graph
      for (int i = 0; i < indegree.length; i++) {
        if (indegree [i] == 0) {
          indegree [i]--;

          for (int x = 0; x < matrix [0].length; x++) {
            if (matrix [i][x]) {
              matrix [i][x] = false;
              indegree [x]--;
            }
          }

          return i;
        }
      }

      return -1;
    }

    public void addEdge (int S, int E) {
      matrix [S][E] = true;
      indegree [E]++;
    }

    public Graph (int N) {
      matrix = new boolean [N][N];
      indegree = new int [N];
    }
  }
}
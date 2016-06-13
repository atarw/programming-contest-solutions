import java.io.*;

public class BREAKING_THE_FRIEND_CHAIN {
  
  static int [] parent, rank;
  
  public static int find (int i) {
    if (parent [i] != i) {
      parent [i] = find (parent [i]);
    }
    
    return parent [i];
  }
  
  public static void union (int a, int b) {
    int r1 = find (a), r2 = find (b);
    
    if (r1 != r2) {
      if (rank [r1] > rank [r2]) {
        parent [r2] = r1;
        rank [r1]++;
      }
      else if (rank [r1] < rank [r2]) {
        parent [r1] = r2;
        rank [r2]++;
      }
      else {
        parent [r2] = r1;
        rank [r1]++;
      }
      
      find (a); find (b);
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    parent = new int [N];
    rank = new int [N];
    
    for (int n = 0; n < N; n++) {
      parent [n] = n;
    }
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      union (Integer.parseInt (t [0]) - 1, Integer.parseInt (t [1]) - 1);
    }
    
    t = in.readLine ().split (" ");
    System.out.print (find (Integer.parseInt (t [0]) - 1) == find (Integer.parseInt (t [1]) - 1) ? 1 : 0);
  }
}
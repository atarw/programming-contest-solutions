import java.io.*;
import java.util.*;

public class DIY_CHRISTMAS_TREE {
  
  static List <Integer> [] list;
  static int [] post;
  static int time = 1;
  
  public static void post (int u) {
    post [u] = time;
    time++;
    
    if (list [u] != null) {
      for (int v = list [u].size () - 1; v >= 0; v--) {
        post (list [u].get (v));
      }
    }
  }
  
  public static void pre (int u) {
    System.out.print (post [u] + " ");
    
    if (list [u] != null) {
      for (int v = 0; v < list [u].size (); v++) {
        pre (list [u].get (v));
      }
    }
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ()), L;
    list = new ArrayList [N];
    post = new int [N];
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      L = Integer.parseInt (t [0]);
      
      list [n] = new ArrayList <Integer> ();
      
      for (int i = 1; i <= L; i++) {
        list [n].add (Integer.parseInt (t [i]) - 1);
      }
    }
    
    post (0);
    pre (0);
  }
}
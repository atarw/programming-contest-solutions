import java.io.*;
import java.util.*;

public class CCC_07_S4_WATERPARK {
  
  static List <Integer> [] list;
  static int [] caché;
  
  public static int ways (int curr) {
    if (caché [curr] == 0 && list [curr] != null) {
      for (int i = 0; i < list [curr].size (); i++) {
        caché [curr] += ways (list [curr].get (i));
      }
    }
    
    return caché [curr];
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ()), x;
    list = new ArrayList [N]; caché = new int [N];
    
    while (true) {
      t = in.readLine ().split (" ");
      
      if (t [0].equals ("0")) {
        break;
      }
      
      x = Integer.parseInt (t [0]) - 1;
      
      if (list [x] == null) {
        list [x] = new ArrayList <Integer> ();
      }
      
      list [x].add (Integer.parseInt (t [1]) - 1);
    }
    
    caché [N - 1] = 1;
    System.out.print (ways (0));
  }
}
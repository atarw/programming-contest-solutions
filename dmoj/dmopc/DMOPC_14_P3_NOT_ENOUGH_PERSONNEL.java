import java.io.*;
import java.util.*;

public class DMOPC_14_P3_NOT_ENOUGH_PERSONNEL {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    NavigableMap <Integer, Veteran> map = new TreeMap <Integer, Veteran> ();
    
    int N = Integer.parseInt (in.readLine ());
    String [] t;
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      Veteran v = new Veteran (t [0], Integer.parseInt (t [1]));
      
      if (!map.containsKey (v.S)) {
        map.put (v.S, v);
      }
    }
    
    int Q = Integer.parseInt (in.readLine ());
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      int S = Integer.parseInt (t [0]);
      int D = Integer.parseInt (t [1]);
      
      Integer key = map.ceilingKey (S);
      
      if (key != null && key - S <= D) {
        System.out.println (map.get (key).name);
      }
      else {
        System.out.println ("No suitable teacher!");
      }
    }
  }
}

class Veteran {
  String name;
  int S;
  
  public int hashCode () {
    return S * 17 + name.hashCode ();
  }
  
  public boolean equals (Object o) {
    Veteran v = ((Veteran) o);
    
    return v.S == S && v.name.equals (name);
  }
  
  public Veteran (String name, int S) {
    this.name = name;
    this.S = S;
  }
}
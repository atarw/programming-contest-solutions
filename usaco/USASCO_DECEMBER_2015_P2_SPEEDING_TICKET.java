import java.io.*;
import java.util.*;

public class USASCO_DECEMBER_2015_P2_SPEEDING_TICKET {
  
  static NavigableMap <Integer, Integer> official = new TreeMap <Integer, Integer> ();//location 0-100 to speed
  static NavigableMap <Integer, Integer> bessie = new TreeMap <Integer, Integer> ();
  
  public static int times () {
    int max = 0, bS;
    Integer offS;
    
    System.out.println ("b " + bessie);
    System.out.println ("o " + official);
    
    for (Integer i : bessie.keySet ()) {
      bS = bessie.get (i);
      offS = official.ceilingKey (i);
      
      System.out.println (i + " to " + offS);
      
      if (offS == null) {
        offS = official.get (official.floorKey (i));
      }
      else {
        offS = official.get (offS);
      }
      
      if (bS > offS) {
        max = Math.max (max, bS - offS);
      }
    }
    
    return max;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    PrintWriter out = new PrintWriter (new OutputStreamWriter (System.out));
    
    //BufferedReader in = new BufferedReader (new FileReader (new File ("speeding.in")));
    //PrintWriter out = new PrintWriter (new FileWriter (new File ("speeding.out")));
    
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    
    int last = 0, L = 0, S = 0;
    
    for (int i = 0; i < N; i++) {
      t = in.readLine ().split (" ");
      L = Integer.parseInt (t [0]);
      S = Integer.parseInt (t [1]);
      
      last += L;
      
      official.put (last, S);
    }
    
    last = 0;
    
    for (int i = 0; i < M; i++) {
      t = in.readLine ().split (" ");
      L = Integer.parseInt (t [0]);
      S = Integer.parseInt (t [1]);
      
      last += L;
      
      bessie.put (last, S);
    }
    
    out.println (times ());
    
    out.close ();
    in.close ();
  }
}
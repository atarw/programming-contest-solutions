import java.io.*;
import java.util.*;

public class CCC_13_S3_CHANCES_OF_WINNING {
  
  static int [] scores = new int [5];//convenience :P
  static List <Match> list = new ArrayList <Match> ();
  static int T;
  
  public static int chance (int ... t) {//G, T1, T2, T3, T4
    if (t [0] == 6) {
      for (int i = 1; i < t.length; i++) {
        if (t [T] <= t [i] && i != T) {
          return 0;
        }
      }
      return 1;
    }
    else {
      int total = chance (update (t, list.get (6 - t [0] - 1), 1));
      total += chance (update (t, list.get (6 - t [0] - 1), 0));
      total += chance (update (t, list.get (6 - t [0] - 1), -1));
      
      return total;
    }
  }
  
  public static int [] update (int [] t, Match m, int result) {//0 = tie, -1 = loss, 1 = win
    int [] t2 = Arrays.copyOf (t, t.length);
    t2 [0]++;
    
    if (result == 0) {
      t2 [m.T1]++;
      t2 [m.T2]++;
    }
    else if (result == 1) {
      t2 [m.T1] += 3;
    }
    else {
      t2 [m.T2] += 3;
    }
    
    return t2;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t;
    T = Character.getNumericValue (in.readLine ().charAt (0));
    int G = Character.getNumericValue (in.readLine ().charAt (0));
    
    for (int i = 1; i <= 4; i++) {
      for (int x = 1; x <= 4; x++) {
        if (i != x && !list.contains (new Match (x, i))) {
          list.add (new Match (i, x));
        }
      }
    }
    
    int A, B, SA, SB;
    
    for (int g = 0; g < G; g++) {
      t = in.readLine ().split (" ");
      A = Character.getNumericValue (t [0].charAt (0));
      B = Character.getNumericValue (t [1].charAt (0));
      
      SA = Integer.parseInt (t [2]);
      SB = Integer.parseInt (t [3]);
      
      if (SA > SB) {
        scores [A] += 3;
      }
      else if (SB > SA) {
        scores [B] += 3;
      }
      else {
        scores [A]++;
        scores [B]++;
      }
      list.remove (new Match (A, B));
      list.remove (new Match (B, A));
    }
    
    System.out.println (chance (G, scores [1], scores [2], scores [3], scores [4]));
    //System.out.println (list);
  }
}

class Match {
  int T1, T2;
  
  public String toString () {
    return T1 + " vs. " + T2;
  }
  
  public boolean equals (Object o) {
    Match m = (Match) o;
    return T1 == m.T1 && T2 == m.T2;
  }
  
  public Match (int T1, int T2) {
    this.T1 = T1;
    this.T2 = T2;
  }
}
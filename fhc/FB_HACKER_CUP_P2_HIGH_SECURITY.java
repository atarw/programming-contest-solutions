import java.io.*;
import java.util.*;

public class FB_HACKER_CUP_P2_HIGH_SECURITY {
  
  static NavigableSet <Range> set = new TreeSet <Range> ();
  
  public static int overlap (Range r1, Range r2) {
    if (r1.S <= r2.E && r2.S <= r1.E && r1.top != r2.top) {
      return Math.max (r1.S, r2.S);
    }
    
    return -1;
  }
  
  public static int solve () {
    int guards = set.size ();
    Range r1, r2 = null;
    
    while (!set.isEmpty ()) {
      r1 = set.pollFirst ();
      
      for (Iterator <Range> i = set.iterator (); i.hasNext ();) {
        r2 = i.next ();
        
        if (overlap (r1, r2) != -1) {
          i.remove ();
          break;
        }
        else {
          r2 = null;
        }
      }
      
      if (r2 != null) {
        if (r1.L == 1 || r2.L == 1) {
          guards--;
        }
        else {
          set.add (r1.L > r2.L ? r1 : r2);
        }
        r2 = null;
      }
    }
    
    return guards;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    //BufferedReader in = new BufferedReader (new FileReader (new File ("high_security.txt")));
    //PrintWriter out = new PrintWriter (new FileWriter (new File ("high_security_answers.txt")));
    
    int T = Integer.parseInt (in.readLine ());
    int N;
    int start = -1;
    
    String ln;
    
    for (int t = 1; t <= T; t++) {
      N = Integer.parseInt (in.readLine ());
      start = -1;
      
      for (int i = 0; i < 2; i++) {
        ln = in.readLine ();
        start = -1;
        
        for (int n = 0; n < N; n++) {
          if (ln.charAt (n) == '.') {
            if (start == -1) {
              start = n;
            }
          }
          else {
            if (start != -1) {
              set.add (new Range (start, n - 1, i == 0));
              start = -1;
            }
          }
          
          if (n + 1 == N && start != -1) {
            set.add (new Range (start, n, i == 0));
            start = -1;
          }
        }
      }
      System.out.println ("Case #" + t + ": " + solve ());
      //out.println ("Case #" + t + ": " + solve ());
      set.clear ();
    }
    
    //in.close ();
    //out.close ();
  }
}

class Range implements Comparable <Range> {
  int S, E, L;
  boolean top;
  
  public String toString () {
    return S + " - " + E + " (" + (top ? "top" : "bottom") +  ")";
  }
  
  public int compareTo (Range r) {
    return this.hashCode () - r.hashCode ();
  }
  
  public int hashCode () {
    return S * 37 + E * 17 + (top ? 1 : 3) * 31;
  }
  
  public boolean equals (Object o) {
    Range r = ((Range) o);
    return r.S == S && r.E == E && r.top == top;
  }
  
  public Range (int S, int E, boolean top) {
    this.S = S;
    this.E = E;
    this.top = top;
    this.L = E - S + 1;
  }
}
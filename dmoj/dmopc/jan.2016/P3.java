import java.io.*;
import java.util.*;

public class P3 {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int G = Integer.parseInt (in.readLine ());
    int N, HM, HI;
    String [] t;
    List <Minion> m;
    
    for (int g = 0; g < G; g++) {
      N = Integer.parseInt (in.readLine ());
      t = in.readLine ().split (" ");
      m = new ArrayList <Minion> (N);
      
      for (int i = 0; i < t.length && N != 0; i++) {
        m.add (new Minion (Integer.parseInt (t [i])));
      }
      
      Collections.sort (m, Collections.reverseOrder ());
      
      t = in.readLine ().split (" ");
      
      HI = Integer.parseInt (t [0]);
      HM = Integer.parseInt (t [1]);
      
      for (int i = m.size () - 1; i >= 0; i--) {
        if (HM <= 0) {
          break;
        }
        else {
          m.get (i).H -= HM;
          HM -= m.get (i).A;
          
          if (m.get (i).H <= 0) {
            m.remove (i);
          }
        }
      }
      
      //System.out.println (HM + " " + m);
      
      if (HM <= 0) {
        for (int i = m.size () - 1; i >= 0; i--) {
          if (HI <= 0) {
            break;
          }
          else {
            HI -= m.get (i).A;
            m.remove (i);
          }
        }
      }
      
      //System.out.println (HI);
      
      if (HM <= 0 && HI <= 0) {
        System.out.println ("LETHAL");
      }
      else {
        System.out.println ("NOT LETHAL");
      }
    }
  }
}

class Minion implements Comparable <Minion> {
  int A, H;
  
  public String toString () {
    return "(" + A + " " + H + ")";
  }
  
  public int compareTo (Minion m) {
    return A - m.A;
  }
  
  public Minion (int A) {
    this.A = A;
    this.H = A;
  }
}
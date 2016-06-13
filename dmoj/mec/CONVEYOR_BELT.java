import java.io.*;

public class CONVEYOR_BELT {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String l1, l2;
    
    in.readLine ();
    l1 = in.readLine ();
    l2 = in.readLine ();
    
    StringBuilder l3 = new StringBuilder (l1.length () * 2);
    l3.append (l1);
    l3.append (l1);
    
    int pos;
    
    if ((pos = l3.indexOf (l2)) == -1) {
      System.out.println (pos);
    }
    else if (pos > l3.length () - l1.length () - pos) {
      System.out.println ("R" + (l3.length () - l1.length () - pos));
    }
    else {
      System.out.println ("L" + pos);
    }
  }
}
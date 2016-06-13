import java.io.*;

public class DMOPC_14_P1_BESSARION {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    boolean f = false;
    
    for (int i = 0; i < N; i++) {
      String t = in.readLine ();
      
      if (t.equals ("Leslie") && in.readLine ().equals ("Bessarion") && in.readLine ().equals ("Bayview")) {
        f = true;
        System.out.println ("Y");
        break;
      }
      else if (t.equals ("Bayview") && in.readLine ().equals ("Bessarion") && in.readLine ().equals ("Leslie")) {
        f = true;
        System.out.println ("Y");
        break;
      }
      else if (t.equals ("Bessarion")) {
        break;
      }
    }
    if (!f) {
      System.out.println ("N");
    }
  }
}
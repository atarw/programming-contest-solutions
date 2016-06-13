import java.io.*;

public class PARKING_LOT {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    int x = 0, y = 0;
    
    for (int i = 0; i < N; i++) {
      String dir = in.readLine ();
      int amt = Integer.parseInt (in.readLine ());
      
      if (dir.equals ("North")) {
        y+= amt;
      }
      else if (dir.equals ("South")) {
        y-=amt;
      }
      else if (dir.equals ("East")) {
        x+=amt;
      }
      else if (dir.equals ("West")) {
        x-=amt;
      }
    }
    System.out.println (x + " " + y);
  }
}
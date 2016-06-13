import java.io.*;

public class ECOO_14_DO_YOU_WANT_TO_BUILD_A_SNOWMAN {
  
  public static void draw (int N) {
    System.out.println ("          |");
    System.out.println ("       \\  |  /");
    System.out.println ("        \\ | /");
    System.out.println ("         \\|/");
    
    System.out.println ("       XXXXXXX");
    System.out.println ("      X       X");
    System.out.println ("     X  O   O  X");
    System.out.println ("    X     V     X");
    System.out.println ("W   X  X     X  X");
    System.out.println (" \\   X  XXXXX  X");
    System.out.println ("  \\   X       X");
    System.out.println ("   \\   XXXXXXX");
    System.out.println ("    \\ X       X---");
    System.out.println ("     X    O    X  \\");
    System.out.println ("    X           X  \\");
    System.out.println ("     XXXXXXXXXXX    \\");
    
    for (int n = 2; n <= N; n++) {
      
    }
    
    System.out.println ("       OOOO OOOO");
    System.out.println ("       OOOO OOOO");
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N;
    
    for (int i = 0; i < 5; i++) {
      N = Integer.parseInt (in.readLine ());
      draw (N);
    }
  }
}
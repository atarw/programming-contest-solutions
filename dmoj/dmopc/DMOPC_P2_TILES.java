import java.io.*;

public class DMOPC_P2_TILES {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] tokens = (in.readLine ()).split (" ");

    int X = Integer.parseInt (tokens [0]), Y = Integer.parseInt (tokens [1]), T = Integer.parseInt (in.readLine ());
    
    System.out.println ((X / T) * (Y / T));
  }
}
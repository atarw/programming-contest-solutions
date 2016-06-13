import java.io.*;

public class DMPG_15_S1_COLLECTING_DUST {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int C = Integer.parseInt (in.readLine ());
    int T = 0;
    int amt [] = {4, 5, 4, 3, 6};
    
    String [] t = in.readLine ().split (" ");
    int curr [] = {Integer.parseInt (t [0]), Integer.parseInt (t [1]), Integer.parseInt (t [2]), Integer.parseInt (t [3]), Integer.parseInt (t [4])};
    
    for (int i = 0; i < curr.length; i++) {
      T += curr [i] / amt [i] + (curr [i] % amt [i] != 0 ? 1 : 0);
    }
    
    int ans = C - T;
    System.out.println (ans <= 0 ? 0 : ans);
  }
}
import java.io.*;

public class CODEFORCES_677_A {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), H = Integer.parseInt (t [1]);
    int count = 0;
    
    t = in.readLine ().split (" ");
    
    for (int n = 0; n < N; n++) {
      if (Integer.parseInt (t [n]) > H) {
        count++;
      }
    }
    
    System.out.print (count * 2 + (N - count));
  }
}
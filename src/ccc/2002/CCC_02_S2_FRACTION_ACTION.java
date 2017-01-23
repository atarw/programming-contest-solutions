import java.io.*;

public class CCC_02_S2_FRACTION_ACTION {
  
  public static String fraction (int N, int D) {
    if (N % D == 0) {
      return "" + N / D;
    }
    
    int M = N / D;
    int gcd = gcd (N, D);
    int newN = (N - M * D) / gcd;
    int newD = D / gcd;
    
    if (M == 0) {
      return newN + "/" + newD;
    }
    else {
      return M + " " + newN + "/" + newD;
    }
  }
  
  public static int gcd (int N, int D) {
    if (D == 0) {
      return N;
    }
    return gcd (D, N % D);
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    
    System.out.println (fraction (Integer.parseInt (in.readLine ()), Integer.parseInt (in.readLine ())));
  }
}
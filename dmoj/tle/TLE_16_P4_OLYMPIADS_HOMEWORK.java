import java.io.*;

public class TLE_16_P4_OLYMPIADS_HOMEWORK {
  
  public static final int mod = 1000000013;
  
  public static long pow (int b, long x) {
    long res = 1;
    long val = b;
    
    while (x > 0) {
      if ((x & 1) == 1) {
        res = res * val;
        res = res % mod;
      }
      
      val = val * val;
      val = val % mod;
      x = x / 2;
    }
    
    return res % mod;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    long N = Long.parseLong (in.readLine ());
    
    if (N <= 3) {
      System.out.print (1);
      return;
    }
    
    long first = pow (2, N - 2), second = 0L;
    int m = (int) (N % 8);
    
    //System.out.println (first);
    
    //cos 45 * N
    //1 = rt2 / 2
    //2 = 0
    //3 = -rt2 / 2
    //4 = -1
    //5 = -rt2 / 2
    //6 = 0
    //7 = rt2 / 2
    //8 = 1
    //9 = rt2 / 2
    //10 = 0
    
    if (m == 0) {//cos (45 * N) = 1
      second = pow (2, N / 2 - 1);
    }
    else if (m == 4) {//cos (45 * N) = -1
      second = -pow (2, N / 2 - 1);
    }
    else if (m == 3 || m == 5) {//cos (45 * N) = -rt2 / 2
      second = -pow (2, (N - 1) / 2 - 1);
    }
    else if (m == 1 || m == 7) {//cos (45 * N) = rt2 / 2
      second = pow (2, (N - 1) / 2 - 1);
    }
    //else cos (45 * N) = 0
    
    System.out.print ((first + second + mod) % mod);
  }
}
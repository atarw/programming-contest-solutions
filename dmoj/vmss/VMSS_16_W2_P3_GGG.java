import java.io.*;

public class VMSS_16_W2_P3_GGG {
  
  static int [][] cache;
  
  public static int lcs (int [] arr1, int [] arr2)  {
    for (int i = 1; i <= arr1.length; i++) {
      cache [0] = cache [1];
      
      for (int j = 1; j <= arr2.length; j++) {
        if (arr1 [i - 1] == arr2 [j - 1]) {
          cache [1][j]++;// = cache [0][j - 1] + 1;
        }
        else {
          cache [1][j] = Math.max (cache [0][j], cache [1][j - 1]);
        }
      }
    }
    
    return cache [1][arr2.length];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t;
    
    int N = Integer.parseInt (in.readLine ());
    t = in.readLine ().split (" ");
    
    int [] arr1 = new int [N];
    
    for (int n = 0; n < N; n++) {
      arr1 [n] = Integer.parseInt (t [n]);
    }
    
    N = Integer.parseInt (in.readLine ());
    t = in.readLine ().split (" ");
    
    int [] arr2 = new int [N];
    
    for (int n = 0; n < N; n++) {
      arr2 [n] = Integer.parseInt (t [n]);
    }
    
    cache = new int [2][Math.max (arr1.length, arr2.length) + 1];
    
    System.out.println (lcs (arr1, arr2));
  }
}
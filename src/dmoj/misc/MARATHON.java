import java.io.*;

public class MARATHON {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]);
    int Q = Integer.parseInt (t [1]);
    
    PrefixSumArray arr = new PrefixSumArray (N);
    
    t = in.readLine ().split (" ");
    
    for (int i = 1; i <= t.length; i++) {
      arr.add (i, Integer.parseInt (t [i - 1]));
    }
    
    int A, B;
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      A = Integer.parseInt (t [0]);
      B = Integer.parseInt (t [1]);
      
      System.out.println (arr.sum (N) - arr.sum (A - 1, B));
    }
  }

  private static class PrefixSumArray {

    private int [] arr;

    public int sum (int from, int to) {
      return arr [to] - arr [from];
    }

    public int sum (int to) {
      return sum (0, to);
    }

    public void add (int pos, int N) {
      arr [pos] = arr [pos - 1] + N;
    }

    public PrefixSumArray (int N) {
      this.arr = new int [N + 1];
    }

    public PrefixSumArray (int [] arr) {
      this.arr = new int [arr.length + 1];

      this.arr [1] = arr [0];

      for (int i = 2; i <= arr.length; i++) {
        this.arr [i] = this.arr [i - 1] + arr [i - 1];
      }
    }
  }
}
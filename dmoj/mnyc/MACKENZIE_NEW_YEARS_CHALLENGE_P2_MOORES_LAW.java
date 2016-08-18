import java.io.*;

public class MACKENZIE_NEW_YEARS_CHALLENGE_P2_MOORES_LAW {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    double C = Double.parseDouble (in.readLine ());
    
    int D = 0, W = 0, M = 0, Y = 0;
    
    double TY = StrictMath.log (C / 2.0) / StrictMath.log (StrictMath.sqrt (2));
    TY = Double.parseDouble (String.format ("%.5f", TY * 365));
    
    Y = (int) (TY / 365);
    TY -= (int)(Y * 365);
    M = (int) (TY / 30);
    TY -= (int)(M * 30);
    W = (int) (TY / 7);
    TY -= (int)(W * 7);
    D = (int) Math.ceil (TY);
    
    if (D >= 7) {
      D -= 7;
      W++;
    }
    
    String ans = "";
    
    if (C != 2.0) {
      if (Y != 0) {
        ans += (Y + "Y ");
      }
      if (M != 0) {
        ans += (M + "M ");
      }
      if (W != 0) {
        ans += (W + "W ");
      }
      if (D != 0) {
        ans += (D + "D ");
      }
    }
    else {
      ans = ("Now!");
    }
    
    System.out.println (ans.trim ());
  }
}
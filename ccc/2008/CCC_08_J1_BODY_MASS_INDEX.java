import java.io.*;

public class CCC_08_J1_BODY_MASS_INDEX {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    double W = Double.parseDouble (in.readLine ());
    double H = Double.parseDouble (in.readLine ());
    double bmi = W / (H * H);
    
    if (bmi > 25) {
      System.out.println ("Overweight");
    }
    else if (18.5 <= bmi && bmi <= 25) {
      System.out.println ("Normal weight");
    }
    else {
      System.out.println ("Underweight");
    }
  }
}
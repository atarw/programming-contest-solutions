import java.io.*;
import java.text.*;
import java.math.*;

public class DMOPC_16_P2_TILT {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    double tilt = 0;
    
    for (int n = 0; n < N; n++) {
      tilt += Double.parseDouble (in.readLine ());
      tilt %= 360;
    }
    
    DecimalFormat form = new DecimalFormat ("0.00000");
    form.setRoundingMode (RoundingMode.DOWN);
    
    System.out.println (form.format (tilt % 360));
  }
}
import java.io.*;
import java.util.*;

public class CCC_07_TEXT_MESSAGE {
  
  public static String conv (String in) {
    if (in.equals ("CU")) {
      return "see you";
    }
    else if (in.equals (":-)")) {
      return "I'm happy";
    }
    else if (in.equals (":-(")) {
      return "I'm unhappy";
    }
    else if (in.equals (":-)")) {
      return "I'm happy";
    }
    else if (in.equals (";-)")) {
      return "wink";
    }
    else if (in.equals (":-)")) {
      return "I'm happy";
    }
    else if (in.equals (":-P")) {
      return "stick out my tongue";
    }
    else if (in.equals ("(~.~)")) {
      return "sleepy";
    }
    else if (in.equals ("TA")) {
      return "totally awesome";
    }
    else if (in.equals ("CCC")) {
      return "Canadian Computing Competition";
    }
    else if (in.equals ("CUZ")) {
      return "because";
    }
    else if (in.equals ("TY")) {
      return "thank-you";
    }
    else if (in.equals ("YW")) {
      return "you're welcome";
    }
    return in;
  } 
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String txt;
    
    while (!(txt = in.readLine ()).equals ("TTYL")) {
      System.out.println (conv (txt));
    }
    System.out.println ("talk to you later");
  }
}
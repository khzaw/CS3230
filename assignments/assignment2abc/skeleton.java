import java.io.*;
import java.util.*;

class skeleton {

  public static void main(String[] args) {
    IntegerScanner scanner = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }
  

  private static void woohoo() {
  }
}

class IntegerScanner {
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }

  public int nextInt() {
    int result = 0;
    try {
      int cur = bis.read();
      if(cur == -1) return -1;

      while(cur < 48 || cur > 57) {
        cur = bis.read();
      }

      while(cur >= 48 && cur <= 57) {
        result = result * 10 + (cur - 48);
        cur = bis.read();
      }

      return result;
    } catch(IOException ioe) {
      return -1;
    }
  }
}

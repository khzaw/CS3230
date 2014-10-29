import java.io.*;
import java.util.*;

class skeleton {

  public static void main(String[] args) {
    IntegerScanner scanner = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int T, C, N, K;

    T = scanner.nextInt();         // number of test cases
    for(int t = 0; t < T; t++) {
        C = scanner.nextInt();     // number of clauses
        N = scanner.nextInt();     // number of variables
        for(int c = 0; c < C; c++) {
          // the first integer K describes the number of literals in that clause
          K = scanner.nextInt();
          // the next K integers describe the literals.
          int literals[] = new int[K];
          for(int k = 0; k < K; k++) {
            literals[k] = scanner.nextInt();
          }

          convert(literals);

          
          // i = literal x(i) if i > 0 or literal -x(-i) if i < 0;
          // e.g., (x1 v x3 v -x4) will be = 3 1 3 -4;
        }
    }
  }
  

  private static void convert(int[] literals) {
    System.out.println("converting");
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

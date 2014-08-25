import java.io.*;
import java.util.*;

/*
 * f(1) = X
 * f(k) = f(k-1) + Y for all k > 1
 * For each cases, you have to output the value of f(1) + f(2) + f(3) + ... + f(N) in a separate line.
 *
 * Constraint
 * For every subtask: 1 <= T <= 100 000: Time Limit: 1s
 * Subtask A: 1 <= N <= 100, 1 <= X,Y <= 100
 * Subtask B: 1 <= N <= 100, 1 <= X,Y <= 1 000 000
 * Subtask C: 1 <= N <= 5000, 1 <= X,Y <= 50
 * Subtask D: 1 <= N <= 100 000, 1 <= X,Y <= 1 000 000
 */
 
class ArithmeticSequence {
    public static void main(String[] args) {
  IntegerScanner sc = new IntegerScanner(System.in);
  PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  
  long T, N, X, Y;
  T = sc.nextInt();

  for(int i = 1; i <= T; ++i) {
      N = sc.nextLong();
      X = sc.nextLong();
      Y = sc.nextLong();
      pr.println("" + cumulativeF(N, X, Y));
  }

  pr.close();
    }

    private static long recursiveF(long n, long x, long y) {
  if(n == 1) return x;
  else {
      return f(n - 1, x, y) + y;
  }
    }

    private static long f(long n, long x, long y) {
  return x + (n-1) *  y;
    }

    private static long cumulativeF(long n, long x, long y) {
  long sumTillN = (n * (n+1)/2) - n;
  return (n*x) + (sumTillN * y);
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
      if(cur == -1)
    return -1;

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

    public long nextLong() {
  long result = 0;
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

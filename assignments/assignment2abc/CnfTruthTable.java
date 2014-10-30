/**
 *======================================================================
 * Course:    CS3230 Design and Analysis of Algorithms
 *            Fall 2014, School of Computing, NUS
 *
 * Student:   Kaung Htet
 * Tutorial:  G2
 * Program:   CnfTruthTable
 * Language:  Java
 * Purpose:   Given a CNF-formula as input, output a circuit with AND,
 *            OR, NOT gates so that the truth table of the circuit and
 *            the formula are the same.
 *
 *========================================================================
 */
import java.io.*;
import java.util.*;

class CnfTruthTable {

  public static ArrayList<ArrayList<Integer>> clauses;
  public static int T, C, N, K;
  public static boolean literals[];

  public static void main(String[] args) {
    IntegerScanner scanner = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    T = scanner.nextInt();    // testcases
    for(int t = 0; t < T; t++) {
        C = scanner.nextInt();    // number of clauses
        clauses = new ArrayList<ArrayList<Integer>>(C);

        N = scanner.nextInt();    // variables

        literals = new boolean[N];
        
        for(int c = 0; c < C; c++) {
          K = scanner.nextInt();
          ArrayList<Integer> clause = new ArrayList<Integer>(K);
          for(int k = 0; k < K; k++) {
            clause.add(scanner.nextInt());
          }
          clauses.add(clause);
        }
        truthTable(pr);
    }
    pr.close();
  }
  public static void truthTable(PrintWriter pr) {
    for(int i = 0; i < (1 << N); i++) {
        
        boolean result = true;

        for(int j = N - 1; j >= 0; j --) {
          literals[j] = (((i/(int) Math.pow(2, j))%2) == 1) ? true : false;
        }
        for(ArrayList<Integer> cl : clauses) {
          result = result && bitCalculate(cl);
        }
        int x = (result) ? 1 : 0;
        pr.println(x);
    }
  }

  public static boolean bitCalculate(ArrayList<Integer> cl) {
    
    boolean result = false;
    for(int i = 0; i < cl.size(); i++) {
      if(cl.get(i) < 0) {
        result = result || !literals[N-(-cl.get(i))];
      } else {
        result = result || literals[N-(cl.get(i))];
      }
    }
    return result;
  }

}

class IntegerScanner {
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }

  public int nextInt() {
    int result = 0;
    boolean negative = false;

    try {
      int cur = bis.read();
      if(cur == -1) return -1;

      while(cur < 48 || cur > 57) {
        if(cur == 45) {
          cur = bis.read();
          if(cur >= 48 && cur <= 57) {
            negative = true;
            break;
          }
        }
        cur = bis.read();
      }

      while(cur >= 48 && cur <= 57) {
        result = result * 10 + (cur - 48);
        cur = bis.read();
      }

      if (negative) {
        result = -result;
      }

      return result;
    } catch(IOException ioe) {
      return -1;
    }
  }
}

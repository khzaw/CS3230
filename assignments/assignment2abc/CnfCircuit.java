/**
 *======================================================================
 * Course:    CS3230 Design and Analysis of Algorithms
 *            Fall 2014, School of Computing, NUS
 *
 * Student:   Kaung Htet
 * Tutorial:  G2
 * Program:   CnfCircuit
 * Language:  Java
 * Purpose:   Given a CNF-formula as input, output a circuit with AND,
 *            OR, NOT gates so that the truth table of the circuit and
 *            the formula are the same.
 *
 *========================================================================
 */
import java.io.*;
import java.util.*;

class CnfCircuit {

  public static ArrayList<ArrayList<Integer>> clauses;
  public static ArrayList<String> gates;
  public static ArrayList<Integer> or;
  public static ArrayList<Integer> and;
  public static int T, C, N, K;

  public static int totalNumberOfStages = 0;

  public static void main(String[] args) {
    IntegerScanner scanner = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    T = scanner.nextInt();         // number of test cases
    for(int t = 0; t < T; t++) {
        C = scanner.nextInt();      // number of clauses
        clauses = new ArrayList<ArrayList<Integer>> (C);

        N = scanner.nextInt();     // number of variables
        totalNumberOfStages = N;
        int literals[] = new int[N];

        for(int c = 0; c < C; c++) {
          K = scanner.nextInt();    // number of literals per clause
          ArrayList<Integer> clause = new ArrayList<Integer>(K);

          for(int k = 0; k < K; k++) {
            clause.add(scanner.nextInt());
          }
          clauses.add(clause);
          // i = literal x(i) if i > 0 or literal -x(-i) if i < 0;
          // e.g., (x1 v x3 v -x4) will be = 3 1 3 -4;
        }
        gates = new ArrayList<String>();
        or = new ArrayList<Integer>();
        and = new ArrayList<Integer>();
        convertToCircuit();
        printAnswer(pr);
    }
    pr.close();
  }
  

  private static void convertToCircuit() {
    for(ArrayList<Integer> cl : clauses) {
      for(int i = 0; i < cl.size(); i++) {
          int variable = cl.get(i);

          if(variable < 0) {
            gates.add("NOT " + (-variable));
            totalNumberOfStages++;
            variable = totalNumberOfStages;
          }

          if(or.isEmpty()) {
            or.add(variable);
          } else {
            totalNumberOfStages++;
            gates.add("OR " + or.remove(0) + " " + variable);
            or.add(totalNumberOfStages);
          }
      }

      if(and.isEmpty()) {
        and.add(or.remove(0));
      } else {
        totalNumberOfStages++;
        gates.add("AND " + and.remove(0) + " " + or.remove(0));
        and.add(totalNumberOfStages);
      }
    }
  }

  private static void printAnswer(PrintWriter pr) {
    pr.println(totalNumberOfStages - N);
    for(String gate : gates) {
      pr.println(gate);
    }
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

/**
 *======================================================================
 * Course:    CS3230 Design and Analysis of Algorithms
 *            Fall 2014, School of Computing, NUS
 *
 * Student:   Kaung Htet
 * Tutorial:  G2
 * Program:   CnfTruthTableWithCircuit
 * Language:  Java
 * Purpose:   Given a CNF-formula as input, output a circuit with AND,
 *            OR, NOT gates so that the truth table of the circuit and
 *            the formula are the same.
 *
 *========================================================================
 */
import java.io.*;
import java.util.*;

class CnfTruthTableWithCircuit {
  public static int T, M, N;
  public static Gate[] gates;

  public static void main(String[] args) {
    CNFScanner scanner = new CNFScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    T = scanner.nextInt();
    for(int t = 0; t < T; t++) {
      M = scanner.nextInt();    // gates
      gates = new Gate[M];
      N = scanner.nextInt();    // variables
      for(int m = 0; m < M; m++) {
        gates[m] = scanner.nextGate();
      }
    }

    printTruthTable(pr);
    pr.close();
  }

  public static void printTruthTable(PrintWriter pr) {
    for(int i = 0; i < (1 << N) ; i++) {
        // int x = (bitCalculate()) ? 1 : 0;
        // pr.println(x);
        System.out.println(i);
    }
  }

  public static boolean bitCalculate() {
    return false;
  }
}

class CNFScanner {
  BufferedInputStream bis;
  CNFScanner(InputStream is) {
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

  public G decideEnum() {
    G gate = null;
    String result = "";
    try {
      int cur = bis.read();
      if (cur == -1) {
        return null;
      }

      while (cur < 65 || cur > 90) {
        cur = bis.read();
      }

      while(cur >= 65 && cur <= 90) {
        result += (char) cur;
        cur = bis.read();
      }

      switch(result) {
        case "AND" : gate = G.AND;  break;
        case "OR" : gate = G.OR; break;
        case "NOT" : gate = G.NOT; break;
      }
    } catch(IOException ioe) {
      result = null;
    }
    return gate;
  }

  public Gate nextGate() {
    G g = decideEnum();
    Gate gate = (g == G.NOT) ? new Gate(g, nextInt()) : new Gate(g, nextInt(), nextInt());
    return gate;
  }
}
enum G {
  AND, OR, NOT
}

class Gate {
  G gate;
  int a;
  int b;

  Gate(G g, int a, int b) {
    this.gate = g;
    this.a = a;
    this.b =b;
  }

  Gate(G g, int a) {
    this.gate = g;
    this.a = a;
  }
}

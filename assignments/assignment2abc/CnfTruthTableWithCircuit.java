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
      N = scanner.nextInt();    // variable literals
      for(int m = 0; m < M; m++) {
        gates[m] = scanner.nextGate();
      }
      printTruthTable(pr);
    }

    pr.close();
  }

  public static void printTruthTable(PrintWriter pr) {
    for(int i = 0; i < (1 << N) ; i++) {
        int x = (bitCalculate(i)) ? 1 : 0;
        pr.println(x);
    }
  }

  public static boolean bitCalculate(int n) {
    boolean[] bits = toBitArray(n);
    // printArray(bits);
    int index = -1;
    for(int g = 0; g < gates.length; g++) {
        Gate gate = gates[g];
        index = N + g;
        if(gate.gate == G.AND) {
          bits[index] = bits[gate.a - 1] && bits[gate.b - 1];
        } else if(gate.gate == G.OR) {
          bits[index] = bits[gate.a - 1] || bits[gate.b - 1];
        } else if(gate.gate == G.NOT) {
          bits[index] = !bits[gate.a - 1];
        }
    }
    return bits[index];
  }

  private static boolean[] toBitArray(int number) {
    boolean[] bits = new boolean[M+N];
    for(int i = 0; i < N; i++) {
        bits[i] = (number & 1 << (N - 1 - i)) != 0;
    }
    return bits;
  }

  private static void printArray(boolean[] bits) {
    for(int i = 0; i < bits.length; i++) {
        int x = (bits[i]) ? 1 : 0;
        System.out.print(x);
    }
    System.out.println();
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

      if(result.equals("AND")) {
        gate = G.AND;
      } else if(result.equals("OR")) {
        gate = G.OR;
      } else if(result.equals("NOT")) {
        gate = G.NOT;
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

/**
     *======================================================================
     * Course:    CS3230 Design and Analysis of Algorithms
     *            Fall 2014, School of Computing, NUS
     *            Skeleton Code provided by Jonathan
     *
     * Student:   Kaung Htet
     * Tutorial:  G2 
     * Program:   Recruting Interns V2, Task C
     * Language:  Java
     * Purpose:   Skeleton code for Programming Assignment 1.
     *            Student MUST use the skeleton code and fill
     *            in the implementation code necessary to make
     *            it work.
     *      
     *            NOTE: The method findStableMarriage()  
     *                  MUST NOT be modified.
     *
     *========================================================================
*/

import java.io.*;
import java.util.*;

class skeleton {

  static int T, N, M;
  static int H[], branches[], interns[], count[];
  static boolean W[];
  static int branchesPref[][], internsPref[][];
  static Queue<Integer> queueBranches = new LinkedList<Integer>();

  private static void printArray(String name, int[] arr) {
    System.out.println(" ====== " + name + " =======");
    System.out.print("[");
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + ", ");
    }
    System.out.println("]");
    System.out.println();
  } 

  private static void printArray(String name, boolean[] arr) {
    System.out.println(" ====== " + name + " =======");
    System.out.print("[");
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + ", ");
    }
    System.out.println("]");
    System.out.println();
  } 

  private static void print2DArray(String name, int[][] arr) {
    System.out.println(" ====== " + name + " =======");
    System.out.println("[");
    for(int i = 0; i < arr.length; i++) {
      System.out.print(i + "\t[");
      for(int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j] + ", ");
      }
      System.out.print("]\n");
    }
    System.out.println("]");
    System.out.println();
  }

  private static void printMatching() {
    System.out.println("===== matching =====");
    System.out.print("branches: [");
    for(int i = 0; i < branches.length; i++) {
      System.out.print(branches[i] + ", ");
    }
    System.out.println("]");
    System.out.print("interns: [");
    for(int i = 0; i < interns.length; i++) {
      System.out.print(interns[i] + ", ");
    }
    System.out.println("]");
    System.out.println();

    System.out.println(queueBranches);
    printArray("women :", W);
    System.out.println();
  }

  private static void printAnswer() {
    for(int m = 0; m < branches.length; m++) {
      System.out.println((m + 1) + " " + (branches[m]+1));
    }
  }


  private static void initializeEachPersonFree()
  {
    count = new int[M];
    // Arrays.fill(branches, -1);
    // Arrays.fill(interns, -1);
  }

  //return true iff there is a man who is free and has not proposed anyone
  private static boolean someManIsFreeAndHasNotProposed()
  {
    return !queueBranches.isEmpty();
  }

  //return the index of the man who is free and has not proposed anyone
  private static int chooseThatMan()
  {
    return queueBranches.poll();
  }

  //return the index of the first woman on Man #manIndex's list whom he has not yet proposed
  private static int firstWomanOnManList(int manIndex)
  {
    return branchesPref[manIndex][count[manIndex]];
  }

  //return true iff woman #womanIndex is free
  private static boolean isFree(int womanIndex)
  {
    return W[womanIndex] == false;
  }

  //assign Man #manIndex and Woman #womanIndex to be engaged
  private static void assign(int manIndex,int womanIndex)
  {
    branches[manIndex] = womanIndex;
    interns[womanIndex] = manIndex ;
    W[womanIndex] = true;
  }

  //return true iff Woman #womanIndex prefers Man #firstManIndex to Man #secondManIndex
  private static boolean prefers(int womanIndex,int firstManIndex,int secondManIndex)
  {
    // firstWoman, freeMan, fiance(firstwoman)
    // System.out.println("fiance(firstWoman) : " + secondManIndex);
    // System.out.println("internsPref[womanIndex][firstManIndex] " + internsPref[womanIndex][firstManIndex]);
    // System.out.println("internsPref[womanIndex][secondManIndex] " + internsPref[womanIndex][secondManIndex]);
    return internsPref[womanIndex][firstManIndex] < internsPref[womanIndex][secondManIndex];
  }

  //set Man #manIndex to be free
  private static void setFree(int manIndex)
  {
    branches[manIndex] = 0;
    queueBranches.offer(manIndex);
  }

  //return the index of man who is the fiance of Woman #womanIndex
  private static int fiance(int womanIndex)
  {
    return interns[womanIndex];
  }

  //Man #manIndex get rejected by Woman #womanIndex
  private static void rejected(int manIndex,int womanIndex)
  {
    count[manIndex]++;
    queueBranches.offer(manIndex);
  }

  private static void findStableMarriage()
  {
    //Initialize each person to be free.
    
    initializeEachPersonFree(); 
    // print2DArray("branchesPref", branchesPref);
    // print2DArray("internsPref", internsPref);
    // printMatching();
    //while (some man is free and hasn't proposed to every woman)
    while (someManIsFreeAndHasNotProposed())
    {
      //Choose such a man m
      int freeMan = chooseThatMan();
      //w = 1st woman on m's list to whom m has not yet proposed
      int firstWoman = firstWomanOnManList(freeMan);

      // System.out.println("man: " + freeMan+ ", woman: " + firstWoman);
      //if (w is free)
      if (isFree(firstWoman)) 
      {
        //assign m and w to be engaged
        assign(freeMan,firstWoman);
        // System.out.println("assigned");
      }
      else if (prefers(firstWoman,freeMan,fiance(firstWoman)))
      {
        //assign m and w to be engaged, and m' to be free
        setFree(fiance(firstWoman)); assign(freeMan,firstWoman);
        // System.out.println("set Free");
      //else
      } else
      {
        //w rejects m
        rejected(freeMan,firstWoman);
        // System.out.println("rejected");
      }
      // printMatching();
    }
  }

  public static void main(String[] args)
  {
    IntegerScanner scanner;
    PrintWriter pr;

    scanner = new IntegerScanner(System.in);
    pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    T = scanner.nextInt();
    for(int i = 0; i < T; i++) {
      M = scanner.nextInt(); // no of branches
      N = scanner.nextInt(); // no of interns

      branches = new int[M];
      count = new int[M];
      interns = new int[N];
      W = new boolean[N];



      H = new int[M];
      branchesPref = new int[M][N];
      internsPref = new int[N][M];

      for(int h = 0; h < M; h++) {
        H[h] = scanner.nextInt();
      }



      for(int m = 0; m < M; m++) {
        for(int n = 0; n < N; n++) {
          int val = scanner.nextInt() - 1;
          branchesPref[m][n] = val;
        }
        queueBranches.offer(m);
      }

      for(int n = 0; n < N; n++) {
        for(int m = 0; m < M; m++) {
          int val = scanner.nextInt() - 1;
          internsPref[n][val] = m;

        }
      }

      findStableMarriage();
      printAnswer();
    }
    pr.close();
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


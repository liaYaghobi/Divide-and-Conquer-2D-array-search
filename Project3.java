/*Lia Yaghobi
 COMP 482 Project 3: 2D array search
 */

 import java.util.*;
 import java.io.*;
 
 public class Project3 {
   public static void main(String[] args) {
     Project3 s = new Project3();
     int array[][] = s.getInput();
     s.solve(array);
   }
 
   int row;                                   
   int col;
   int target;
 
   private int[][] getInput() {
     Scanner sc = null;
     try {                                                             /* Read from text file */
 
       sc = new Scanner(new File("input.txt"));
     } catch (FileNotFoundException e) {
       System.out.println("Did you forget the input file?");
       System.exit(1);
     }
 
     row = sc.nextInt();
     col = sc.nextInt();
     target = sc.nextInt();
 
     int[][] arr = new int[row][col];
     sc.nextLine();
 
     for (int i = 0; i < row; i++) {
       for (int j = 0; j < col; j++) {
         arr[i][j] = sc.nextInt();
 
       }
     }
 
     return arr;
   }
 
   boolean recursiveSearch(int[][] arr, int target, int rowStart, int rowEnd, int colStart, int colEnd) {
 
     if (rowStart > rowEnd || colStart > colEnd) {       /*row ends or col ends */
 
       return false;
     }
 
     int midRow = rowStart + (rowEnd - rowStart) / 2;       /*get middle col and middle row */
     int midCol = colStart + (colEnd - colStart) / 2;
     int midVal = arr[midRow][midCol];
 
     if (rowStart == rowEnd && colStart == colEnd && midVal != target) {   /*array is 1 element, and NOT target*/
       return false;
     }
                                                                         
     if (midVal == target) {                                                /*midVal is target  */
       System.out.print((midRow + 1) + " " + (midCol + 1));
       return true;
     }
 
     if (midVal > target) {                                                /*if midval is greater than target */
 
       return recursiveSearch(arr, target, rowStart, midRow - 1, colStart, midCol - 1) ||
         recursiveSearch(arr, target, rowStart, midRow - 1, midCol, colEnd) ||
         recursiveSearch(arr, target, midRow, rowEnd, colStart, midCol - 1);
 
     }                                                                     /*else midval is smaller */      
     return recursiveSearch(arr, target, rowStart, midRow, midCol + 1, colEnd) ||  
       recursiveSearch(arr, target, midRow + 1, rowEnd, colStart, midCol) ||
       recursiveSearch(arr, target, midRow + 1, rowEnd, midCol + 1, colEnd);
 
   }
 
   private void solve(int[][] arr) {
 
     if (!recursiveSearch(arr, target, 0, row - 1, 0, col - 1)) {  
       System.out.print("NOT FOUND");               /*if search returns false, print NOT FOUND */
     }
   }
 
 }

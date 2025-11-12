package nov11.array;

import java.util.Scanner;

public class Create2dArray {
    public static int[][] create(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Rows: ");
        int rows = Integer.parseInt(scanner.next());

        System.out.print("Enter Cols: ");
        int cols = Integer.parseInt(scanner.next());

        int[][] output = new int[rows][cols];
        for(int i = 0; i<rows; i++){
            int[] temp = new int[cols];
            System.out.println("Enter Rows: ");
            for(int j = 0; j<cols; j++){
                temp[j] = Integer.parseInt(scanner.next());
            }
            output[i] = temp;
        }

        return output;
    }

    public static void print(int[][] matrix){
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }
    }
}

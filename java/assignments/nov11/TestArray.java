package nov11;

import nov11.array.ColRowSum;
import nov11.array.Create2dArray;
import nov11.array.MatrixAdd;
import nov11.array.MaxMin;
import nov11.array.Sum;
import nov11.array.Transpose;

public class TestArray {
    public static void main(String[] args) {
        //Create and print
        int[][] create2dArray = Create2dArray.create();
        System.out.println("\nPrint Array: ");
        Create2dArray.print(create2dArray);

        //Col sum, Row sum, total Sum
        System.out.println("\nTotal Sum: ");
        System.out.print(Sum.sum2dArray(create2dArray));

        System.out.println("\n\nRow Sum: ");
        int[] rowSum = ColRowSum.rowSum(create2dArray);
        for(int i = 0; i<rowSum.length;i++){System.out.print(rowSum[i]+" ");}

        System.out.println("\n\nCol Sum: ");
        int[] colSum = ColRowSum.colSum(create2dArray);
        for(int i = 0; i<rowSum.length;i++){System.out.print(colSum[i]+" ");}

        //Min Max
        System.out.println("\n\nMax: "+MaxMin.max(create2dArray));
        System.out.println("Min: "+MaxMin.min(create2dArray));

        //Matrix add
        System.out.println("\nMaxrix add: ");
        Create2dArray.print(MatrixAdd.add(create2dArray, create2dArray));

        //Matrix Transpose
        System.out.println("\nMaxrix Transpose: ");
        Create2dArray.print(Transpose.transpose(create2dArray));
    }
}

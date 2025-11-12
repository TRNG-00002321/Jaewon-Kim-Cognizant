package nov11.array;

public class ColRowSum {
    public static int[] colSum(int[][] matrix){
        int[] colSums = new int[matrix[0].length];
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                colSums[j]+=matrix[i][j];
            }
        }

        return colSums;
    }

    public static int[] rowSum(int[][] matrix){
        int[] rowSums = new int[matrix.length];
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){  
                rowSums[i]+=matrix[i][j];
            }
        }
        return rowSums;
    }
}

package nov11.array;

public class MaxMin {
    public static int min(int[][] matrix){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                if(min>matrix[i][j]) min = matrix[i][j];
            }
        }
        return min;
    }

    public static int max(int[][] matrix){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                if(max<matrix[i][j]) max = matrix[i][j];
            }
        }
        return max;
    }
}

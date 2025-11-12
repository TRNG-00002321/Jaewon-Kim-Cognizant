package nov11.array;

public class Sum {
    public static int sum2dArray(int[][] matrix){
        int sum = 0;
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                sum+=matrix[i][j];
            }
        }
        return sum;
    }
}

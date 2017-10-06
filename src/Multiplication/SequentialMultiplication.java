package Multiplication;

public class SequentialMultiplication {
	public static int[][] multiplyMatrix (int[][] matrixA, int[][] matrixB, int[][] matrixC){
		int[][] result = new int[matrixA.length][matrixB.length];
		
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixB[i].length; j++) {
				int sum = 0;
				for (int k = 0; k < matrixB.length; k++) {
					sum += matrixA[i][k]*matrixB[k][j];
				}
				result[i][j] = sum;
			}
		}
		return result;
	}
	
	public static double getMean(long[] times) {
		double total = 0;
		for (long time : times) {
			total += time;
		}
		return total/times.length;
	}
	
	public static double getStandardDeviation(long[] times) {
		double mean = getMean(times);
		double result = 0;
		for (long time : times) {
			result += (time - mean)*(time - mean);
		}
		return Math.sqrt(result/(times.length-1));
	}

}

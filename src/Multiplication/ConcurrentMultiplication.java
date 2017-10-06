package Multiplication;

public class ConcurrentMultiplication implements Runnable {
	private int[][] matrixA;
	private int[][] matrixB;
	private int[][] matrixC;
	private int init;
	private int end;

	public ConcurrentMultiplication(int[][] matrixA, int[][] matrixB, int[][] matrixC, int init, int end) {
		super();
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixC = matrixC;
		this.init = init;
		this.end = end;
	}

	public void multiplyMatrixOperation() {
		for (int i = this.init; i < this.end; i++) {
			for (int j = 0; j < this.matrixB[i].length; j++) {
				int sum = 0;
				for (int k = 0; k < matrixC.length; k++) {
					sum += this.matrixA[i][k] * this.matrixB[k][j];
				}
				matrixC[i][j] = sum;
			}
		}
	}

	public void run() {
		this.multiplyMatrixOperation();
	}

}
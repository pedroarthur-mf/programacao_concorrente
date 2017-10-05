package Multiplication;
public class ConcurrentMultiplication implements Runnable {
	private int[][] matrixA;
	private int[][] matrixB;
	private int init;
	private int end;
	private String name;
	
	public ConcurrentMultiplication(String name, int[][] matrixA, int[][] matrixB, int init, int end) {
		super();
		this.name = name;
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.init = init;
		this.end = end;
	}

	public int[][] multiplyMatrixOperation (int[][] matrixA, int[][] matrixB, int init, int end){
		int [][] result = new int [matrixA.length][matrixB.length];
		
		for (int i = init; i < end; i++) {
			for (int j = 0; j < matrixB[i].length; j++) {
				int sum = 0;
				for (int k = 0; k < result.length; k++) {
					sum += matrixA[i][k]*matrixB[k][j];
				}
				result[i][j] = sum;
			}
		}
		System.out.println("Terminando operação em " +  name);
		return result;
	}
	
	@Override
	public void run() {
		System.out.println("Iniciando operação na Thread " + name);
		this.multiplyMatrixOperation(matrixA, matrixB, init, end);
	}
}

package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Multiplication.ConcurrentMultiplication;
import Multiplication.SequentialMultiplication;

public class MainConcurrent {
	public static int[][] readFile(String path) throws FileNotFoundException {

		InputStream file = new FileInputStream(path);
		InputStreamReader fileReader = new InputStreamReader(file);
		BufferedReader input = new BufferedReader(fileReader);
		String size;
		try {
			size = input.readLine();
			String[] sizes = size.split(" ");
			int[][] matrix = new int[Integer.parseInt(sizes[0])][Integer.parseInt(sizes[1])];

			String line;
			String[] numstr;
			for (int i = 0; i < Integer.parseInt(sizes[0]); i++) {
				line = input.readLine();
				numstr = line.split(" ");
				for (int j = 0; j < numstr.length; j++) {
					matrix[i][j] = Integer.parseInt(numstr[j]);
				}

			}
			file.close();
			fileReader.close();
			input.close();
			return matrix;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		System.out.println("Iniciando...");
		String pathA = "matrix/A" + args[0] + "x" + args[0] + ".txt";
		String pathB = "matrix/B" + args[0] + "x" + args[0] + ".txt";
		
		int threadsNumber = Integer.parseInt(args[1]);
		List<Thread> threads = new ArrayList<Thread>();

		System.out.println(pathA);
		System.out.println(pathB);

		int[][] matrixA = readFile(pathA);
		int[][] matrixB = readFile(pathB);
		int[][] matrixC = new int[matrixA.length][matrixB.length];

		Runnable r;
		Thread t;

		long max = 0;
		long min = 1000000000;
		long[] times = new long[20];

		int k = (matrixA.length / threadsNumber);
		int init = 0, end = 0;

		for (int i = 0; i < 20; i++) {
			long start = System.currentTimeMillis();

			for (int j = 0; j < threadsNumber; j++) {
				init = j * k;
				end = init + k;

				if (j == threadsNumber - 1) {
					end = matrixA.length;
				}

				r = new ConcurrentMultiplication(matrixA, matrixB, matrixC, init, end);
				t = new Thread(r);
				threads.add(t);
			}

			for (int j = 0; j < threadsNumber; j++) {
				threads.get(j).start();
			}

			for (int j = 0; j < threadsNumber; j++) {
				threads.get(j).join();
			}

			long total = System.currentTimeMillis() - start;
			
			threads.clear();
			
			times[i] = total;

			if (total > max) {
				max = total;
			}
			if (total < min) {
				min = total;
			}

		}
		System.out.println("\nConcorrent!!\n");
		System.out.println("Média: " + SequentialMultiplication.getMean(times));
		System.out.println("Desvio padão: " + SequentialMultiplication.getStandardDeviation(times));
		System.out.println("Max:" + max);
		System.out.println("Min:" + min);
	}
}

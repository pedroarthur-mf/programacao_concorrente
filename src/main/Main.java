package main;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Multiplication.SequencialMultiplication;

public class Main {
	public static int[][] readFile(String path) throws FileNotFoundException {
			
		InputStream file = new FileInputStream(path);
        InputStreamReader fileReader = new InputStreamReader(file);
		BufferedReader input = new BufferedReader(fileReader);
		String size;
		try {
			size = input.readLine();
			String[] sizes = size.split(" ");
			int[][] matrix =  new int[Integer.parseInt(sizes[0])][Integer.parseInt(sizes[1])];
			
			String line;
			String[] numstr;
			for (int i = 0; i< Integer.parseInt(sizes[0]); i++) {
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
			e1.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String pathA = "matrix/A" + args[0] + "x" + args[0] + ".txt";
		String pathB = "matrix/B" + args[0] + "x" + args[0] + ".txt";
		System.out.println(pathA);
		System.out.println(pathB);
		
		int [][] matrixA = readFile(pathA);
		int [][] matrixB = readFile(pathB);
		long max = 0;
		long min = 1000000000;
		long[] times = new long[20];
		
		for (int i = 0; i < 20; i++) {
			long start = System.currentTimeMillis();
				SequencialMultiplication.multiplyMatrix(matrixA, matrixB);
			long total = System.currentTimeMillis() - start;
			times[i] = total; 
			if (total > max) {
				max = total;
			}
			if(total < min) {
				min = total;
			}
		}
		
		System.out.println("Media: " + SequencialMultiplication.getMean(times));
		System.out.println("Desvio padão: " + SequencialMultiplication.getVariance(times));
		System.out.println("Min:" + min);
		System.out.println("Max:" + max);
	}
}

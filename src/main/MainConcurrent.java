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

public class MainConcurrent {
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
		
		int [][] matrixA = readFile(pathA);
		int [][] matrixB = readFile(pathB);
		
		int k = (matrixA.length / threadsNumber);
		int init = 0, end = 0;
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < threadsNumber; i++) {
			init = i*k;
			end = init + k;
			
			if(i == threadsNumber - 1) {
				end = matrixA.length;
			}
			
			Runnable r = new ConcurrentMultiplication("" + i, matrixA, matrixB, init, end);
			Thread t = new Thread(r);
			threads.add(t);
		}
		
		for(int i = 0; i < threadsNumber; i++) {
			threads.get(i).start();
		}
		
		for(int i = 0; i < threadsNumber; i++) {
			threads.get(i).join();
		}
		
		long total = System.currentTimeMillis() - start;
		System.out.println(total);
	}
}

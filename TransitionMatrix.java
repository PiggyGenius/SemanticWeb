/* [ENSIMAG - ISI3A] */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ioannis Partalas
 *
 *
 * This program reads a file in the following form and the calculates the 
 * transition matrix
 *
 * N % number of web page
 * 0 4 5 6 7 1 % links of web page 0 to other web pages
 * 1 3 4
 * 2 8 5
 * ...
 * N-1 0 43 12
 *
 */
public class TransitionMatrix {
	double pmatrix[][]; // matrix to store the probabilities
	double lambda=0.85; 
	int N; // number of pages

	/**
	 * @param filename The file where the graph is stored
	 * @param lam the lambda value
	*/
	public TransitionMatrix(String filename,double lam){
		lambda = lam;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = br.readLine(); // read first line with the number of pages
			N = Integer.parseInt(line);
			pmatrix = new double[N][N];
			int adjacence[][] = new int[N][N]; // keeps the adjacency  table: number of links from page i to page j
			int outdegree[] = new int[N]; // keeps the number of links that go out from page i
			while((line=br.readLine())!=null){ // read the file line by line
				String links[] = line.split("\\s+");
				int page_i = Integer.parseInt(links[0]); // get first the page i
				outdegree[page_i] = links.length - 1;
				for(int j = 1; j < links.length; j++){
					adjacence[page_i][Integer.parseInt(links[j])] += 1;
				}
			}

			double count_ratio = ((double) 1) / N;
			for (int i = 0; i < N; i++){
				if(outdegree[i] == 0){
					for(int j = 0; j < N; j++)
						pmatrix[i][j] = count_ratio;
				}
				else {
					for (int j = 0; j < N; j++) {
						pmatrix[i][j] = (lambda * adjacence[i][j] / outdegree[i]) + ((1 - lambda) * count_ratio);
					}
				}
			}
		} catch (IOException ex) {
		}
	}

	/** This method computes the page rank vector using power method */
	public double[] powerPageRank(double epsilon){
		double count_ratio = ((double) 1) / N;
		double page_vector_1[] = new double[N];
		double page_vector_2[] = new double[N];
		for(int i = 0; i < N; i++){
			page_vector_1[i] = count_ratio;
		}
		while(true){
			page_vector_2 = Algebra.matrixDotVector(pmatrix, page_vector_1);
			if(Algebra.getVectorRankNorm(page_vector_1, page_vector_2) <= epsilon){
				break;
			}
			page_vector_1 = page_vector_2;
		}
		return page_vector_1;
	}

	/** This method prints the transition matrix */ 
	public static void main(String args[]){
		// arguments: the file graph and the lambda value and epsilon
		double[] page_rank;
		TransitionMatrix tr = new TransitionMatrix(args[0],Double.parseDouble(args[1]));
		page_rank = tr.powerPageRank(Double.parseDouble(args[2]));
		Algebra.printVector(page_rank);
		Algebra.printSortVector(page_rank);
		//Algebra.printMatrix(tr.pmatrix);
	}
}

public final class Algebra {

	private Algebra(){
	}

	public static double getVectorRankNorm(double[] vector_1, double[] vector_2){
		double sum_square = 0.0;
		for(int i = 0; i < vector_1.length; i++){
			sum_square += Math.pow((vector_2[i] - vector_1[i]), 2);
		}
		return Math.sqrt(sum_square);
	}

	public static double[] matrixDotVector(double[][] matrix, double[] vector){
		double dot_vector[] = new double[vector.length];
		double sum;
		for(int i = 0; i < matrix.length; i++){
			sum = 0.0;
			for(int j = 0; j < matrix[i].length; j++){
				sum += matrix[j][i] * vector[j];
			}
			dot_vector[i] = sum;
		}
		return dot_vector;
	}

	public static void printVector(double[] vector){
		for(int i = 0; i < vector.length; i++){
			System.out.print(vector[i] + " ");
		}
		System.out.println();
	}

	public static void printVector(int[] vector){
		for(int i = 0; i < vector.length; i++){
			System.out.print(vector[i] + " ");
		}
		System.out.println();
	}

	public static void printMatrix(double[][] matrix){
		for (int i = 0; i < matrix.length; i++)  {
			for (int j = 0; j < matrix[i].length; j++)
				System.out.format("%.4f ",matrix[i][j]);
			System.out.println();
		}
	}

	public static int indexOf(int[] array, int value){
		for(int i = 0; i < array.length; i++)
			if(array[i] == value) return i;
		return -1;
	}

	public static void printSortVector(double[] vector){
		int[] index = new int[vector.length];
		for(int i = 0; i < vector.length; i++)
			index[i] = i;
		Sort.quicksort(vector, index);
		for(int i = 0; i < vector.length; i++){
			System.out.println("Page " + i + " --> " + indexOf(index, i));
		}
	}
}

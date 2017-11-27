public final class Sort {

	private Sort(){
	}

	public static void quicksort(double[] values, int[] index){
		quicksort(values, index, 0, index.length - 1);
	}

	public static void quicksort(double[] values, int[] index, int left, int right){
		if(right <= left)
			return;
		int i = partition(values, index, left, right);
		quicksort(values, index, left, i - 1);
		quicksort(values, index, i + 1, right);
	}

	private static int partition(double[] values, int[] index, int left, int right){
		int i = left - 1;
		int j = right;
		while(true){
			while(less(values[++i], values[right]))
				;
			while(less(values[right], values[--j]))
				if(j == left) break;
			if(i >= j) break;
			exchange(values, index, i, j);
		}
		exchange(values, index, i, right);
		return i;
	}

	private static void exchange(double[] values, int[] index, int i, int j){
		double swap = values[i];
		values[i] = values[j];
		values[j] = swap;
		int b = index[i];
		index[i] = index[j];
		index[j] = b;
	}

	private static boolean less(double x, double y){
		return (x < y);
	}
}

public class MergeSort extends SortAlgorithm {
	
	public MergeSort(int input_array[]) {
		super(input_array);
	}
	
	private void merge(int left, int middle, int right) {
        // fill this method

        //Calculate the sizes of the two sub arrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Create two temporary arrays to store the two sub arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        //Copy the elements of the main array into the two sub arrays
        for(int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for(int j = 0; j < n2; j++) {
            R[j] = arr[middle + 1 + j];
        }

        //Merge the two sub arrays back into the main array

        //Initialize the indices of the two sub arrays
        int i = 0, j = 0;

        //Initialize the index of the main array
        int k = left;

        //Merge the two sub arrays
        while(i < n1 && j < n2) {
            // Increment the comparison counter for each comparison made
            comparison_counter++;

            // Pick the smaller among the smallest unpicked element in L and R
            if(L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[], if any
        while(i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[], if any
        while(j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void sort(int left, int right) {
        // fill this method
        // Check if low is smaller than high, if not then the array is sorted
        if(left < right) {
            // Get the index of the element which is in the middle
            int middle = (left + right) / 2;
            // Sort the left side of the array
            sort(left, middle);
            // Sort the right side of the array
            sort(middle + 1, right);
            // Combine them both
            merge(left, middle, right);
        }
    }
    
    @Override
    public void sort() {
        // This method is used to sort the array using the merge sort algorithm
    	sort(0, arr.length - 1);
    }
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}

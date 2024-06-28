public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	
    private int partition(int low, int high) {
        // fill this method
        //Choose the pivot element
        int pivot = arr[high];
        //Initialize the index of the smaller element
        int i = low - 1;

        //Iterate over the array
        for(int j = low; j < high; j++) {
            // Increment the comparison counter for each comparison made
            comparison_counter++;
            //If the current element is smaller than the pivot, swap it with the element at index i
            if(arr[j] < pivot) {
                i++;
                swap(i, j);
            }
        }
        //Swap the pivot element with the element at index i + 1
        swap(i + 1, high);
        //Return the index of the pivot element
        return i + 1;
    }

    private void sort(int low, int high) {
        // fill this method
        //If the lower index is less than the higher index
        if(low < high) {
            //Partition the array
            int pi = partition(low, high);
            //Recursively sort the two sub arrays
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    @Override
    public void sort() {
        // fill this method
        //Call the sort method with the lower and higher indices of the arrayq
    	sort(0, arr.length - 1);
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}

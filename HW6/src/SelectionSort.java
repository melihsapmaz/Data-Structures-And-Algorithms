public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        // fill this method
        //Get the length of the array
        int n = arr.length;
        //Iterate over the array
        for(int i = 0; i < n - 1; i++) {
            //Find the minimum element in the unsorted part of the array
            int min_index = i;
            //Iterate over the unsorted part of the array
            for(int j = i + 1; j < n; j++) {
                // Increment the comparison counter for each comparison made
                comparison_counter++;
                //If the current element is smaller than the minimum element, update the index of the minimum element
                if(arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }
            //Swap the minimum element with the first element of the unsorted part
            swap(i, min_index);
        }
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}

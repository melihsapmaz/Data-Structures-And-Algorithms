public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
        // fill this method
        //Get the length of the array
        int n = arr.length;

        //Outer loop iterates over each element of the array
        for(int i = 0; i < n - 1; i++){

            //Inner loop for each pass, it iterates until the last unsorted element
            for(int j = 0; j < n - i - 1; j++) {

                // Increment the comparison counter for each comparison made
                comparison_counter++;

                // If the current element is greater than the next element, swap them
                if(arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}

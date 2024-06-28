public class preprocessor {
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str) {
		//Constructor
		initial_string = str;
		preprocessed_string = "";
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}

	//Capitalizes the preprocessed string
	private void capitalize() {
		//If the preprocessed string contains the character 'i', replace it with 'I'
		String temp = "";
		if(initial_string.contains("i")) {
			temp = initial_string.replace("i", "I");
		}
		//If the preprocessed string does not contain the character 'i', set the preprocessed string to the initial string
		else{
			temp = initial_string;
		}
		//Set the preprocessed string to the capitalized string
		preprocessed_string = temp.toUpperCase();
	}

	//Removes all non-alphabetic characters from the preprocessed string
	private void clean() {
		preprocessed_string = preprocessed_string.replaceAll("[^A-Z]", "");

	}

	//Getter method
	public String get_preprocessed_string() {
		return preprocessed_string;
	}
}
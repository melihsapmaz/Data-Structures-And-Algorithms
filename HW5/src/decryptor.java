import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		//Constructor
		map = _map;
		key = _key;
		cipher_text = text;
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}

	//generate the keystream
	private void generate_keystream() {
		//Create a StringBuilder object to store the keystream
		StringBuilder keystreamBuilder = new StringBuilder();
		//Iterate through the cipher text
		for (int i = 0; i < cipher_text.length(); i++) {
			//Append the key character at the current index to the keystream
			keystreamBuilder.append(key.charAt(i % key.length()));
		}
		//Convert the StringBuilder object to a string and store it in the keystream variable
		keystream = keystreamBuilder.toString();

	}

	//generate the plain text
	private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method

		//Create a StringBuilder object to store the plain text
		StringBuilder plain_textBuilder = new StringBuilder();
		//Create iterators for the cipher text and keystream
		Iterator<Character> cipherIterator = cipher_text.chars().mapToObj(c -> (char) c).iterator();
		Iterator<Character> keystreamIterator = keystream.chars().mapToObj(c -> (char) c).iterator();
		//Iterate through the cipher text and keystream
		while (cipherIterator.hasNext() && keystreamIterator.hasNext()) {
			//Get the current character from the keystream and cipher text
			char keystream_char = keystreamIterator.next();
			char cipher_char = cipherIterator.next();
			//Iterate through the map to find the corresponding plain text character
			Iterator<Character> iterator = map.get(keystream_char).keySet().iterator();
			//Iterate through the map to find the corresponding plain text character
			while (iterator.hasNext()) {
				//Get the current character from the map
				char text_char = iterator.next();
				//If the cipher character matches the value in the map, append the plain text character to the plain text and break
				if (map.get(keystream_char).get(text_char).equals(cipher_char)) {
					plain_textBuilder.append(text_char);
					break;
				}
			}
		}
		//Convert the StringBuilder object to a string and store it in the plain_text variable
		plain_text = plain_textBuilder.toString();
	}

	//Getter methods
	public String get_keystream() {
		return keystream;
	}
	
	public String get_plain_text() {
		return plain_text;
	}
}

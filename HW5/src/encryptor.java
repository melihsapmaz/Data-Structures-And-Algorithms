import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		//Constructor
		map = _map;
		key = _key;
		plain_text = text;
	}
	
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}

	//generate the keystream
	private void generate_keystream() {
		//Create a StringBuilder object to store the keystream
		StringBuilder keystreamBuilder = new StringBuilder();
		//Iterate through the plain text
		for (int i = 0; i < plain_text.length(); i++) {
			//Append the key character at the current index to the keystream
			keystreamBuilder.append(key.charAt(i % key.length()));
		}
		//Convert the StringBuilder object to a string and store it in the keystream variable
		keystream = keystreamBuilder.toString();

	}

	//generate the cipher text
	private void generate_cipher_text() {
		//Create a StringBuilder object to store the cipher text
		StringBuilder cipher_textBuilder = new StringBuilder();
		//Iterate through the plain text
		for (int i = 0; i < plain_text.length(); i++) {
			//Get the current character from the plain text and keystream
			char text_char = plain_text.charAt(i);
			char keystream_char = keystream.charAt(i);
			//Append the corresponding cipher text character to the cipher text
			cipher_textBuilder.append(map.get(text_char).get(keystream_char));
		}
		//Convert the StringBuilder object to a string and store it in the cipher_text variable
		cipher_text = cipher_textBuilder.toString();

	}

	//Getter methods
	public String get_keystream() {
		return keystream;
	}
	
	public String get_cipher_text() {
		return cipher_text;
	}
}

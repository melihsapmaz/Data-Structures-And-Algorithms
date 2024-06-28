import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	
	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}

	//Fill the "english_alphabet" set with the English alphabet
	private void fill_english_alphabet() {
		// do not edit this method
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
		    english_alphabet.add(c);
		}
	}

	//Fill the "map" variable with the Vigenere cipher table
	private void fill_map() {
		//Create an iterator for the English alphabet
		Iterator<Character> outerIterator = english_alphabet.iterator();
		//Iterate through the English alphabet
		for (int i = 0; i < english_alphabet.size(); i++) {
			//Get the current character from the English alphabet
			char outerChar = outerIterator.next();
			//Create a map to store the inner map
			Map<Character, Character> innerMap = new HashMap<>();
			//Create an iterator for the English alphabet
			Iterator<Character> innerIterator = english_alphabet.iterator();
			//Iterate through the English alphabet
			for (int j = 0; j < english_alphabet.size(); j++) {
				//Get the current character from the English alphabet
				char innerChar = innerIterator.next();
				//Add the character to the inner map
				innerMap.put(innerChar, (char)('A' + (i + j) % english_alphabet.size()));
			}
			//Add the inner map to the map
			map.put(outerChar, innerMap);
		}

	}

	//Print the Vigenere cipher table
	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
		
	}

	//Getter method
	public Map get_map() {
		return map;
	}
}
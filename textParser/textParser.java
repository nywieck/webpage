import java.util.*;
import java.io.*;

/**
 * @author Nathaniel Wieck
 * @version 9 MAY 2019
 * 
 * Program finds the top 10 words and number of times they occur in books by Charlotte Bronte but not used by Jane Austen, excluding proper nouns (names).
 * From the main, text files of the books are: turned into maps, merged, cleaned of errors, cleaned of proper nouns, and finally the top 10 are printed.
 * The top 10 results are printed after each iteration to show the progress.
 */
public class textParser {

	public static void main(String[] args) throws FileNotFoundException {
		Map<String, Integer> mapJane = new TreeMap<>();
		Map<String, Integer> mapCharlotte = new TreeMap<>();
		
		String[] fileNameArrJane = {"SenseAndSensibility.txt", "Persuasion.txt", "PrideAndPrejudics.txt", "Emma.txt", "MansfieldPark.txt"};
		for(int i = 0; i < fileNameArrJane.length; i++) {
			mapJane = textToMap(mapJane, fileNameArrJane[i]);
		}
		
		String[] fileNameArrCharlotte = {"JaneAyreAnAutobiography.txt", "TheProfessor.txt", "Shirley.txt", "Villette.txt"};
		for(int j = 0; j < fileNameArrCharlotte.length; j++) {
			mapCharlotte = textToMap(mapCharlotte, fileNameArrCharlotte[j]);
		}
		
		mapCharlotte = onlyCharlotte(mapJane, mapCharlotte);
		
		System.out.println("[ LIST 1: INCLUDING proper nouns, NOT cleaned ]");
		top10(mapCharlotte);
		
		mapCharlotte = removeProperNouns(mapCharlotte);
		System.out.println("\n\n[ LIST 2: EXCLUDING proper nouns, NOT cleaned ]");
		top10(mapCharlotte);
		
		mapCharlotte = cleanText(mapCharlotte);
		System.out.println("\n\n[ LIST 3: EXCLUDING proper nouns, and CLEANED of non-word fragments ]");
		top10(mapCharlotte);
	}
	
	/**
	 * textToMap method reads the text file, removes all characters except letters, then adds the words and counts to the existing HashMap.
	 * @param map is the existing HashMap of words and counts that will be updated
	 * @param text is the name of the text file to be read and added into the existing HashMap
	 * @return updated HashMap of words and counts
	 */
	public static Map<String, Integer> textToMap(Map<String, Integer> map, String text) throws FileNotFoundException {
		Scanner input = new Scanner(new File(text));
		input.useDelimiter("[^a-zA-Z']+");
		while(input.hasNext()) {
			String word = input.next();
			if(map.containsKey(word)) {
				int count = map.get(word);
				map.put(word, count + 1);
			} else {
				map.put(word, 1);
			}
		}

		return map;
	}
	
	/**
	 * onlyCharlotte method removes all word keys and corresponding count values in the mapCharlotte HashMap that also exist in the mapJane HashMap
	 * @param mapJane is the HashMap of words and counts found in the Jane Austen books
	 * @param mapCharlotte is the HashMap of words and counts found in the Charlotte Bronte books
	 * @return updated HashMap of keys and values of words that only Charlotte Bronte uses 
	 */
	public static Map<String, Integer> onlyCharlotte(Map<String, Integer> mapJane, Map<String, Integer> mapCharlotte) {
		mapCharlotte.keySet().removeAll(mapJane.keySet());
		
		return mapCharlotte;
	}
	
	/**
	 * removeProperNouns method removes all proper nouns from the HashMap by identifying proper nouns as words that start with capital letters
	 * and that never appear again in all lower case, then removing those word keys and corresponding count values.
	 * @param map is the HashMap of words and counts
	 * @return updated HashMap of keys and values without proper nouns
	 */
	public static Map<String, Integer> removeProperNouns(Map<String, Integer> map) {
		Map<String, Integer> mapNew = new HashMap<>();
		mapNew.putAll(map);
		for(String word : map.keySet()) {
			char firstLetter = word.charAt(0);
			if(firstLetter <= 90) {
				String wordToLower = word.toLowerCase();
				if(!map.containsKey(wordToLower)) {
					mapNew.remove(word);
				} else if(map.get(word) > map.get(wordToLower)) {
					mapNew.remove(word);
				}
			}
		}
		
		return mapNew;
	}
	
	/**
	 * cleanText method removes all word fragments caused by typos in original text files or from removing punctuation like apostrophes with delimeter
	 * @param map is the HashMap of words and counts
	 * @return updated HashMap of keys and values without word fragments
	 */
	public static Map<String, Integer> cleanText(Map<String, Integer> map) {
		Map<String, Integer> mapNew = new HashMap<>();
		mapNew.putAll(map);
		for(String word : map.keySet()) {
			if(word.length() == 1 && word.toLowerCase().equals("i") == false && word.toLowerCase().equals("a") == false) {
				mapNew.remove(word);
			}
		}
		
		return mapNew;
	}

	/**
	 * top10 method arranges the HashMap word keys according to corresponding count values in an ArrayList
	 * and finds / displays the top 10 most frequently used words
	 * @param map is the HashMap of words and counts
	 */
	public static void top10(Map<String, Integer> map) {
		ArrayList<Integer> countArray = new ArrayList<>(map.values());
		Collections.sort(countArray);
		int minThreshold = countArray.get(countArray.size() - 10);
		System.out.println("The following is a list of the top 10 most used words that only appear in Bronte's works, and not Austen's:\n");
		for (String word : map.keySet()) {
			int count = map.get(word);
			if(count >= minThreshold) {
				System.out.println("\"" + word + "\" occurs " + count + " times.");
			}
		}
	}
}
package cindywen.searchengine;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * SearchEngine takes the input file, stores the article information into memory, 
 * and provides the function of searching articles that contain the terms. 
 * @author Cynthia
 *
 */
public interface SearchEngine {
	
	/**
	 * The function to read (CSV) file.
	 * @param filename path to the input file
	 * @throws Exception FileNotExistException or IOException
	 */
	void readFile(String filename) throws Exception;
	
	/**
	 * The function to read file from reader.
	 * @param reader java.io.Reader to read from
	 * @throws IOException
	 */
	void readCSV(Reader reader) throws IOException;
	
	/**
	 * The function to read elements of the article.
	 * @param id
	 * @param title
	 * @param body
	 */
	void readElements(String id, String title, String body);

	/**
	 * The function to search articles that contain the array of terms.
	 * @param keyword
	 * @return search result.
	 */
	List<Article> search(String[] keyword);
	
	
	
}

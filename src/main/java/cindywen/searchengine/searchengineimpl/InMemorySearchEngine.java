package cindywen.searchengine.searchengineimpl;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import cindywen.searchengine.Article;
import cindywen.searchengine.SearchEngine;

/**
 * 
 * @author Cynthia
 *
 */
public class InMemorySearchEngine implements SearchEngine {

	private Hashtable<String, Set<String>> wordmap = new Hashtable<String, Set<String>>();
	private Hashtable<String, String> articles = new Hashtable<String, String>();
	/**
	 * The function to read CSV file into memory.
	 * @param filename
	 * @throws Exception 
	 */
	public void readFile(String filename) throws Exception {
		try (Reader in = new FileReader(filename)) {
			readCSV(in);
		} catch (IOException e) {
			throw e;
		}

	}
	
	public void readCSV(Reader reader) throws IOException {
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

		for(CSVRecord record : records) {
			String id = record.get(0);
			String title = record.get(1).toLowerCase();
			String body = record.get(2).toLowerCase();
			readElements(id, title, body);
				
		}
	}
	
	public void readElements(String id, String title, String body) {
		articles.put(id,title);
		processPassage(id, title);
		processPassage(id, body);
	}

	public List<Article> search(String[] keyword) {
		int n = keyword.length;
		Set<String> res = new HashSet<String>();
		res = wordmap.get(keyword[0]);
		if (res == null) return (new LinkedList<>());

		for (int i = 1; i < n; i++) {
			Set<String> tmp = new HashSet<String>();
			Set<String> fnd = wordmap.get(keyword[i]);
			if (fnd == null) return new LinkedList<>();; 
			for (String id: fnd) {
				if (res.contains(id)) {
					tmp.add(id);
				}
			}
			res = tmp;
		}	
		return getArticleFromId(res);
	}
	
	/**
	 * The function to put word into inverted index wordmap.
	 * @param word
	 */
	private void processPassage(String id, String passage) {
		String[] words = passage.split("\\W+");
		for (String word: words) {			
			if (wordmap.containsKey(word)) {
				if(!wordmap.get(word).contains(id)) {
					wordmap.get(word).add(id);
				}
			} else {
				Set<String> a = new HashSet<String>();
				a.add(id);
				wordmap.put(word, a);
			}
		}
	}
	
	/**
	 * The function to get the Article information from ids.
	 * @param idSet
	 * @return
	 */
	private List<Article> getArticleFromId(Set<String> idSet) {
		List<Article> ans = new LinkedList<>();
		for (String id: idSet) {
			String title = articles.get(id);
			Article art = new Article(id, title);
			ans.add(art);
		}
		return ans;
	}

}

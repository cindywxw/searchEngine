package cindywen.searchengine;

import java.io.FileNotFoundException;
import java.util.List;

public class App {
    public static void main(String[] args) {
    	if (args.length < 2) {
    		System.err.println("Please specify the csv file and the search terms.");
    		System.err.println("Usage: java -jar search.jar <documents.scv> [search terms])");
    		return;		
    	}   
    	
    	try {
    		SearchEngine searchEngine = SearchEngineFactory.inMemorySearchEngine();
    		searchEngine.readFile(args[0]);
    		String[] terms = new String[args.length - 1];
    		for (int i = 1; i < args.length; i++) {
    			terms[i - 1] = args[i]; 
    		}

    		List<Article> found = searchEngine.search(terms); 
    		if (found.size() == 0) {
    			System.out.println("No article matches.");
    		}
    		for (Article art: found) {
    			System.out.println(art);
    		}
    	} catch (Exception e) {
    		if (e instanceof FileNotFoundException) {
    			System.err.println(e.getMessage());
    		} else {
    			e.printStackTrace();
    		}
    	}
    }
    
   
}

package cindywen.searchengine;

import cindywen.searchengine.searchengineimpl.InMemorySearchEngine;

public class SearchEngineFactory {
	public static SearchEngine inMemorySearchEngine() {
		return new InMemorySearchEngine();
	}
}

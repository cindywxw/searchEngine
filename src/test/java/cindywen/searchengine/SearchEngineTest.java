package cindywen.searchengine;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import cindywen.searchengine.SearchEngine;
import cindywen.searchengine.searchengineimpl.InMemorySearchEngine;

public class SearchEngineTest {

	@Test
	public void searchEngineTest() {
		SearchEngine fake = new InMemorySearchEngine();
		
		fake.readElements("id is id", "title is title", "body is nobody");
		String[] str = {"is"};
		List<Article> res = fake.search(str);
		
		Article article = new Article("id is id", "title is title");
		
		List<Article> exp = new LinkedList<>();
		exp.add(article);
		
		Assert.assertEquals(exp, res);
		
		String[] str1 = {"not exist"};
		res = fake.search(str1);
		
		Assert.assertEquals(0, res.size());
	}
}

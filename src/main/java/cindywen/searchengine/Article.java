package cindywen.searchengine;

public class Article {
	private final String id;
	private final String title;
	
	public Article(String id, String title) {
		this.id = id;
		this.title = title;
	}

	public String getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s", id, title);
	}
	
	@Override
	public boolean equals(Object other) {
		return this.toString().equals(other.toString());
	}
}

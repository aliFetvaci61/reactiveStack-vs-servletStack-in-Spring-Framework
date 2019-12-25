package com.alifetvaci.reactivestack.domain;

public class Library {

	private String id;
	private String book;
	private String content;

	// Empty constructor is needed for Jackson to deserialize JSON
	public Library() {
	}

	public Library(String id, String book, String content) {
		this.id = id;
		this.book = book;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getBook() {
		return book;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Library{" + "id='" + id + '\'' + ", book='" + book + '\'' + ", content='" + content + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Library library = (Library) o;

		if (id != null ? !id.equals(library.id) : library.id != null)
			return false;
		if (book != null ? !book.equals(library.book) : library.book != null)
			return false;
		return content != null ? content.equals(library.content) : library.content == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (book != null ? book.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		return result;
	}
}

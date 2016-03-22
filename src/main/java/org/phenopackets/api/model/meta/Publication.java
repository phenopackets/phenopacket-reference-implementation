package org.phenopackets.api.model.meta;

import java.util.Objects;

public class Publication {
	
	private String title;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Publication that = (Publication) o;
		return Objects.equals(title, that.title) &&
				Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, id);
	}

	@Override
	public String toString() {
		return "Publication{" +
				"title='" + title + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}

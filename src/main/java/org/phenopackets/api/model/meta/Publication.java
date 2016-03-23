package org.phenopackets.api.model.meta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = Publication.Builder.class)
@JsonPropertyOrder({"id", "title"})
public class Publication {
	
	private final String id;
	private final String title;

	private Publication(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
    }

    public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
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
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				'}';
	}

    public static class Builder {

        @JsonProperty
        private String id;
        @JsonProperty
        private String title;

        @JsonCreator
        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Publication build() {
            return new Publication(this);
        }
    }
}

package io.github.russianmushroom.ID3v1Modifer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tag {

    private Map<TagElement, String> tags = new HashMap<>();
    private boolean isDefined;

    public Tag(boolean isDefined) {
        this.isDefined = isDefined;
    }

    public Tag initialise(List<Tuple<TagElement, String>> tags) {
        tags.forEach(tag -> this.tags.put(tag.getKey(), tag.getVal()));

        return this;
    }

    public String get(TagElement element) {
        return this.tags.get(element);
    }

    public boolean isDefined() {
        return isDefined;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, by %s in the album %s in %s. Comments: %s. Genre: %s",
                tags.get(TagElement.TITLE),
                tags.get(TagElement.ARTIST),
                tags.get(TagElement.ALBUM),
                tags.get(TagElement.YEAR),
                tags.get(TagElement.COMMENTS),
                tags.get(TagElement.GENRE));
    }
}

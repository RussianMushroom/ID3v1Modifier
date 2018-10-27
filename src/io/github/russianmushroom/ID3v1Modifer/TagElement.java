package io.github.russianmushroom.ID3v1Modifer;

public enum TagElement {
    TITLE(3, 30), ARTIST(33, 30),
    ALBUM(63, 30), GENRE(127, 1),
    YEAR(93, 4), COMMENTS(97, 30);

    private final int byteSize;
    private final int position;

    private TagElement(int position, int byteSize) {
        this.position = position;
        this.byteSize = byteSize;
    }

    public int getByteSize() {
        return byteSize;
    }

    public int getPosition() {
        return position;
    }
}

package io.github.russianmushroom.ID3v1Modifer;

import io.github.russianmushroom.ID3v1Modifer.reader.TagReader;
import io.github.russianmushroom.ID3v1Modifer.writer.TagWriter;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class TaggedMP3 {

    private File file;
    private Tag tag;

    public TaggedMP3(File file) {
        this.file = file;
    }

    public void read() {
        if (file.toPath().toString().endsWith(".mp3")) {
            this.tag = TagReader.read(file);
        }
    }

    public void write(List<Tuple<TagElement, String>> values) {
        TagWriter.write(values, file);
    }

    public File getFile() {
        return file;
    }

    public Tag getTag() {
        return tag;
    }
}

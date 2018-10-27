package io.github.russianmushroom.ID3v1Modifer.writer;

import io.github.russianmushroom.ID3v1Modifer.TagElement;
import io.github.russianmushroom.ID3v1Modifer.Tuple;
import io.github.russianmushroom.ID3v1Modifer.reader.TagReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TagWriter {

    public static void write(List<Tuple<TagElement, String>> values, File file) {
        byte[] contents = TagReader.readContents(file, 0, (int) file.length());

        try (FileOutputStream stream = new FileOutputStream(file)) {

            for (Tuple<TagElement, String> tuple : values) {
                byte[] buffer = truncate(tuple.getKey(), tuple.getVal().getBytes());
                int position = tuple.getKey().getPosition();

                System.arraycopy(buffer, 0, contents, (contents.length - 128) + position, buffer.length);
            }

            stream.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] truncate(TagElement type, byte[] value) {
        return value.length > type.getByteSize()
                ? Arrays.copyOfRange(value, 0, type.getByteSize())
                : value;
    }

}

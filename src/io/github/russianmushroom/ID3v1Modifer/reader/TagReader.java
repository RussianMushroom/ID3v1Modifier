package io.github.russianmushroom.ID3v1Modifer.reader;

import io.github.russianmushroom.ID3v1Modifer.Tag;
import io.github.russianmushroom.ID3v1Modifer.Tuple;

import java.io.*;
import java.time.Year;
import java.util.Arrays;

import static io.github.russianmushroom.ID3v1Modifer.TagElement.*;

public class TagReader {

    /**
     * Reads the mp3 files and deals with the last 128 bytes of the file
     * 0-2 (TAG) = Tag exists
     * 3 - 32    = Title
     * 33 - 62   = Artist
     * 63 - 92   = Album
     * 93 - 96   = Year
     * 97 - 126  = Comment
     * 127       = Genre
     * @param file The mp3 file that is to be processed
     * @return A Tag instance with all the song's details
     */
    public static Tag read(File file) {
        byte[] contents = readContents(file, (int) file.length() - 128, 128);

        // Check if the "TAG" exists
        if (new String(Arrays.copyOfRange(contents, 0, 3)).equals("TAG")) {
            return new Tag(true).initialise(Arrays.asList(
                    new Tuple<>(TITLE   , new String(Arrays.copyOfRange(contents, TITLE.getPosition()   , 32))),
                    new Tuple<>(ARTIST  , new String(Arrays.copyOfRange(contents, ARTIST.getPosition()  , 62))),
                    new Tuple<>(ALBUM   , new String(Arrays.copyOfRange(contents, ALBUM.getPosition()   , 92))),
                    new Tuple<>(YEAR    , new String(Arrays.copyOfRange(contents, YEAR.getPosition()    , 96))),
                    new Tuple<>(COMMENTS, new String(Arrays.copyOfRange(contents, COMMENTS.getPosition(), 126))),
                    new Tuple<>(GENRE   , contents[GENRE.getPosition()] + "")
            ));
        } else return new Tag(false);

    }

    public static byte[] readContents(File file, int begin, int size) {
        byte[] data = new byte[size];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.skip(begin);
            inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}

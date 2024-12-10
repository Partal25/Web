package mk.finki.ukim.mk.demo.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.demo.model.Album;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = null;
    public static List<Song> songList = null;
    public static List<Album> albumList = null;

    @PostConstruct
    public void init() {
        artistList = new ArrayList<>();
        artistList.add(new Artist(0L, "Aleksandar", "Partaloski", "Test bio bla bla"));
        artistList.add(new Artist(1L, "Petar", "Partaloski", "Test bio bla bla"));
        artistList.add(new Artist(2L, "Stefan", "Partaloski", "Test bio bla bla"));
        artistList.add(new Artist(3L, "Aleksandra", "Partaloski", "Test bio bla bla"));
        artistList.add(new Artist(4L, "Kiko", "Partaloski", "Test bio bla bla"));

        albumList = new ArrayList<>();
        albumList.add(new Album("RedAlbum", "Pop", "2003"));
        albumList.add(new Album("BlueAlbum", "Rap", "2013"));
        albumList.add(new Album("GreenAlbum", "EDM", "2021"));
        albumList.add(new Album("YellowAlbum", "Pop", "2034"));
        albumList.add(new Album("BlackAlbum", "Rock", "2006"));

        songList = new ArrayList<>();
        songList.add(new Song("0", "Pop", 2003, albumList.get(0), List.of(artistList.get(0))));
        songList.add(new Song("1", "Metal", 1913, albumList.get(1), List.of(artistList.get(1))));
        songList.add(new Song("2", "Pop", 1903, albumList.get(2), List.of(artistList.get(2))));
        songList.add(new Song("3", "EDM", 1893, albumList.get(3), List.of(artistList.get(3))));
        songList.add(new Song("4", "Rap", 2001, albumList.get(4), List.of(artistList.get(4))));
    }
}
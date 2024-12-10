package mk.finki.ukim.mk.demo.repository;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.demo.bootstrap.DataHolder;
import mk.finki.ukim.mk.demo.model.Album;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.*;

//@Repository
//public class SongRepository {
//    public List<Song> findAll() {
//        return DataHolder.songList;
//    }
//
//    public Optional<Song> findByTrackId(Long trackId) {
//        return DataHolder.songList.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst();
//    }
//
//    public Optional<Song> findByTitle(String title) {
//        return DataHolder.songList.stream().filter(s -> s.getTitle().equals(title)).findFirst();
//    }
//
//    public List<Song> findAllByArtist(Artist artist){
//        return DataHolder.songList.stream().filter(song -> song.hasArtist(artist)).toList();
//    }
//
//    public Artist addArtistToSong(Song song, Artist artist) {
//        song.addArtist(artist);
//        return artist;
//    }
//
//    public Song save(String title, String genre, Integer releaseYear, Album album){
//        Song song = new Song(title, genre, releaseYear, album, Collections.emptyList());
//        DataHolder.songList.removeIf(s -> Objects.equals(s.getTitle(), title));
//        DataHolder.songList.add(song);
//        return song;
//    }
//
//    public void deleteById(Long id) {
//        DataHolder.songList.removeIf(s -> s.getTrackId().equals(id));
//    }
//
//    public void update(Song song){
//        for(int i =0;i<DataHolder.songList.size();i++){
//            if(DataHolder.songList.get(i).getTrackId().equals(song.getTrackId())){
//                DataHolder.songList.set(i, song);
//                break;
//            }
//        }
//    }
//
//}

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByPerformersContaining(Artist performer);
    Optional<Song> findByTitle(String title);
}
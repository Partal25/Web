package mk.finki.ukim.mk.demo.service;

import mk.finki.ukim.mk.demo.exceptions.AlreadyExistsException;
import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Album;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    List<Song> listSongsByArtist(Long artistId) throws NotFoundException;
    Artist addArtistToTheSong(Long artistId, Long songId) throws NotFoundException;
    Song findByTrackId(Long trackId) throws NotFoundException;
    Song save(String title, String genre, Integer releaseYear, Long albumId) throws NotFoundException, AlreadyExistsException;
    Song update(Long trackId, String title, String genre, Integer releaseYear, Long albumId) throws NotFoundException;
    void deleteById(Long id);
}

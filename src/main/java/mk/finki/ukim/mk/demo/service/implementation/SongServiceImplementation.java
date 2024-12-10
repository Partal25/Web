package mk.finki.ukim.mk.demo.service.implementation;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.demo.bootstrap.DataHolder;
import mk.finki.ukim.mk.demo.exceptions.AlreadyExistsException;
import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Album;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.model.Song;
import mk.finki.ukim.mk.demo.repository.AlbumRepository;
import mk.finki.ukim.mk.demo.repository.ArtistRepository;
import mk.finki.ukim.mk.demo.repository.SongRepository;
import mk.finki.ukim.mk.demo.service.AlbumService;
import mk.finki.ukim.mk.demo.service.ArtistService;
import mk.finki.ukim.mk.demo.service.SongService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongServiceImplementation implements SongService {
    private final SongRepository songRepository;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> listSongsByArtist(Long artistId) throws NotFoundException {
        Artist artist = artistService.findById(artistId);
        return songRepository.findAllByPerformersContaining(artist);
    }

    @Override
    public Artist addArtistToTheSong(Long artistId, Long songId) throws NotFoundException {
        Song song = this.findByTrackId(songId);
        Artist artist = this.artistService.findById(artistId);

//        return songRepository.addArtistToSong(song, artist);
        return artist;
    }

    @Override
    public Song findByTrackId(Long trackId) throws NotFoundException {
        return songRepository.findById(trackId).orElseThrow(() -> new NotFoundException("Song with ID not found!"));
    }

    @Override
    public Song save(String title, String genre, Integer releaseYear, Long albumId) throws NotFoundException, AlreadyExistsException {
        if(this.songRepository.findByTitle(title).isPresent()){
            throw new AlreadyExistsException("Song with title already exists");
        }
        Album album = albumService.findById(albumId);

        return this.songRepository.save(new Song(title, genre, releaseYear, album, Collections.emptyList()));
    }

    @Override
    public Song update(Long trackId, String title, String genre, Integer releaseYear, Long albumId) throws NotFoundException {
        Song song = this.findByTrackId(trackId);
        Album album = albumService.findById(albumId);

        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        this.songRepository.save(song);
        return song;
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }




}
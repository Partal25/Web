package mk.finki.ukim.mk.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long trackId;
    String title;
    String genre;
    Integer releaseYear;

    @ManyToMany
    private List<Artist> performers;
    @ManyToOne
    private Album album;

    public Song(String title, String genre, Integer releaseYear, Album album, List<Artist> performers) {
        this.performers = performers;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
    }

    public boolean hasArtist(Artist artist){
        return this.performers.stream().anyMatch(a -> a.getId().equals(artist.getId()));
    }

    public void addArtist(Artist performer){
        performers.add(performer);
    }
}
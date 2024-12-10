package mk.finki.ukim.mk.demo.repository;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.demo.bootstrap.DataHolder;
import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    public List<Artist> findAll() {
        return DataHolder.artistList;
    }

    public Optional<Artist> findById(Long id) {
        return DataHolder.artistList.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }

    public Optional<Artist> findByName(String name) {
        return DataHolder.artistList.stream().filter(artist -> artist.getFirstName().equals(name)).findFirst();
    }
}
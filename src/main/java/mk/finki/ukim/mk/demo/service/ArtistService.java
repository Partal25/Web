package mk.finki.ukim.mk.demo.service;

import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();
    Artist findById (Long id) throws NotFoundException;
    Artist findByName(String name) throws NotFoundException;
}

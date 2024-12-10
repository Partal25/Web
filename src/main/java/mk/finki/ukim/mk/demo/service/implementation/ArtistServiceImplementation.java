package mk.finki.ukim.mk.demo.service.implementation;

import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.repository.ArtistRepository;
import mk.finki.ukim.mk.demo.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImplementation implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImplementation(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) throws NotFoundException {
        return artistRepository.findById(id).orElseThrow(() -> new NotFoundException("Artist with Id not found!"));
    }

    @Override
    public Artist findByName(String name) throws NotFoundException {
        return artistRepository.findByName(name).orElseThrow(() -> new NotFoundException("Artist with name not found!"));
    }
}

package mk.finki.ukim.mk.demo.service.implementation;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Album;
import mk.finki.ukim.mk.demo.repository.AlbumRepository;
import mk.finki.ukim.mk.demo.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumServiceImplementation implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album findById(Long albumId) throws NotFoundException {
        return albumRepository.findAlbumById(albumId).orElseThrow(() -> new NotFoundException("Album with id not found"));
    }



}

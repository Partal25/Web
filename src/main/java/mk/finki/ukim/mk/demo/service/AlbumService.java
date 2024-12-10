package mk.finki.ukim.mk.demo.service;

import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Album;
import mk.finki.ukim.mk.demo.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    public List<Album> findAll();
    Album findById(Long albumId) throws NotFoundException;
}

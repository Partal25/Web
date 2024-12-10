package mk.finki.ukim.mk.demo.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.finki.ukim.mk.demo.bootstrap.DataHolder;
import mk.finki.ukim.mk.demo.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Repository
//public class AlbumRepository {
//
//    public List<Album> findAll() {
//        return DataHolder.albumList;
//    }
//
//    public Optional<Album> findAlbumById(Long albumId) {
//        return DataHolder.albumList.stream().filter(a -> a.getId() == albumId).findFirst();
//    }
//}


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findAlbumById(Long id);
}
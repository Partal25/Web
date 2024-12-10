package mk.finki.ukim.mk.demo.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidAlbumIdException extends RuntimeException{
    public InvalidAlbumIdException(Long albumId){
        super(String.format("Album with id: %d was not found", albumId));


    }
}

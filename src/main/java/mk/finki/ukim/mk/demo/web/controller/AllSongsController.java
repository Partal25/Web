package mk.finki.ukim.mk.demo.web.controller;

import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.model.Song;
import mk.finki.ukim.mk.demo.service.ArtistService;
import mk.finki.ukim.mk.demo.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@Controller
public class AllSongsController {

    private final SongService songService;
    private final ArtistService artistService;

    public AllSongsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping("/allSongs")
    public String getAllSongs(@RequestParam(required = false) Long artistId, Model model) {
        List<Song> songs = null;
        Artist artist = null;

        try {
            if(artistId == null){
                songs = songService.listSongs();
            } else {
                artist = artistService.findById(artistId);
                model.addAttribute("selectedArtist", artist);

                songs = songService.listSongsByArtist(artistId);
            }

            model.addAttribute("songs", songs);
        } catch (NotFoundException nfe) {
            return String.format("redirect:/error?=%s", nfe.getMessage());
        }

        return "allSongs";
    }
}
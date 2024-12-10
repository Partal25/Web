package mk.finki.ukim.mk.demo.web.controller;

import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.model.Song;
import mk.finki.ukim.mk.demo.service.ArtistService;
import mk.finki.ukim.mk.demo.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistsPage(Model model) {
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("artists", artists);
        return "listArtists";
    }

    @GetMapping("/add")
    public String getAddArtistForm(@RequestParam(required = false) Long songId, Model model) {
        model.addAttribute("songId", songId);
        return "addArtist";
    }

    @PostMapping("/add")
    public String addArtistToSong(@RequestParam Long artistId, @RequestParam Long songId) {
        try{
            songService.addArtistToTheSong(artistId, songId);
            return "redirect:/songs";
        } catch (NotFoundException nfe){
            return String.format("redirect:/error?=%s", nfe.getMessage());
        }
    }

    @GetMapping("/delete/{artistId}")
    public String deleteArtist(@PathVariable Long artistId){
        try {
            artistService.findById(artistId);
            return "redirect:/artists";
        } catch (NotFoundException nfe) {
            return String.format("redirect:/error?=%s", nfe.getMessage());
        }
    }
}
package mk.finki.ukim.mk.demo.web.controller;

import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Album;
import mk.finki.ukim.mk.demo.model.Artist;
import mk.finki.ukim.mk.demo.model.Song;
import mk.finki.ukim.mk.demo.service.AlbumService;
import mk.finki.ukim.mk.demo.service.ArtistService;
import mk.finki.ukim.mk.demo.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, org.springframework.ui.Model model){

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }


        model.addAttribute("songs", this.songService.listSongs());
        return "listSongs";
    }

    @GetMapping("/songs/add-form")
    public String getAddSongPage(org.springframework.ui.Model model){
        List<Artist> artists = this.artistService.listArtists();
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @PostMapping("/songs/add")
    public String saveSong(@RequestParam(required = false) String title,
                           @RequestParam(required = false) Long trackId,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId) throws NotFoundException {


        return "redirect:/songs";
    }

    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/songs/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) throws NotFoundException {
        Song song = null;
        try {
            song = this.songService.findByTrackId(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Song with trackId does not exist!");
        }
        List<Artist> artists = this.artistService.listArtists();
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/songs/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer releaseYear,
                           @RequestParam Long albumId) {
        try {
            this.songService.update(songId, title, genre, releaseYear, albumId);
            return "redirect:/songs";
        } catch (NotFoundException nfe){
            return String.format("redirect:/error?=%s", nfe.getMessage());
        }
    }
}

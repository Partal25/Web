package mk.finki.ukim.mk.demo.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.demo.exceptions.NotFoundException;
import mk.finki.ukim.mk.demo.model.Song;
import mk.finki.ukim.mk.demo.service.ArtistService;
import mk.finki.ukim.mk.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

//@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songDetails")
public class SongDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    private final ArtistService artistService;

    private final SongService songService;

    public SongDetailsServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Song song = songService.listSongs().stream().findFirst().orElse(null);

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(exchange);
        context.setVariable("selectedSong", song);
        springTemplateEngine.process("songDetails.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long artistId = Long.parseLong(req.getParameter("artistId"));
        String trackId = req.getParameter("trackId");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        try{
            var song = this.songService.addArtistToTheSong(artistId, Long.parseLong(trackId));
            context.setVariable("entity", song);

            springTemplateEngine.process("songDetails.html", context, resp.getWriter());
        } catch (NotFoundException notFoundException){
            throw new ServletException(notFoundException.getMessage());
        }

    }
}
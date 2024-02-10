package com.nadhem.livres.controllers;

import com.nadhem.livres.entities.Genre;
import com.nadhem.livres.entities.Livre;
import com.nadhem.livres.service.GenreService;
import com.nadhem.livres.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/livres")
public class LivreController {
    @Autowired
    private LivreService livreService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/showCreate")
    public String showCreate(Model model) {
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
        model.addAttribute("livre", new Livre());
        return "createLivre";
    }

    @PostMapping("/saveLivre")
    public String saveLivre(@ModelAttribute("livre") @jakarta.validation.Valid Livre livre,
                            @RequestParam("genre") Long genreId,
                            @RequestParam("datePublication") String datePublication) throws ParseException {
        Genre genre = genreService.getGenreById(genreId);
        livre.setGenre(genre);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(datePublication);
        livre.setDatePublication(date);

        livreService.saveLivre(livre);
        return "redirect:/listeLivres";
    }
    @GetMapping("/listeLivres")
    public String listeLivres(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size) {
        Page<Livre> livres = livreService.getAllLivresParPage(page, size);
        model.addAttribute("livres", livres);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "listeLivres";
    }


	@GetMapping("/supprimerLivre")
    public String supprimerLivre(@RequestParam("id") Long id,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "3") int size) {
        livreService.deleteLivreById(id);
        return "redirect:/listeLivres?page=" + page + "&size=" + size;
    }

    @GetMapping("/modifierLivre")
    public String editerLivre(@RequestParam("id") Long idLivre, Model model) {
        Livre livre = livreService.getLivre(idLivre);
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("livre", livre);
        model.addAttribute("genres", genres);
        return "modifierLivre";
    }

    @PostMapping("/updateLivre")
    public String updateLivre(@ModelAttribute("livre") Livre livre,
                              @RequestParam("datePublication") String datePublication,
                              @RequestParam("genreId") Long genreId,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size) throws ParseException {
        Genre genre = genreService.getGenreById(genreId);
        livre.setGenre(genre);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(datePublication);
        livre.setDatePublication(date);

        livreService.saveLivre(livre);
        return "redirect:/listeLivres?page=" + page + "&size=" + size;
    }
}

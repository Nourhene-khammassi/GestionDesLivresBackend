package com.nadhem.livres.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nadhem.livres.entities.Genre;import com.nadhem.livres.service.GenreService;
import com.nadhem.livres.service.LivreService;

@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;


    @GetMapping("/showCreateGenre")
    public String showCreate(ModelMap model) {
        model.addAttribute("genre", new Genre());
        return "createGenre";
    }

    @PostMapping("/saveGenre")
    public String saveGenre(@ModelAttribute("genre") Genre genre, BindingResult result,
                            @RequestParam("dateCreation") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateCreation,
                            ModelMap modelMap) {
        genre.setDateCreation(dateCreation);
        Genre savedGenre = genreService.saveGenre(genre);
        String msg = "Genre enregistré avec Id " + savedGenre.getIdGen();
        modelMap.addAttribute("msg", msg);
        return "redirect:listeGenres";
    }

    @GetMapping("/listeGenres")
    public String listeGenres(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size) {
        return listGenres(modelMap, page, size);
    }

    @GetMapping("/supprimerGenre")
    public String supprimerGenre(@RequestParam("id") Long id, ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "2") int size) {
        genreService.deleteGenreById(id);
        return listGenres(modelMap, page, size);
    }

    // ...

    private String listGenres(ModelMap modelMap, int page, int size) {
        Page<Genre> genres = genreService.getAllGenresParPage(page, size);
        modelMap.addAttribute("genres", genres);
        modelMap.addAttribute("pages", new int[genres.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeGenres";
    }
}

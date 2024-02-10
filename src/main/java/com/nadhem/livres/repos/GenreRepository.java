package com.nadhem.livres.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nadhem.livres.entities.Genre;

@RepositoryRestResource(path ="gen")
@CrossOrigin("http://localhost:4200/")//pour autoriser angular
public interface GenreRepository extends JpaRepository<Genre, Long>{
	

}

package com.nadhem.livres.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gen")
    private Long idGen;

    @NotNull
    @Column(name = "nom_gen")
    private String nomGen;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<Livre> livres;

    public Long getIdGen() {
        return idGen;
    }

    public void setIdGen(Long idGen) {
        this.idGen = idGen;
    }

    public String getNomGen() {
        return nomGen;
    }

    public void setNomGen(String nomGen) {
        this.nomGen = nomGen;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Genre [idGen=" + idGen + ", nomGen=" + nomGen + ", dateCreation=" + dateCreation + "]";
    }

	
}

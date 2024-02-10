package com.nadhem.livres.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livre")
    private Long idLivre;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "titre_livre")
    private String titreLivre;

    @Nonnull
    @Size(min = 1, max = 30)
    @Column(name = "auteur_livre")
    private String auteurLivre;

    @Min(value = 1)
    @Column(name = "prix_livre")
    private Double prixLivre;

    @Column(name = "quantite_stock")
    private Integer quantiteStock;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_publication")
    private Date datePublication;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id_gen")
    private Genre genre;

    public Livre() {
    }

    public Livre(String titreLivre, String auteurLivre, Double prixLivre, Integer quantiteStock, Date datePublication, Genre genre) {
        this.titreLivre = titreLivre;
        this.auteurLivre = auteurLivre;
        this.prixLivre = prixLivre;
        this.quantiteStock = quantiteStock;
        this.datePublication = datePublication;
        this.genre = genre;
    }

    public Long getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Long idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    public String getAuteurLivre() {
        return auteurLivre;
    }

    public void setAuteurLivre(String auteurLivre) {
        this.auteurLivre = auteurLivre;
    }

    public Double getPrixLivre() {
        return prixLivre;
    }

    public void setPrixLivre(Double prixLivre) {
        this.prixLivre = prixLivre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Integer getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Integer quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "idLivre=" + idLivre +
                ", titreLivre='" + titreLivre + '\'' +
                ", auteurLivre='" + auteurLivre + '\'' +
                ", prixLivre=" + prixLivre +
                ", quantiteStock=" + quantiteStock +
                ", datePublication=" + datePublication +
                ", genre=" + genre +
                '}';
    }

}

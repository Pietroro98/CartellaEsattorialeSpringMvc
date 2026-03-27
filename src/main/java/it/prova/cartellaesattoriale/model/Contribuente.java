package it.prova.cartellaesattoriale.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contribuente")
public class Contribuente {
    //(id,nome,cognome,data di nascita, codiceFiscale,indirizzo)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "dataDiNascita", nullable = false)
    private LocalDate dataDiNascita;

    @Column(name = "codiceFiscale", unique = true, length = 16, nullable = false)
    private String codiceFiscale;

    @Column(name = "indirizzo", nullable = false)
    private String indirizzo;

    @OneToMany(mappedBy = "contribuente", fetch = FetchType.LAZY)
    private Set<CartellaEsattoriale> cartelle = new HashSet<>();

    public Contribuente() {}

    public Contribuente(String nome, String cognome, LocalDate dataDiNascita, String codiceFiscale, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.indirizzo = indirizzo;
    }

    public Contribuente(Long id, String nome, String cognome, LocalDate dataDiNascita, String codiceFiscale, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.indirizzo = indirizzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Set<CartellaEsattoriale> getCartelle() {
        return cartelle;
    }

    public void setCartelle(Set<CartellaEsattoriale> cartelle) {
        this.cartelle = cartelle;
    }
}

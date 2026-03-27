package it.prova.cartellaesattoriale.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cartellaEsattoriale")
public class CartellaEsattoriale
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descrizione",nullable = false)
    private String descrizione;

    @Column(name = "dataCreazione",nullable = false)
    private LocalDate dataCreazione;

    @Column(name = "importo", nullable = false, scale = 2)
    private BigDecimal importo;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false)
    private StatoCartella stato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contribuente_id", nullable = false)
    private Contribuente contribuente;

    public CartellaEsattoriale() {}

    public CartellaEsattoriale(String descrizione, LocalDate dataCreazione, BigDecimal importo, StatoCartella stato) {
        this.descrizione = descrizione;
        this.dataCreazione = dataCreazione;
        this.importo = importo;
        this.stato = stato;
    }

    public CartellaEsattoriale(Long id, String descrizione, LocalDate dataCreazione, BigDecimal importo, StatoCartella stato) {
        this.id = id;
        this.descrizione = descrizione;
        this.dataCreazione = dataCreazione;
        this.importo = importo;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public StatoCartella getStato() {
        return stato;
    }

    public void setStato(StatoCartella stato) {
        this.stato = stato;
    }

    public Contribuente getContribuente() {
        return contribuente;
    }

    public void setContribuente(Contribuente contribuente) {
        this.contribuente = contribuente;
    }
}

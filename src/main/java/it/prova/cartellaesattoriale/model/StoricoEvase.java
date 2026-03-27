package it.prova.cartellaesattoriale.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "storicoEvase")
public class StoricoEvase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "idCartellaOld", nullable = false, unique = true)
    private String idCartellaOld;

    @Column(name = "descrizioneOld", nullable = false)
    private String descrizioneOld;

    @Column(name = "importoOld", nullable = false, scale = 2)
    private BigDecimal importoOld;

    @Column(name = "dataCreazioneOld", nullable = false)
    private LocalDate dataCreazioneOld;

    @Column(name = "nomeContribuenteOld", nullable = false)
    private String nomeContribuenteOld;

    @Column(name = "cognomeContribuenteOld", nullable = false)
    private String cognomeContribuenteOld;

    @Column(name = "codiceFiscaleOld", nullable = false)
    private String codiceFiscaleOld;

    @Column(name = "dataStoricizzazione", nullable = false)
    private LocalDateTime dataStoricizzazione;

    public StoricoEvase() {}

    public StoricoEvase(Long id, String idCartellaOld, String descrizioneOld, BigDecimal importoOld,
                        LocalDate dataCreazioneOld, String nomeContribuenteOld, String cognomeContribuenteOld,
                        String codiceFiscaleOld, LocalDateTime dataStoricizzazione) {
        this.id = id;
        this.idCartellaOld = idCartellaOld;
        this.descrizioneOld = descrizioneOld;
        this.importoOld = importoOld;
        this.dataCreazioneOld = dataCreazioneOld;
        this.nomeContribuenteOld = nomeContribuenteOld;
        this.cognomeContribuenteOld = cognomeContribuenteOld;
        this.codiceFiscaleOld = codiceFiscaleOld;
        this.dataStoricizzazione = dataStoricizzazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCartellaOld() {
        return idCartellaOld;
    }

    public void setIdCartellaOld(String idCartellaOld) {
        this.idCartellaOld = idCartellaOld;
    }

    public String getDescrizioneOld() {
        return descrizioneOld;
    }

    public void setDescrizioneOld(String descrizioneOld) {
        this.descrizioneOld = descrizioneOld;
    }

    public BigDecimal getImportoOld() {
        return importoOld;
    }

    public void setImportoOld(BigDecimal importoOld) {
        this.importoOld = importoOld;
    }

    public LocalDate getDataCreazioneOld() {
        return dataCreazioneOld;
    }

    public void setDataCreazioneOld(LocalDate dataCreazioneOld) {
        this.dataCreazioneOld = dataCreazioneOld;
    }

    public String getNomeContribuenteOld() {
        return nomeContribuenteOld;
    }

    public void setNomeContribuenteOld(String nomeContribuenteOld) {
        this.nomeContribuenteOld = nomeContribuenteOld;
    }

    public String getCognomeContribuenteOld() {
        return cognomeContribuenteOld;
    }

    public void setCognomeContribuenteOld(String cognomeContribuenteOld) {
        this.cognomeContribuenteOld = cognomeContribuenteOld;
    }

    public String getCodiceFiscaleOld() {
        return codiceFiscaleOld;
    }

    public void setCodiceFiscaleOld(String codiceFiscaleOld) {
        this.codiceFiscaleOld = codiceFiscaleOld;
    }

    public LocalDateTime getDataStoricizzazione() {
        return dataStoricizzazione;
    }

    public void setDataStoricizzazione(LocalDateTime dataStoricizzazione) {
        this.dataStoricizzazione = dataStoricizzazione;
    }
}

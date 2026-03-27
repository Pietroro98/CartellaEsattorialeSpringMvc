package it.prova.cartellaesattoriale.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.cartellaesattoriale.model.StoricoEvase;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoricoEvaseDTO
{
    private Long id;
    private String idCartellaOld;
    private String descrizioneOld;
    private BigDecimal importoOld;
    private LocalDate dataCreazioneOld;
    private String nomeContribuenteOld;
    private String cognomeContribuenteOld;
    private String codiceFiscaleOld;
    private LocalDate dataStoricizzazione;

    public StoricoEvaseDTO() {
    }

    public StoricoEvaseDTO(Long id, String idCartellaOld, String descrizioneOld, BigDecimal importoOld,
                           LocalDate dataCreazioneOld, String nomeContribuenteOld, String cognomeContribuenteOld,
                           String codiceFiscaleOld, LocalDate dataStoricizzazione) {
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

    public LocalDate getDataStoricizzazione() {
        return dataStoricizzazione;
    }

    public void setDataStoricizzazione(LocalDate dataStoricizzazione) {
        this.dataStoricizzazione = dataStoricizzazione;
    }

    public StoricoEvase buildModel() {
        return new StoricoEvase(id, idCartellaOld, descrizioneOld, importoOld, dataCreazioneOld,
                nomeContribuenteOld, cognomeContribuenteOld, codiceFiscaleOld, dataStoricizzazione);
    }

    public static StoricoEvaseDTO buildDTOFromModel(StoricoEvase model) {
        return new StoricoEvaseDTO(model.getId(), model.getIdCartellaOld(), model.getDescrizioneOld(),
                model.getImportoOld(), model.getDataCreazioneOld(), model.getNomeContribuenteOld(),
                model.getCognomeContribuenteOld(), model.getCodiceFiscaleOld(), model.getDataStoricizzazione());
    }

    public static List<StoricoEvaseDTO> createListFromModelList(List<StoricoEvase> modelList) {
        return modelList.stream().map(StoricoEvaseDTO::buildDTOFromModel).collect(Collectors.toList());
    }

}

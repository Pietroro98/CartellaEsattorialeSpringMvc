package it.prova.cartellaesattoriale.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.model.StatoCartella;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartellaEsattorialeDTO
{
    private Long id;

    @NotBlank(message = "Il campo descrizione deve essere valorizzato")
    private String descrizione;

    @NotNull(message = "Il campo dataCreazione deve essere valorizzato")
    private LocalDate dataCreazione;

    @NotNull(message = "Il campo importo deve essere valorizzato")
    @DecimalMin(value = "0.01", message = "L'importo deve essere maggiore di zero")
    private BigDecimal importo;

    @NotNull(message = "Il campo stato deve essere valorizzato")
    private StatoCartella stato;

    @JsonIgnoreProperties("cartelle")
    @NotNull(message = "Il contribuente deve essere valorizzato")
    private ContribuenteDTO contribuente;

    public CartellaEsattorialeDTO() {}

    public CartellaEsattorialeDTO(Long id, String descrizione, LocalDate dataCreazione, BigDecimal importo,
                                  StatoCartella stato) {
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

    public ContribuenteDTO getContribuente() {
        return contribuente;
    }

    public void setContribuente(ContribuenteDTO contribuente) {
        this.contribuente = contribuente;
    }

    public CartellaEsattoriale buildModel() {
        CartellaEsattoriale result = new CartellaEsattoriale(id, descrizione, dataCreazione, importo, stato);
        if (contribuente != null) {
            result.setContribuente(contribuente.buildModel());
        }
        return result;
    }

    public static CartellaEsattorialeDTO buildDTOFromModel(CartellaEsattoriale model, boolean includeContribuente) {
        CartellaEsattorialeDTO result = new CartellaEsattorialeDTO(model.getId(), model.getDescrizione(),
                model.getDataCreazione(), model.getImporto(), model.getStato());
        if (includeContribuente) {
            result.setContribuente(ContribuenteDTO.buildDTOFromModel(model.getContribuente(), false));
        }
        return result;
    }

    public static List<CartellaEsattorialeDTO> createListFromModelList(List<CartellaEsattoriale> modelList,
                                                                       boolean includeContribuente) {
        return modelList.stream().map(element -> buildDTOFromModel(element, includeContribuente)).collect(Collectors.toList());
    }

    public static Set<CartellaEsattorialeDTO> createSetFromModelSet(Set<CartellaEsattoriale> modelSet,
                                                                    boolean includeContribuente) {
        return modelSet.stream().map(element -> buildDTOFromModel(element, includeContribuente)).collect(Collectors.toSet());
    }
}

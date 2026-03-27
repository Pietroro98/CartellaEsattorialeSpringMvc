package it.prova.cartellaesattoriale.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.cartellaesattoriale.model.Contribuente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContribuenteDTO {

    private Long id;

    @NotBlank(message = "Il campo nome deve essere valorizzato")
    @Valid()
    private String nome;

    @NotBlank(message = "Il campo cognome deve essere valorizzato")
    private String cognome;

    @NotNull(message = "Il campo dataNascita deve essere valorizzato")
    private LocalDate dataNascita;

    @NotBlank(message = "Il campo codiceFiscale deve essere valorizzato")
    @Size(min = 16, message = "Il codice fiscale deve essere di 16 caratteri")
    private String codiceFiscale;

    @NotBlank(message = "Il campo indirizzo deve essere valorizzato")
    private String indirizzo;

    private Boolean daAttenzionare;

    @JsonIgnoreProperties("contribuente")
    private Set<CartellaEsattorialeDTO> cartelle;

    public ContribuenteDTO() {
    }

    public ContribuenteDTO(Long id, String nome, String cognome, LocalDate dataNascita, String codiceFiscale,
                           String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
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

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
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

    public Boolean getDaAttenzionare() {
        return daAttenzionare;
    }

    public void setDaAttenzionare(Boolean daAttenzionare) {
        this.daAttenzionare = daAttenzionare;
    }

    public Set<CartellaEsattorialeDTO> getCartelle() {
        return cartelle;
    }

    public void setCartelle(Set<CartellaEsattorialeDTO> cartelle) {
        this.cartelle = cartelle;
    }

    public Contribuente buildModel() {
        return new Contribuente(id, nome, cognome, dataNascita, codiceFiscale, indirizzo);
    }

    public static ContribuenteDTO buildDTOFromModel(Contribuente model, boolean includeCartelle)
    {
        ContribuenteDTO result = new ContribuenteDTO(model.getId(), model.getNome(), model.getCognome(),
                model.getDataDiNascita(), model.getCodiceFiscale(), model.getIndirizzo());
        if (includeCartelle) {
            result.setCartelle(CartellaEsattorialeDTO.createSetFromModelSet(model.getCartelle(), false));
        }
        return result;
    }

    public static List<ContribuenteDTO> createListFromModelList(List<Contribuente> modelList, boolean includeCartelle) {
        return modelList.stream().map(element -> buildDTOFromModel(element, includeCartelle)).collect(Collectors.toList());
    }
}

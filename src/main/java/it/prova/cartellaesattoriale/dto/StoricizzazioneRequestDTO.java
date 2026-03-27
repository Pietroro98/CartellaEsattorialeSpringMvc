package it.prova.cartellaesattoriale.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class StoricizzazioneRequestDTO {

    @NotNull(message = "Il campo dataRiferimento deve essere valorizzato")
    private LocalDate dataRiferimento;

    public LocalDate getDataRiferimento() {
        return dataRiferimento;
    }

    public void setDataRiferimento(LocalDate dataRiferimento) {
        this.dataRiferimento = dataRiferimento;
    }
}

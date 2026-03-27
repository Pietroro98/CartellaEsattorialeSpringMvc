package it.prova.cartellaesattoriale.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface StoricoEvaseProjection {
    Long getIdCartella();
    String getDescrizione();
    BigDecimal getImporto();
    LocalDate getDataCreazione();
    String getNomeContribuente();
    String getCognomeContribuente();
    String getCodiceFiscale();
}

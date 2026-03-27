package it.prova.cartellaesattoriale.dto;

import java.math.BigDecimal;

public class ReportContribuentiDTO
{
    private BigDecimal totImportoCartelle;
    private BigDecimal totConclusoEPagato;
    private BigDecimal totInContenzioso;

    public ReportContribuentiDTO() {
    }

    public ReportContribuentiDTO(BigDecimal totImportoCartelle, BigDecimal totConclusoEPagato,
                                 BigDecimal totInContenzioso) {
        this.totImportoCartelle = totImportoCartelle;
        this.totConclusoEPagato = totConclusoEPagato;
        this.totInContenzioso = totInContenzioso;
    }

    public BigDecimal getTotImportoCartelle() {
        return totImportoCartelle;
    }

    public void setTotImportoCartelle(BigDecimal totImportoCartelle) {
        this.totImportoCartelle = totImportoCartelle;
    }

    public BigDecimal getTotConclusoEPagato() {
        return totConclusoEPagato;
    }

    public void setTotConclusoEPagato(BigDecimal totConclusoEPagato) {
        this.totConclusoEPagato = totConclusoEPagato;
    }

    public BigDecimal getTotInContenzioso() {
        return totInContenzioso;
    }

    public void setTotInContenzioso(BigDecimal totInContenzioso) {
        this.totInContenzioso = totInContenzioso;
    }
}

package it.prova.cartellaesattoriale.service.storico;

import it.prova.cartellaesattoriale.model.StoricoEvase;

import java.time.LocalDate;
import java.util.List;

public interface StoricoEvaseService {
    List<StoricoEvase> inserisci(LocalDate dataRiferimento);
    List<StoricoEvase> findByExample(StoricoEvase example);
}

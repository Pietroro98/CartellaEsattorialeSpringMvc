package it.prova.cartellaesattoriale.service.storico;

import it.prova.cartellaesattoriale.model.StoricoEvase;

import java.util.List;

public interface StoricoEvaseService {
    StoricoEvase inserisci(StoricoEvase storicoEvase);
    List<StoricoEvase> findByExample(StoricoEvase example);
}

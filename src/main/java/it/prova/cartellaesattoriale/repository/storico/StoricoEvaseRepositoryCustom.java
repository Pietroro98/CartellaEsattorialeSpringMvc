package it.prova.cartellaesattoriale.repository.storico;

import it.prova.cartellaesattoriale.model.StoricoEvase;;
import java.util.List;

public interface StoricoEvaseRepositoryCustom {
    List<StoricoEvase> findByExample(StoricoEvase example);
}

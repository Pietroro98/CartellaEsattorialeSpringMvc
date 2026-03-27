package it.prova.cartellaesattoriale.service.contribuente;

import it.prova.cartellaesattoriale.dto.ReportContribuentiDTO;
import it.prova.cartellaesattoriale.model.Contribuente;

import java.util.List;

public interface ContribuenteService {
    List<Contribuente> listAll(boolean eager);
    Contribuente caricaSingolo(Long id);
    Contribuente caricaSingoloEager(Long id);
    Contribuente inserisci(Contribuente contribuente);
    Contribuente aggiorna(Contribuente contribuente);
    void rimuovi(Long id);
    ReportContribuentiDTO generaReport();
}

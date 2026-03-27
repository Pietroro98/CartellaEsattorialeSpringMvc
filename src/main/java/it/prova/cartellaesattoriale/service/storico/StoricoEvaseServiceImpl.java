package it.prova.cartellaesattoriale.service.storico;
import it.prova.cartellaesattoriale.model.StoricoEvase;
import it.prova.cartellaesattoriale.repository.storico.StoricoEvaseProjection;
import it.prova.cartellaesattoriale.repository.storico.StoricoEvaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StoricoEvaseServiceImpl implements StoricoEvaseService
{
    @Autowired
    private StoricoEvaseRepository storicoEvaseRepository;


    /**
     * Recupera, tramite projection interface, i dati delle cartelle da storicizzare
     * alla data di riferimento indicata, senza caricare interamente le entità dal database.
     *
     * I dati restituiti dalla projection `StoricoEvaseProjection` vengono usati per
     * costruire nuovi oggetti `StoricoEvase`, popolando i campi storici con i valori
     * letti dalla query.
     *
     * Per ogni record vengono valorizzati:
     * l'id della cartella, la descrizione, l'importo, la data di creazione
     * e i dati anagrafici del contribuente. La data di storicizzazione viene
     * invece impostata con la data corrente.
     *
     * Se non risultano cartelle da storicizzare, il metodo restituisce una lista vuota;
     * altrimenti salva tutti i record storici creati e restituisce la lista salvata.
     *
     * @param dataRiferimento data usata per individuare le cartelle da storicizzare
     * @return lista dei record storicizzati salvati nel database, oppure lista vuota se non ci sono dati da storicizzare
     */
    @Override
    @Transactional
    public List<StoricoEvase> inserisci(LocalDate dataRiferimento) {
        List<StoricoEvaseProjection> records = storicoEvaseRepository.findCartelleDaStoricizzare(dataRiferimento);

        List<StoricoEvase> daInserire = records
                .stream()
                .map(
                        item ->
                        {
                            StoricoEvase storico = new StoricoEvase();
                            storico.setIdCartellaOld(String.valueOf(item.getIdCartella()));
                            storico.setDescrizioneOld(item.getDescrizione());
                            storico.setImportoOld(item.getImporto());
                            storico.setDataCreazioneOld(item.getDataCreazione());
                            storico.setNomeContribuenteOld(item.getNomeContribuente());
                            storico.setCognomeContribuenteOld(item.getCognomeContribuente());
                            storico.setCodiceFiscaleOld(item.getCodiceFiscale());
                            storico.setDataStoricizzazione(LocalDate.now());
                            return storico;
                        }
                ).toList();
        return daInserire.isEmpty() ? daInserire : storicoEvaseRepository.saveAll(daInserire);
    }

    @Override
    public List<StoricoEvase> findByExample(StoricoEvase example) {
        return storicoEvaseRepository.findByExample(example);
    }
}

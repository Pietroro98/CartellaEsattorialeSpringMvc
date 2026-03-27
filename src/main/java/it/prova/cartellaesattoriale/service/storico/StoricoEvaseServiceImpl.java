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

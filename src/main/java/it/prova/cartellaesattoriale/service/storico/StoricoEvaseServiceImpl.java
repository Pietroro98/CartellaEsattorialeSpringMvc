package it.prova.cartellaesattoriale.service.storico;
import it.prova.cartellaesattoriale.model.StoricoEvase;
import it.prova.cartellaesattoriale.repository.storico.StoricoEvaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StoricoEvaseServiceImpl implements StoricoEvaseService
{
    @Autowired
    private StoricoEvaseRepository storicoEvaseRepository;

    @Override
    @Transactional
    public StoricoEvase inserisci(StoricoEvase storicoEvase) {
        return storicoEvaseRepository.save(storicoEvase);
    }

    @Override
    public List<StoricoEvase> findByExample(StoricoEvase example) {
        return List.of();
    }
}

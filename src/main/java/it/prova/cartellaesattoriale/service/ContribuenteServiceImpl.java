package it.prova.cartellaesattoriale.service;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.repository.ContribuenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContribuenteServiceImpl implements ContribuenteService {

    @Autowired
    private ContribuenteRepository contribuenteRepository;

    @Override
    public List<Contribuente> listAll(boolean eager) {
        return eager ? contribuenteRepository.findAllEager() : contribuenteRepository.findAll();
    }

    @Override
    public Contribuente caricaSingolo(Long id) {
        return contribuenteRepository.findById(id).orElse(null);
    }

    @Override
    public Contribuente caricaSingoloEager(Long id) {
        return contribuenteRepository.findSingleEager(id);
    }

    @Override
    @Transactional
    public Contribuente inserisci(Contribuente contribuente) {
        return contribuenteRepository.save(contribuente);
    }

    @Override
    @Transactional
    public Contribuente aggiorna(Contribuente contribuente) {
        return contribuenteRepository.save(contribuente);
    }

    @Override
    public void rimuovi(Long id) {
        contribuenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contribuente non trovato con id: " + id));
        contribuenteRepository.deleteById(id);
    }
}

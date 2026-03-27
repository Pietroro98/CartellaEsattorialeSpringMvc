package it.prova.cartellaesattoriale.service.contribuente;
import it.prova.cartellaesattoriale.dto.ContribuenteDTO;
import it.prova.cartellaesattoriale.dto.ReportContribuentiDTO;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.model.StatoCartella;
import it.prova.cartellaesattoriale.repository.contribuente.ContribuenteRepository;
import it.prova.cartellaesattoriale.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                .orElseThrow(() -> new NotFoundException("Contribuente non trovato con id: " + id));
        contribuenteRepository.deleteById(id);
    }

    @Override
    public List<ContribuenteDTO> verificaContenziosi() {
        Set<Long> idsDaAttenzionare = new HashSet<>(contribuenteRepository.findIdsDaAttenzionare());

        return listAll(false).stream()
                .map(contribuente -> {
                    ContribuenteDTO dto = ContribuenteDTO.buildDTOFromModel(contribuente, false);
                    if (idsDaAttenzionare.contains(contribuente.getId())) {
                        dto.setDaAttenzionare(true);
                    }
                    return dto;
                })
                .toList();
    }

    @Override
    public ReportContribuentiDTO generaReport() {
        return new ReportContribuentiDTO(
                contribuenteRepository.sumTutteLeCartelle(),
                contribuenteRepository.sumByStato(StatoCartella.CONCLUSA),
                contribuenteRepository.sumByStato(StatoCartella.IN_CONTENZIOSO)
        );
    }
}

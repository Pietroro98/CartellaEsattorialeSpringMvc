package it.prova.cartellaesattoriale.web.api;
import it.prova.cartellaesattoriale.dto.ContribuenteDTO;
import it.prova.cartellaesattoriale.dto.MessageDTO;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.repository.contribuente.ContribuenteRepository;
import it.prova.cartellaesattoriale.service.cartella.CartellaEsattorialeService;
import it.prova.cartellaesattoriale.service.contribuente.ContribuenteService;
import it.prova.cartellaesattoriale.web.exception.BadRequestException;
import it.prova.cartellaesattoriale.web.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/contribuente")
public class ContribuenteController {

    @Autowired
    private ContribuenteService contribuenteService;

    @Autowired
    private ContribuenteRepository contribuenteRepository;

    @GetMapping
    public List<ContribuenteDTO> listAll() {
        return ContribuenteDTO.createListFromModelList(contribuenteService.listAll(true), true);
    }

    @GetMapping("/{id}")
    public ContribuenteDTO findById(@PathVariable Long id) {
        Contribuente contribuente = contribuenteService.caricaSingoloEager(id);
        if (contribuente == null) {
            throw new NotFoundException("Contribuente non trovato con id: " + id);
        }
        return ContribuenteDTO.buildDTOFromModel(contribuente, true);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContribuenteDTO create(@Valid @RequestBody ContribuenteDTO input) {
        if (input.getId() != null) {
            throw new BadRequestException("Non e' ammesso fornire l'id in creazione");
        }
        return ContribuenteDTO.buildDTOFromModel(contribuenteService.inserisci(input.buildModel()), false);
    }

    @PutMapping("/{id}")
    public ContribuenteDTO update(@PathVariable Long id, @Valid @RequestBody ContribuenteDTO input) {
        if (input.getId() != null) {
            throw new BadRequestException("Non e' ammesso fornire l'id nel body");
        }
        if (contribuenteService.caricaSingolo(id) == null) {
            throw new NotFoundException("Contribuente non trovato con id: " + id);
        }
        input.setId(id);
        return ContribuenteDTO.buildDTOFromModel(contribuenteService.aggiorna(input.buildModel()), false);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageDTO delete(@PathVariable Long id) {
        contribuenteService.rimuovi(id);
        return new MessageDTO("Contribuente eliminato correttamente");
    }


    /**
     * GET /verificaContenziosi che restituisce la lista di contribuenti (lazy)
     * e per ogni voce va evidenziato nel JSON di risposta se (e solo se)
     * quel contribuente ha almeno una Cartella Esattoriale in stato IN_CONTENZIOSO.
     * Il sistema dovrà evidenziarlo con un apposito campo aggiuntivo -> (daAttenzionare:true),
     * che non comparirà negli altri casi.
     *
     * @return List<ContribuenteDTO> con il campo daAttenzionare valorizzato a true per i contribuenti con cartelle in contenzioso
     */
    @GetMapping("/verificaContenziosi")
    public List<ContribuenteDTO> verificaContenziosi() {
        Set<Long> idsDaAttenzionare = new HashSet<>(contribuenteRepository.findIdsDaAttenzionare());
        return contribuenteService.listAll(false).stream()
                .map(contribuente -> {
                    ContribuenteDTO dto = ContribuenteDTO.buildDTOFromModel(contribuente, false);
                    if (idsDaAttenzionare.contains(contribuente.getId())) {
                        dto.setDaAttenzionare(true);
                    }
                    return dto;
                })
                .toList();
    }
}

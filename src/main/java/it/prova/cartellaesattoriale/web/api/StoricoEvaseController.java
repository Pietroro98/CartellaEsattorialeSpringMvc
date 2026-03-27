package it.prova.cartellaesattoriale.web.api;
import it.prova.cartellaesattoriale.dto.StoricizzazioneRequestDTO;
import it.prova.cartellaesattoriale.dto.StoricoEvaseDTO;
import it.prova.cartellaesattoriale.service.storico.StoricoEvaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storicoEvase")
public class StoricoEvaseController {

    @Autowired
    private StoricoEvaseService storicoEvaseService;

    @PostMapping("/search")
    public List<StoricoEvaseDTO> search(@RequestBody StoricoEvaseDTO example) {
        return StoricoEvaseDTO.createListFromModelList(storicoEvaseService.findByExample(example.buildModel()));
    }

    @PostMapping("/storicizzaEvaseCreateAPartireDa")
    @ResponseStatus(HttpStatus.CREATED)
    public List<StoricoEvaseDTO> storicizza(@RequestBody StoricizzazioneRequestDTO input) {
        return StoricoEvaseDTO.createListFromModelList(
                storicoEvaseService.inserisci(input.getDataRiferimento())
        );
    }

}

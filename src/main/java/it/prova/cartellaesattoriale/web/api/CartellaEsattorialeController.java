package it.prova.cartellaesattoriale.web.api;
import it.prova.cartellaesattoriale.dto.CartellaEsattorialeDTO;
import it.prova.cartellaesattoriale.dto.MessageDTO;
import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.service.CartellaEsattorialeService;
import it.prova.cartellaesattoriale.service.ContribuenteService;
import it.prova.cartellaesattoriale.web.exception.BadRequestException;
import it.prova.cartellaesattoriale.web.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cartellaEsattoriale")
public class CartellaEsattorialeController {

    @Autowired
    private CartellaEsattorialeService cartellaEsattorialeService;

    @Autowired
    private ContribuenteService contribuenteService;

    @GetMapping
    public List<CartellaEsattorialeDTO> listAll() {
        return CartellaEsattorialeDTO.createListFromModelList(cartellaEsattorialeService.listAll(true), true);
    }

    @GetMapping("/{id}")
    public CartellaEsattorialeDTO findById(@PathVariable Long id) {
        CartellaEsattoriale cartella = cartellaEsattorialeService.caricaSingolaEager(id);
        if (cartella == null) {
            throw new NotFoundException("Cartella esattoriale non trovata con id: " + id);
        }
        return CartellaEsattorialeDTO.buildDTOFromModel(cartella, true);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartellaEsattorialeDTO create(@Valid @RequestBody CartellaEsattorialeDTO input) {
        CartellaEsattoriale cartella = buildCartella(input, null);
        return CartellaEsattorialeDTO.buildDTOFromModel(cartellaEsattorialeService.inserisci(cartella), true);
    }

    @PutMapping("/{id}")
    public CartellaEsattorialeDTO update(@PathVariable Long id, @Valid @RequestBody CartellaEsattorialeDTO input) {
        if (cartellaEsattorialeService.caricaSingola(id) == null) {
            throw new NotFoundException("Cartella esattoriale non trovata con id: " + id);
        }
        CartellaEsattoriale cartella = buildCartella(input, id);
        return CartellaEsattorialeDTO.buildDTOFromModel(cartellaEsattorialeService.aggiorna(cartella), true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageDTO delete(@PathVariable Long id) {
        cartellaEsattorialeService.rimuovi(id);
        return new MessageDTO("Cartella esattoriale eliminata correttamente");
    }

    private CartellaEsattoriale buildCartella(CartellaEsattorialeDTO input, Long id) {
        if (id == null && input.getId() != null) {
            throw new BadRequestException("Non e' ammesso fornire l'id in creazione");
        }
        if (id != null && input.getId() != null) {
            throw new BadRequestException("Non e' ammesso fornire l'id nel body");
        }
        if (input.getContribuente() == null || input.getContribuente().getId() == null) {
            throw new BadRequestException("Occorre fornire il contribuente con id");
        }

        Contribuente contribuente = contribuenteService.caricaSingolo(input.getContribuente().getId());
        if (contribuente == null) {
            throw new NotFoundException("Contribuente non trovato con id: " + input.getContribuente().getId());
        }

        CartellaEsattoriale result = input.buildModel();
        result.setId(id);
        result.setContribuente(contribuente);
        return result;
    }
}

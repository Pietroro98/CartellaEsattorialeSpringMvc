package it.prova.cartellaesattoriale.service.cartella;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.repository.cartella.CartellaEsattorialeRepository;
import it.prova.cartellaesattoriale.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService
{
    @Autowired
    private CartellaEsattorialeRepository cartellaEsattorialeRepository;

    @Override
    public List<CartellaEsattoriale> listAll(boolean eager) {
        return eager ? cartellaEsattorialeRepository.findAllEager() : cartellaEsattorialeRepository.findAll();
    }

    @Override
    public CartellaEsattoriale caricaSingola(Long id) {
        return cartellaEsattorialeRepository.findById(id).orElse(null);
    }

    @Override
    public CartellaEsattoriale caricaSingolaEager(Long id) {
        return cartellaEsattorialeRepository.findSingleEager(id);
    }

    @Override
    @Transactional
    public CartellaEsattoriale inserisci(CartellaEsattoriale cartella) {
        return cartellaEsattorialeRepository.save(cartella);
    }

    @Override
    @Transactional
    public CartellaEsattoriale aggiorna(CartellaEsattoriale cartella) {
        return cartellaEsattorialeRepository.save(cartella);
    }

    @Override
    public void rimuovi(Long id) {
        cartellaEsattorialeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cartella esattoriale non trovata con id: " + id));
        cartellaEsattorialeRepository.deleteById(id);
    }
}

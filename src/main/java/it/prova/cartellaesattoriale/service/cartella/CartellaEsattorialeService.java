package it.prova.cartellaesattoriale.service.cartella;
import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import java.util.List;

public interface CartellaEsattorialeService
{
    List<CartellaEsattoriale> listAll(boolean eager);
    CartellaEsattoriale caricaSingola(Long id);
    CartellaEsattoriale caricaSingolaEager(Long id);
    CartellaEsattoriale inserisci(CartellaEsattoriale cartella);
    CartellaEsattoriale aggiorna(CartellaEsattoriale cartella);
    void rimuovi(Long id);
}

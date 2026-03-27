package it.prova.cartellaesattoriale.repository.cartella;
import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartellaEsattorialeRepository extends JpaRepository<CartellaEsattoriale, Long>
{
    @Query("select ce from CartellaEsattoriale ce join fetch ce.contribuente")
    List<CartellaEsattoriale> findAllEager();

    @Query("select ce from CartellaEsattoriale ce join fetch ce.contribuente where ce.id = ?1")
    CartellaEsattoriale findSingleEager(Long id);
}

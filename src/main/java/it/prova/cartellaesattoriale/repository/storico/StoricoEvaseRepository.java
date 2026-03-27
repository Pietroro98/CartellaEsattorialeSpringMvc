package it.prova.cartellaesattoriale.repository.storico;
import it.prova.cartellaesattoriale.model.StoricoEvase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoricoEvaseRepository extends JpaRepository<StoricoEvase, Long>, StoricoEvaseRepositoryCustom  {
    //
}

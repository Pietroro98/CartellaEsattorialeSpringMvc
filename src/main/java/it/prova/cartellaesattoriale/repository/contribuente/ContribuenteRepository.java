package it.prova.cartellaesattoriale.repository.contribuente;

import it.prova.cartellaesattoriale.model.Contribuente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContribuenteRepository extends JpaRepository<Contribuente, Long>
{
    @Query("select distinct c from Contribuente c left join fetch c.cartelle")
    List<Contribuente> findAllEager();

    @Query("select distinct c from Contribuente c left join fetch c.cartelle where c.id = ?1")
    Contribuente findSingleEager(Long id);

    @Query("""
            select distinct c.id
            from Contribuente c
            join c.cartelle ce
            where ce.stato = it.prova.cartellaesattoriale.model.StatoCartella.IN_CONTENZIOSO
            """)
    List<Long> findIdsDaAttenzionare();
}

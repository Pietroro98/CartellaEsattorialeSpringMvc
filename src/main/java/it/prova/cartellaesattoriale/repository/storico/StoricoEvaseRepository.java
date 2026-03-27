package it.prova.cartellaesattoriale.repository.storico;
import it.prova.cartellaesattoriale.model.StoricoEvase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StoricoEvaseRepository extends JpaRepository<StoricoEvase, Long>, StoricoEvaseRepositoryCustom  {
    @Query(value = """
        select
            ce.id as idCartella,
            ce.descrizione as descrizione,
            ce.importo as importo,
            ce.dataCreazione as dataCreazione,
            c.nome as nomeContribuente,
            c.cognome as cognomeContribuente,
            c.codiceFiscale as codiceFiscale
        from cartellaEsattoriale ce
                 inner join contribuente c on c.id = ce.contribuente_id
        where ce.stato = 'CONCLUSA'
          and ce.dataCreazione <= :dataRiferimento
          and not exists (
            select 1
            from storicoEvase se
            where se.idCartellaOld = cast(ce.id as char)
            )"""
            , nativeQuery = true)
    List<StoricoEvaseProjection> findCartelleDaStoricizzare(@Param("dataRiferimento") LocalDate dataRiferimento);
}

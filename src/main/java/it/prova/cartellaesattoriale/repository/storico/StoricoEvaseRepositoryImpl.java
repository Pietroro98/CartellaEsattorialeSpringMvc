package it.prova.cartellaesattoriale.repository.storico;

import it.prova.cartellaesattoriale.model.StoricoEvase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoricoEvaseRepositoryImpl implements StoricoEvaseRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StoricoEvase> findByExample(StoricoEvase example) {
        Map<String, Object> parameterMap = new HashMap<>();
        List<String> whereClauses = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("select * from storicoEvase s where 1=1 ");

        if (StringUtils.isNotEmpty(example.getIdCartellaOld())) {
            whereClauses.add("s.idCartellaOld like :idCartellaOld ");
            parameterMap.put("idCartellaOld", "%" + example.getIdCartellaOld() + "%");
        }
        if (StringUtils.isNotEmpty(example.getDescrizioneOld())) {
            whereClauses.add("s.descrizioneOld like :descrizioneOld ");
            parameterMap.put("descrizioneOld", "%" + example.getDescrizioneOld() + "%");
        }
        if (example.getImportoOld() != null) {
            whereClauses.add("s.importoOld = :importoOld ");
            parameterMap.put("importoOld", example.getImportoOld());
        }
        if (example.getDataCreazioneOld() != null) {
            whereClauses.add("s.dataCreazioneOld >= :dataCreazioneOld ");
            parameterMap.put("dataCreazioneOld", example.getDataCreazioneOld());
        }
        if (StringUtils.isNotEmpty(example.getNomeContribuenteOld())) {
            whereClauses.add("s.nomeContribuenteOld like :nomeContribuenteOld ");
            parameterMap.put("nomeContribuenteOld", "%" + example.getNomeContribuenteOld() + "%");
        }
        if (StringUtils.isNotEmpty(example.getCognomeContribuenteOld())) {
            whereClauses.add("s.cognomeContribuenteOld like :cognomeContribuenteOld ");
            parameterMap.put("cognomeContribuenteOld", "%" + example.getCognomeContribuenteOld() + "%");
        }
        if (StringUtils.isNotEmpty(example.getCodiceFiscaleOld())) {
            whereClauses.add("s.codiceFiscaleOld like :codiceFiscaleOld ");
            parameterMap.put("codiceFiscaleOld", "%" + example.getCodiceFiscaleOld() + "%");
        }
        if (example.getDataStoricizzazione() != null) {
            whereClauses.add("s.dataStoricizzazione >= :dataStoricizzazione ");
            parameterMap.put("dataStoricizzazione", example.getDataStoricizzazione());
        }

        if (!whereClauses.isEmpty()) {
            queryBuilder.append(" and ").append(String.join(" and ", whereClauses));
        }

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), StoricoEvase.class);

        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }
}

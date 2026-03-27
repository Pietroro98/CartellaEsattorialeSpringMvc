package it.prova.cartellaesattoriale.repository.storico;

import it.prova.cartellaesattoriale.model.StoricoEvase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

        StringBuilder queryBuilder = new StringBuilder("select s from StoricoEvase s where s.id = s.id ");

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

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));

        TypedQuery<StoricoEvase> typedQuery = entityManager.createQuery(queryBuilder.toString(), StoricoEvase.class);

        for (String key : parameterMap.keySet()) {
            typedQuery.setParameter(key, parameterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}

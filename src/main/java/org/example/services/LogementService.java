package org.example.services;

import org.example.exceptions.NoInstanceFoundException;
import org.example.models.Logement;
import org.example.repositories.LogementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LogementService {

    @Autowired
    LogementRepository logementRepository;


    public Logement getLogement(Long id) {
        return logementRepository.findById(id).
                orElseThrow(() -> new NoInstanceFoundException("No logement found with this id => " + id));
    }


    public List<Logement> getAllLogement() {
        List<Logement> logements = new ArrayList<Logement>();
        logementRepository.findAll().forEach(lgt -> logements.add(lgt));
        return logements;
    }


    public void delete(Long id) {
        Logement lgt = getLogement(id);
        logementRepository.delete(lgt);
    }


    public Logement addOrUpdate(Logement logement) {
        return logementRepository.save(logement);
    }
}

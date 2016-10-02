/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jovana.videoklubserver.service.impl;

import com.jovana.videoklubserver.repository.KopijaRepository;
import com.jovana.videoklubserver.serivce.KopijaService;
import com.jovana.videoklubzajednicko.domen.Film;
import com.jovana.videoklubzajednicko.domen.Kopija;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmoldovan
 */
@Service
@Transactional
public class KopijaServiceImpl implements KopijaService{
    @Autowired
    KopijaRepository kopijaRepository;
    
    @Override
    public List<Kopija> findAll() throws Exception {
        return kopijaRepository.findAll();
    }

    @Override
    public void delete(Kopija kopija) throws Exception {

    }

    @Override
    public Kopija save(Kopija kopija) throws Exception {
        return null;
    }

    @Override
    public Kopija find(Kopija kopija) throws Exception {
        System.out.println("KOPIJA SERIVCE film id"+kopija.getKopijaPK().getFilmid() + " a kopija id"+kopija.getKopijaPK().getKopijaid());
        return kopijaRepository.findOne(kopija.getKopijaPK());
    }

    @Override
    public List<Kopija> findAllForFilm(Film film) throws Exception {
        return kopijaRepository.findByFilmid(film.getFilmid());
    }
    
}
